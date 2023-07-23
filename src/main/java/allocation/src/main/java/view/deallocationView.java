package view;

import controller.OrderController;
import controller.allocationController;
import controller.employeeController;

import javax.swing.*;
import java.util.Objects;

public class deallocationView extends JFrame {
    private JLabel lblEmpID;
    private JLabel lblEmail;
    private JTextField txtEmpEmail;
    private JLabel lblEmpStatus;
    private JTextField txtEmpStatus;
    private JLabel lblOrderID;
    private JLabel lblCustomerEmail;
    private JTextField txtCustomerEmail;
    private JLabel lblEmployeeLabel;
    private JComboBox cbEmpID;
    private JButton btnAllocate;
    private JTextField txtOrderID;
    private JPanel deallocPane;

    //normal Constructor to create the view
    public deallocationView() {
        setTitle("Deallocation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        // Set the main panel as the content pane
        setContentPane(deallocPane);
        setVisible(true);

        // Create an instance of the controller
        allocationController allocController = new allocationController();

        // Populate the combo boxes via separate methods, aka populateOrders and populateEmployees
        populateFields();

        // Allocate button action listener
        btnAllocate.addActionListener(e -> {
            int orderID = Integer.parseInt(Objects.requireNonNull(txtOrderID.getText()).toString());
            int empID = Integer.parseInt(Objects.requireNonNull(cbEmpID.getSelectedItem()).toString());
            //check return message from controller and if 1: success, 0: fail
            if (allocController.deallocateOrder(orderID, empID) == 1) {
                JOptionPane.showMessageDialog(deallocPane, "Order deallocated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                //change the status of the order to "Pending
                Object[][] data = new OrderController().getAllOrders();
                for (Object[] datum : data) {
                    if (datum[0].equals(orderID)) {
                        new OrderController().updateOrderStatus(orderID, "Pending");
                    }
                }
                // change status of employee to "Available"
                Object[][] empData = new employeeController().viewFromJSON();
                for (Object[] datum : empData) {
                    if (datum[0].equals(empID)) {
                        new employeeController().updateEmployeeStatus(empID, "Available");
                    }
                }

                //System.exit(0);
            } else {
                //error message
                JOptionPane.showMessageDialog(deallocPane, "Invalid order ID.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        });
    }

    private void populateFields() {
        allocationController allocController = new allocationController();
        Object[][] data = allocController.viewFromJSON();
        //populate all allocated orders in the combo box
        for (Object[] datum : data) {
            cbEmpID.addItem(datum[1]);
        }
        // initial values
        txtOrderID.setText(data[0][0].toString());
        txtEmpEmail.setText(data[0][2].toString());
        txtCustomerEmail.setText(data[0][3].toString());
        // action listener for combo box
        cbEmpID.addActionListener(e -> {
            String empID = cbEmpID.getSelectedItem().toString();
            for (Object[] datum : data) {
                if (datum[1].toString().equals(empID)) {
                    txtOrderID.setText(datum[0].toString());
                    txtEmpEmail.setText(datum[2].toString());
                    txtCustomerEmail.setText(datum[3].toString());
                }
            }
        });
    }

    //main
    public static void main(String[] args) {
        new deallocationView();
    }
}
