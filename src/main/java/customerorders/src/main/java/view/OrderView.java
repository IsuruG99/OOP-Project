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
        // Create the table with default table model
        orderTable = new JTable();
        tableModel = new DefaultTableModel();
        orderTable.setModel(tableModel);

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // Create the main panel and add the scroll pane to it
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the order pane and add the main panel to it
        orderPane = new JPanel(new BorderLayout());
        orderPane.add(mainPanel, BorderLayout.CENTER);

        // Set the order pane as the content pane of the frame
        setContentPane(orderPane);
    }

    public void displayOrders(Object[][] data, String[] columns) {
        // Set the table model data and columns
        tableModel.setDataVector(data, columns);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderView orderView = new OrderView();

            // Load orders from JSON file using OrderController
            OrderController orderController = new OrderController();
            Object[][] data = orderController.getAllOrders();
            String[] columns = {"Order ID", "Customer ID", "Type", "Date", "Status", "Total Amount"};

            // Display the orders in the view
            orderView.displayOrders(data, columns);
        });
    }
}
