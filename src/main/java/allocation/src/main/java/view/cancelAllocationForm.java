package view;

import controller.OrderController;
import controller.allocationController;
import controller.employeeController;

import javax.swing.*;
import java.util.Objects;

public class cancelAllocationForm extends JFrame {
    private JLabel lblEmpID;
    private JLabel lblEmail;
    private JTextField txtEmpEmail;
    private JLabel lblEmpStatus;
    private JTextField txtEmpStatus;
    private JLabel lblOrderID;
    private JLabel lblCustomerEmail;
    private JTextField txtCustomerEmail;
    private JComboBox cbEmpID;
    private JButton btnDeallocate;
    private JTextField txtOrderID;
    private JPanel deallocPane;
    private JComboBox cbAssignID;
    private JTextField txtEmpID;
    private JLabel lblAssignID;

    //normal Constructor to create the view
    public cancelAllocationForm() {
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
        btnDeallocate.addActionListener(e -> {
            int assignID = Integer.parseInt(Objects.requireNonNull(cbAssignID.getSelectedItem()).toString());
            int orderID = Integer.parseInt(Objects.requireNonNull(txtOrderID.getText()).toString());
            int empID = Integer.parseInt(Objects.requireNonNull(txtEmpID.getText()).toString());
            System.out.println(assignID);
            System.out.println(orderID);
            System.out.println(empID);
            //check return message from controller and if 1: success, 0: fail
            if (allocController.deallocateOrder(assignID) == 1) {
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
                JOptionPane.showMessageDialog(deallocPane, "Order deallocated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deallocPane);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(deallocPane, "Invalid Assign ID.", "Error", JOptionPane.ERROR_MESSAGE);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deallocPane);
                frame.dispose();
            }
        });
    }

    private void populateFields() {
        allocationController allocController = new allocationController();
        Object[][] data = allocController.viewFromJSON();
        //populate all allocated orders in the combo box
        for (Object[] datum : data) {
            cbAssignID.addItem(datum[0]);
            if (txtOrderID.getText().isEmpty()) {
                txtOrderID.setText(datum[1].toString());
                txtEmpID.setText(datum[2].toString());
                txtEmpEmail.setText(datum[3].toString());
                txtCustomerEmail.setText(datum[4].toString());
            }
        }
        // action listener for combo box
        cbAssignID.addActionListener(e -> {
            int assignID = Integer.parseInt(Objects.requireNonNull(cbAssignID.getSelectedItem()).toString());
            for (Object[] datum : data) {
                if (datum[0].equals(assignID)) {
                    txtOrderID.setText(datum[1].toString());
                    txtEmpID.setText(datum[2].toString());
                    txtEmpEmail.setText(datum[3].toString());
                    txtCustomerEmail.setText(datum[4].toString());
                }
            }
        });
    }

    //main
    public static void main(String[] args) {
        new cancelAllocationForm();
    }
}
