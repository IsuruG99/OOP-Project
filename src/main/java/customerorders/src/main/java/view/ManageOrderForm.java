package view;

import controller.OrderController;

import javax.swing.*;
import java.sql.Date;

public class ManageOrderForm extends JFrame {
    private JLabel lblDeleteOrderID;
    private JTextField txtDeleteID;
    private JLabel lblDeleteDate;
    private JTextField txtDeleteDate;
    private JLabel lblDeleteStatus;
    private JLabel lblDeleteCustomerID;
    private JTextField txtDeleteCustomerID;
    private JLabel lblDeleteType;
    private JTextField txtDeleteType;
    private JTextField txtDeleteStatus;
    private JLabel lblDeleteTotal;
    private JTextField txtDeleteTotal;
    private JButton btnRemoveOrder;
    private JPanel removeOrderPane;
    private JComboBox cbDeleteID;
    private JTextField txtDeleteEmail;
    private JLabel lblDeleteEmail;
    private JButton btnUpdateOrder;
    private JComboBox cbDeleteType;

    private OrderController orderController;

    public ManageOrderForm() {
        setTitle("Manage Order Form");
        setContentPane(removeOrderPane);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        orderController = new OrderController();

        // Populate the combo box with order IDs
        populateFields();

        btnUpdateOrder.addActionListener(e -> {
            // ask for confirmation before deleting order
            int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this order?", "Warning", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.NO_OPTION) {
                return;
            }
            int orderId = Integer.parseInt(cbDeleteID.getSelectedItem().toString());
            int customerId = Integer.parseInt(txtDeleteCustomerID.getText());
            String email = txtDeleteEmail.getText();
            String type = cbDeleteType.getSelectedItem().toString();
            Date date = Date.valueOf(txtDeleteDate.getText());
            String status = txtDeleteStatus.getText();
            double total = Double.parseDouble(txtDeleteTotal.getText());

            // Update the order using the order controller
            orderController.updateOrder(orderId, customerId, email, type, date, status, total);


            // Display success message
            JOptionPane.showMessageDialog(removeOrderPane, "Order updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(removeOrderPane);
            frame.dispose();
        });


        btnRemoveOrder.addActionListener(e -> {
            // ask for confirmation before deleting order
            int check = JOptionPane.showConfirmDialog(removeOrderPane, "Are you sure you want to delete this order?", "Warning", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.NO_OPTION) {
                return;
            }
            int orderId = Integer.parseInt(cbDeleteID.getSelectedItem().toString());

            // Delete the order using the order controller
            orderController.deleteOrder(orderId);

            // Display success message
            JOptionPane.showMessageDialog(removeOrderPane, "Order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(removeOrderPane);
            frame.dispose();
        });
    }

    private void populateFields() {
        Object[][] data = orderController.getAllOrders();
        //populate all order IDs in the combo box
        for (Object[] datum : data) {
            cbDeleteID.addItem(datum[0]);
        }
        // set the first order ID as default
        cbDeleteID.setSelectedIndex(0);
        txtDeleteCustomerID.setText(data[0][1].toString());
        txtDeleteEmail.setText(data[0][2].toString());
        cbDeleteType.setSelectedItem(data[0][3].toString());
        txtDeleteDate.setText(data[0][4].toString());
        txtDeleteStatus.setText(data[0][5].toString());
        txtDeleteTotal.setText(data[0][6].toString());

        // action listener for combo box
        cbDeleteID.addActionListener(e -> {
            String orderIdStr = cbDeleteID.getSelectedItem().toString();
            for (Object[] datum : data) {
                if (datum[0].toString().equals(orderIdStr)) {
                    txtDeleteCustomerID.setText(datum[1].toString());
                    txtDeleteEmail.setText(datum[2].toString());
                    cbDeleteType.setSelectedItem(datum[3].toString());
                    txtDeleteDate.setText(datum[4].toString());
                    txtDeleteStatus.setText(datum[5].toString());
                    txtDeleteTotal.setText(datum[6].toString());
                }
            }
        });
    }

    public static void main(String[] args) {
        new ManageOrderForm();
    }
}
