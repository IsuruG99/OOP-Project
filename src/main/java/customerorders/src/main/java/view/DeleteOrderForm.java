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

    private OrderController orderController;

    public DeleteOrderForm() {
        orderController = new OrderController();

        removeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderIdStr = txtDeleteID.getText();
                int orderId = Integer.parseInt(orderIdStr);

                // Delete the order using the order controller
                orderController.deleteOrder(orderId);

                // Display success message
                JOptionPane.showMessageDialog(removeOrderPane, "Order deleted successfully.");

                // Clear the input fields
                txtDeleteID.setText("");
                txtDeleteCustomerID.setText("");
                txtDeleteType.setText("");
                txtDeleteDate.setText("");
                txtDeleteStatus.setText("");
                txtDeleteTotal.setText("");
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
        frame.pack();
        frame.setVisible(true);
    }
}
