package view;

import controller.OrderController;
import model.Order;

import javax.swing.*;
import java.util.List;

public class OrderView extends JFrame {

    public OrderView() {
        // Set up the JFrame properties
        setTitle("Order Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the components and layout
        initComponents();
    }

    public void setController(OrderController controller) {
    }

    public void displayOrders(List<Order> orders) {
        // Display the list of orders in the view
    }

    public void promptAddOrder() {
        // Display a prompt for adding a new order
    }

    public void promptUpdateOrder(Order order) {
        // Display a prompt for updating an existing order
    }

    public void promptRemoveOrder(int orderId) {
        // Display a prompt for removing an order
    }

    private void initComponents() {
        // Initialize and add the GUI components to the JFrame
        // Set up event listeners for user interactions
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderView view = new OrderView();
            Order.OrderModel model = new Order.OrderModel();
            OrderController controller = new OrderController(model, view);
            view.setController(controller);
            view.setVisible(true);
        });
    }
}
