package view;

import controller.allocationController;
import controller.OrderController;
import controller.employeeController;

import javax.swing.*;
import java.util.Objects;

public class completeAllocationForm extends JFrame {
    private JLabel lblCompleteOrderID;
    private JLabel lblCompleteCustomerID;
    private JTextField txtCompleteCustomerID;
    private JLabel lblCompleteType;
    private JTextField txtCompleteType;
    private JLabel lblCompleteDate;
    private JTextField txtCompleteDate;
    private JLabel lblCompleteStatus;
    private JTextField txtCompleteStatus;
    private JLabel lblCompleteTotal;
    private JTextField txtCompleteTotal;
    private JButton completeOrderButton;
    private JComboBox cbCompleteOrderID;
    private JLabel lblCompleteEmail;
    private JTextField txtCompleteEmail;
    private JPanel completeOrderPane;

    public completeAllocationForm() {
        // Set the title, default close operation, size and location of the frame
        setTitle("Complete Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        // Set the main panel as the content pane
        setContentPane(completeOrderPane);
        setVisible(true);

        allocationController allocController = new allocationController();

        // Populate the combo box with order IDs
        populateFields();

        completeOrderButton.addActionListener(e -> {
            // ask for confirmation before deleting order
            int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to complete this order?", "Warning", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.NO_OPTION) {
                return;
            }
            int orderId = Integer.parseInt(Objects.requireNonNull(cbCompleteOrderID.getSelectedItem()).toString());
            // make a loop on allocController.viewFromJSON() to find the employee ID
            int empId = 0;
            Object[][] idCheck = allocController.viewFromJSON();
            for (Object[] datum : idCheck) {
                if (datum[0].equals(orderId)) {
                    empId = Integer.parseInt(datum[1].toString());
                }
            }

            // Set Order status to complete
            if (allocController.deallocateOrder(orderId, empId) == 1) {
                JOptionPane.showMessageDialog(completeOrderPane, "Order submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                // completeOrder(), changing order status to "Complete"
                Object[][] data = new OrderController().getAllOrders();
                for (Object[] datum : data) {
                    if (datum[0].equals(orderId)) {
                        new OrderController().completeOrder(orderId);
                    }
                }
                // change status of employee to "Available"
                Object[][] empData = new employeeController().viewFromJSON();
                for (Object[] datum : empData) {
                    if (datum[0].equals(empId)) {
                        new employeeController().updateEmployeeStatus(empId, "Available");
                    }
                }
            }
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(null);
            frame.dispose();
        });
    }

    private void populateFields() {
        Object[][] data = new OrderController().getAllOrders();
        //populate all order IDs in the combo box & rest of the fields with first order
        for (Object[] datum : data) {
            if (datum[5].equals("Allocated")){
                cbCompleteOrderID.addItem(datum[0]);
                if (txtCompleteCustomerID.getText().equals("")){
                    txtCompleteCustomerID.setText(datum[1].toString());
                    txtCompleteType.setText(datum[3].toString());
                    txtCompleteDate.setText(datum[4].toString());
                    txtCompleteStatus.setText(datum[5].toString());
                    txtCompleteTotal.setText(datum[6].toString());
                    txtCompleteEmail.setText(datum[2].toString());
                }
            }
        }

        // action listener for combo box
        cbCompleteOrderID.addActionListener(e -> {
            String orderIdStr = Objects.requireNonNull(cbCompleteOrderID.getSelectedItem()).toString();
            for (Object[] datum : data) {
                if (datum[0].toString().equals(orderIdStr)) {
                    txtCompleteCustomerID.setText(datum[1].toString());
                    txtCompleteEmail.setText(datum[2].toString());
                    txtCompleteType.setText(datum[3].toString());
                    txtCompleteDate.setText(datum[4].toString());
                    txtCompleteStatus.setText(datum[5].toString());
                    txtCompleteTotal.setText(datum[6].toString());
                }
            }
        });
    }

    //main
    public static void main(String[] args) {
        new completeAllocationForm();
    }
}
