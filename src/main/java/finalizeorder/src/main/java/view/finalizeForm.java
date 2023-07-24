package view;

import controller.OrderController;

import javax.swing.*;
import java.util.Objects;

public class finalizeForm extends JFrame{
    private JLabel lblCompleteOrderID;
    private JComboBox cbCompleteOrderID;
    private JLabel lblCompleteCustomerID;
    private JTextField txtCompleteCustomerID;
    private JLabel lblCompleteEmail;
    private JTextField txtCompleteEmail;
    private JLabel lblCompleteType;
    private JTextField txtCompleteType;
    private JLabel lblCompleteDate;
    private JTextField txtCompleteDate;
    private JLabel lblCompleteStatus;
    private JTextField txtCompleteStatus;
    private JLabel lblCompleteTotal;
    private JTextField txtCompleteTotal;
    private JButton btnFinalizeOrder;
    private JPanel finalizePane;

    public finalizeForm() {
        setTitle("Finalize Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        // Set the main panel as the content pane
        setContentPane(finalizePane);
        setVisible(true);

        // populate the combo box with order IDs
        populateFields();

        btnFinalizeOrder.addActionListener(e -> {
            // ask for confirmation before deleting order
            int check = JOptionPane.showConfirmDialog(finalizePane, "Are you sure you want to notify the Customer?", "Warning", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.NO_OPTION) {
                return;
            }
            int orderId = Integer.parseInt(Objects.requireNonNull(cbCompleteOrderID.getSelectedItem()).toString());
            // Set Order status to Finalized, the function output String message, we need to display it in a dialog box
            String message = new OrderController().finalizeOrder(orderId);
            JOptionPane.showMessageDialog(finalizePane, message, "Success", JOptionPane.INFORMATION_MESSAGE);

        });

    }

    private void populateFields() {
        // populate the combo box with order IDs
        Object[][] orders = new OrderController().getAllOrders();
        for (Object[] order : orders) {
            if (order[5].equals("Completed")){
                cbCompleteOrderID.addItem(order[0]);
                if (txtCompleteCustomerID.getText().isEmpty()) {
                    txtCompleteCustomerID.setText(order[1].toString());
                    txtCompleteEmail.setText(order[2].toString());
                    txtCompleteType.setText(order[3].toString());
                    txtCompleteDate.setText(order[4].toString());
                    txtCompleteStatus.setText(order[5].toString());
                    txtCompleteTotal.setText(order[6].toString());
                }
            }
        }

        // add action listener to combo box
        cbCompleteOrderID.addActionListener(e -> {
            // get the selected order ID
            int orderId = Integer.parseInt(Objects.requireNonNull(cbCompleteOrderID.getSelectedItem()).toString());
            // get the order details
            Object[][] data = new OrderController().getAllOrders();
            for (Object[] datum : data) {
                if (datum[0].equals(orderId) && datum[5].equals("Completed")) {
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

    public static void main(String[] args) {
        new finalizeForm();
    }
}
