package model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private Date date;
    private String status;
    private double totalAmount;

    // Getters and setters for the order attributes

    // OrderModel class within the same file
    public static class OrderModel {
        private List<Order> orders;

        public OrderModel() {
            // Initialize the orders list
        }

        public List<Order> getAllOrders() {
            // Retrieve all orders from the data source
            return orders;
        }

        public void addOrder(Order order) {
            // Add a new order to the data source
            orders.add(order);
        }

        public void updateOrder(Order order) {
            // Update an existing order in the data source
            // Use the orderId to identify the order to be updated
        }

        public void removeOrder(int orderId) {
            // Remove an order from the data source based on the orderId
        }
    }
}
