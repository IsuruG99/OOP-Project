package view;

import controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteOrderForm {
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
    private JButton removeOrderButton;
    private JPanel removeOrderPane;
    private JComboBox cbDeleteID;

    private OrderController orderController;

    public DeleteOrderForm() {
        orderController = new OrderController();

        // Populate the combo box with order IDs
        populateFields();

        removeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ask for confirmation before deleting order
                int check = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this order?", "Warning", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.NO_OPTION) {
                    return;
                }
                int orderId = Integer.parseInt(txtDeleteID.getText());

                // Delete the order using the order controller
                orderController.deleteOrder(orderId);

                // Display success message
                JOptionPane.showMessageDialog(removeOrderPane, "Order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
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
        txtDeleteType.setText(data[0][2].toString());
        txtDeleteDate.setText(data[0][3].toString());
        txtDeleteStatus.setText(data[0][4].toString());
        txtDeleteTotal.setText(data[0][5].toString());

        // action listener for combo box
        cbDeleteID.addActionListener(e -> {
            String orderIdStr = cbDeleteID.getSelectedItem().toString();
            for (Object[] datum : data) {
                if (datum[0].toString().equals(orderIdStr)) {
                    txtDeleteCustomerID.setText(datum[1].toString());
                    txtDeleteType.setText(datum[2].toString());
                    txtDeleteDate.setText(datum[3].toString());
                    txtDeleteStatus.setText(datum[4].toString());
                    txtDeleteTotal.setText(datum[5].toString());
                }
            }
        });
    }

    public JPanel getRemoveOrderPane() {
        return removeOrderPane;
    }

    public void displayForm() {
        JFrame frame = new JFrame("Delete Order Form");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setContentPane(removeOrderPane);
        frame.setPreferredSize(new Dimension(300, 500));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
