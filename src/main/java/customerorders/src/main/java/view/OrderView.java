package view;

import controller.OrderController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderView extends JFrame {
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JScrollPane orderScroll;
    private JPanel orderPane;

    public OrderView() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Order View");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        orderTable = new JTable();
        tableModel = new DefaultTableModel(0, 6); // Set initial row count to 0
        orderTable.setModel(tableModel);
        orderScroll = new JScrollPane(orderTable);
        orderPane = new JPanel(new BorderLayout());
        orderPane.add(orderScroll, BorderLayout.CENTER);
        setContentPane(orderPane);
    }

    public void displayOrders(Object[][] data, String[] columns) {
        tableModel.setDataVector(data, columns); // Set the table model data and columns
    }

    public void loadOrdersFromController() {
        OrderController orderController = new OrderController(); // Load orders from JSON file using OrderController
        Object[][] data = orderController.getAllOrders();
        String[] columns = {"Order ID", "Customer ID", "Type", "Date", "Status", "Total Amount"};
        displayOrders(data, columns); // Display the orders in the view
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderView orderView = new OrderView();
            orderView.loadOrdersFromController();
        });
    }
}
