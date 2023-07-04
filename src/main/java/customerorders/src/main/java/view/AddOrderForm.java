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

public class AddOrderForm {
    private JPanel addOrderPane;
    private JLabel lblAddDate;
    private JTextField txtAddDate;
    private JLabel lblAddStatus;
    private JLabel lblAddCustomerID;
    private JTextField txtAddCustomerID;
    private JLabel lblAddType;
    private JTextField txtAddType;
    private JTextField txtAddStatus;
    private JLabel lblAddTotal;
    private JTextField txtAddTotal;
    private JLabel lblAddOrderID;
    private JTextField txtAddOrderID;
    private JButton addOrderButton;

    private OrderController orderController;

    public AddOrderForm() {
        orderController = new OrderController();

        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderIdStr = txtAddOrderID.getText();
                int orderId = Integer.parseInt(orderIdStr);

                String customerIdStr = txtAddCustomerID.getText();
                int customerId = Integer.parseInt(customerIdStr);

                String type = txtAddType.getText();

                String dateString = txtAddDate.getText();
                Date date;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    date = dateFormat.parse(dateString);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(addOrderPane, "Invalid date format. Please use yyyy-MM-dd.");
                    return;
                }

                String status = txtAddStatus.getText();

                String totalAmountStr = txtAddTotal.getText();
                double totalAmount = Double.parseDouble(totalAmountStr);

                // Create the new order
                Order newOrder = new Order(orderId, customerId, type, date, status, totalAmount);

                // Add the order using the order controller
                orderController.addOrder(orderId, customerId, type, date, status, totalAmount);

                // Display success message
                JOptionPane.showMessageDialog(addOrderPane, "Order added successfully.");

                // Clear the input fields
                txtAddOrderID.setText("");
                txtAddCustomerID.setText("");
                txtAddType.setText("");
                txtAddDate.setText("");
                txtAddStatus.setText("");
                txtAddTotal.setText("");
            }
        });
    }

    public JPanel getAddOrderPane() {
        return addOrderPane;
    }

    public void displayForm() {
        JFrame frame = new JFrame("Add Order Form");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setContentPane(addOrderPane);
        frame.setPreferredSize(new Dimension(300, 500));
        frame.pack();
        frame.setVisible(true);
    }
}
