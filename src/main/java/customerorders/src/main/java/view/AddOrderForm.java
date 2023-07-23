package view;

import controller.OrderController;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddOrderForm {
    private JPanel addOrderPane;
    private JLabel lblAddDate;
    private JTextField txtAddDate;
    private JLabel lblAddCustomerID;
    private JTextField txtAddCustomerID;
    private JLabel lblAddType;
    private JTextField txtAddType;
    private JLabel lblAddTotal;
    private JTextField txtAddTotal;
    private JTextField txtAddOrderID;
    private JButton addOrderButton;
    private JComboBox cbAddType;
    private JTextField txtAddEmail;
    private JLabel lblAddEmail;

    private OrderController orderController;

    public AddOrderForm() {
        orderController = new OrderController();

        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the last order ID and increment it by 1
                int orderId = Integer.parseInt(orderController.getLastOrderId());
                int customerId = Integer.parseInt(txtAddCustomerID.getText());
                String email = txtAddEmail.getText();
                String type = Objects.requireNonNull(cbAddType.getSelectedItem()).toString();
                String dateString = txtAddDate.getText();
                Date date;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    date = dateFormat.parse(dateString);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(addOrderPane, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String status = "Pending";
                String totalAmountStr = txtAddTotal.getText();
                double totalAmount = Double.parseDouble(totalAmountStr);
                orderController.addOrder(orderId, customerId, email, type, date, status, totalAmount);

                // Display success message
                JOptionPane.showMessageDialog(addOrderPane, "Order added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the input fields
                txtAddCustomerID.setText("");
                txtAddDate.setText("");
                txtAddTotal.setText("");

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(addOrderPane);
                frame.dispose();
            }
        });
    }

    public void displayForm() {
        JFrame frame = new JFrame("Add Order Form");
        frame.setContentPane(addOrderPane);
        frame.setPreferredSize(new Dimension(300, 500));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
