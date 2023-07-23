package view;

import controller.OrderController;
import controller.allocationController;
import controller.employeeController;

import javax.swing.*;
import java.util.Objects;

public class allocationView extends JFrame {
    private JPanel allocPane;
    private JLabel lblEmpID;
    private JButton btnAllocate;
    private JComboBox cbEmpID;
    private JLabel lblName;
    private JTextField txtEmpName;
    private JTextField txtEmpProfession;
    private JTextField txtEmpEmail;
    private JTextField txtEmpStatus;
    private JLabel lblEmpStatus;
    private JLabel lblProfession;
    private JLabel lblEmail;
    private JLabel lblOrderID;
    private JComboBox cbOrderID;
    private JTextField txtOrderType;
    private JLabel lblOrderType;
    private JLabel lblOrderStatus;
    private JLabel lblCustomerEmail;
    private JLabel lblCustomerID;
    private JTextField txtCustomerID;
    private JTextField txtCustomerEmail;
    private JTextField txtOrderStatus;
    private JLabel lblEmployeeLabel;
    private JLabel lblOrderLabel;

    //normal Constructor to create the view
    public allocationView() {
        setTitle("Allocation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        // Set the main panel as the content pane
        setContentPane(allocPane);
        setVisible(true);

        // Create an instance of the controller
        allocationController allocController = new allocationController();

        // Populate the combo boxes via separate methods, aka populateOrders and populateEmployees
        populateOrders();
        populateEmployees();

        // Allocate button action listener
        btnAllocate.addActionListener(e -> {
            int orderID = Integer.parseInt(Objects.requireNonNull(cbOrderID.getSelectedItem()).toString());
            int empID = Integer.parseInt(Objects.requireNonNull(cbEmpID.getSelectedItem()).toString());
            //check return message from controller and if 1: success, 0: fail
            if (allocController.allocateOrder(orderID, empID, txtEmpEmail.getText(), txtCustomerEmail.getText()) == 1) {
                JOptionPane.showMessageDialog(allocPane, "Order allocated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                //change the status of the order to "Allocated"
                Object [][] data = new OrderController().getAllOrders();
                for (Object[] datum : data) {
                    if (datum[0].equals(orderID)) {
                        new OrderController().updateOrderStatus(orderID, "Allocated");
                    }
                }
                //change the status of the employee to "Working"
                Object [][] data2 = new employeeController().viewFromJSON();
                for (Object[] datum : data2) {
                    if (datum[0].equals(empID)) {
                        new employeeController().updateEmployeeStatus(empID, "Working");
                    }
                }
                //refresh the combo boxes and empty the text fields
                cbOrderID.removeAllItems();
                txtCustomerID.setText("");
                txtCustomerEmail.setText("");
                txtOrderType.setText("");
                txtOrderStatus.setText("");
                cbEmpID.removeAllItems();
                txtEmpName.setText("");
                txtEmpProfession.setText("");
                txtEmpEmail.setText("");
                txtEmpStatus.setText("");
                populateOrders();
                populateEmployees();
            } else {
                //error message
                JOptionPane.showMessageDialog(allocPane, "Invalid order ID or employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public void populateOrders() {
        Object[][] data = new OrderController().getAllOrders();
        //populate the combo box //with only values where status is "Pending"
        for (Object[] datum : data) {
            if (datum[5].equals("Pending")) {
                cbOrderID.addItem(datum[0]);
                //if first time then set the initial values
                if (txtOrderType.getText().isEmpty()) {
                    txtCustomerID.setText(datum[1].toString());
                    txtOrderType.setText(datum[3].toString());
                    txtOrderStatus.setText(datum[5].toString());
                    txtCustomerID.setText(datum[1].toString());
                    txtCustomerEmail.setText(datum[2].toString());
                }
            }
        }
        // action listener for combo box
        cbOrderID.addActionListener(e -> {
            int orderID = Integer.parseInt(Objects.requireNonNull(cbOrderID.getSelectedItem()).toString());
            for (Object[] datum : data) {
                if (datum[0].equals(orderID) && datum[5].equals("Pending")) {
                    txtCustomerID.setText(datum[1].toString());
                    txtOrderType.setText(datum[3].toString());
                    txtOrderStatus.setText(datum[5].toString());
                    txtCustomerID.setText(datum[1].toString());
                    txtCustomerEmail.setText(datum[2].toString());
                }
            }
        });
    }

    public void populateEmployees() {
        Object[][] data = new employeeController().viewFromJSON();
        //populate the combo box if status is "Available"
        for (Object[] datum : data) {
            if (datum[5].equals("Available")) {
                cbEmpID.addItem(datum[0]);
                //if first time then set the initial values
                if (txtEmpName.getText().isEmpty()) {
                    txtEmpName.setText(datum[1].toString());
                    txtEmpProfession.setText(datum[2].toString());
                    txtEmpEmail.setText(datum[4].toString());
                    txtEmpStatus.setText(datum[5].toString());
                }
            }
        }

        // action listener for combo box
        cbEmpID.addActionListener(e -> {
            int empID = Integer.parseInt(Objects.requireNonNull(cbEmpID.getSelectedItem()).toString());
            for (Object[] datum : data) {
                if (datum[0].equals(empID) && datum[5].equals("Available")) {
                    txtEmpName.setText(datum[1].toString());
                    txtEmpProfession.setText(datum[2].toString());
                    txtEmpEmail.setText(datum[4].toString());
                    txtEmpStatus.setText(datum[5].toString());
                }
            }
        });
    }

    //main
    public static void main(String[] args) {
        new allocationView();
    }
}
