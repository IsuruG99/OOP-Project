package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppView extends JFrame {
    private JTabbedPane mainPane;
    private JPanel orderPane;
    private JPanel inventoryPane;
    private JPanel mainAppPanel;
    private JButton btnAddOrder;
    private JButton btnDisplayOrders;
    private JButton btnRemoveOrder;

    public AppView() {
        setTitle("My App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(375, 300);

        // Create the main panel
        mainAppPanel = new JPanel();
        mainAppPanel.setLayout(new BorderLayout());

        // Create the tabbed pane
        mainPane = new JTabbedPane();

        // Create the order pane
        orderPane = new JPanel();
        orderPane.setLayout(null); // Set layout to null for absolute positioning

        // Create the buttons
        btnAddOrder = new JButton("Add Order");
        btnDisplayOrders = new JButton("Display Orders");
        btnRemoveOrder = new JButton("Remove Order");

        // Set the positions of the buttons within the order pane
        btnAddOrder.setBounds(100, 50, 150, 30);
        btnDisplayOrders.setBounds(100, 100, 150, 30);
        btnRemoveOrder.setBounds(100, 150, 150, 30);

        // Add the buttons to the order pane
        orderPane.add(btnAddOrder);
        orderPane.add(btnDisplayOrders);
        orderPane.add(btnRemoveOrder);

        // Add the order pane to the main pane
        mainPane.add("Order", orderPane);

        // Create the inventory pane
        inventoryPane = new JPanel();
        // Add the inventory pane to the main pane
        mainPane.add("Inventory", inventoryPane);

        // Add the main pane to the main panel
        mainAppPanel.add(mainPane, BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainAppPanel);

        setVisible(true);
        btnDisplayOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderView orderView = new OrderView();
                orderView.loadOrdersFromController();
            }
        });
        btnRemoveOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOrderForm deleteOrderForm = new DeleteOrderForm();
                deleteOrderForm.displayForm();
            }
        });
        btnAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrderForm addOrderForm = new AddOrderForm();
                addOrderForm.displayForm();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppView appView = new AppView();
        });
    }
}
