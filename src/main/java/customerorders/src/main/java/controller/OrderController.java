package controller;

import model.Order;
import view.OrderView;

import java.util.List;

public class OrderController {
    private Order.OrderModel model;
    private OrderView view;

    public OrderController(Order.OrderModel model, OrderView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public void displayOrders() {
        List<Order> orders = model.getAllOrders();
        view.displayOrders(orders);
    }

    public void addOrder(Order order) {
        model.addOrder(order);
    }

    public void updateOrder(Order order) {
        model.updateOrder(order);
    }

    public void removeOrder(int orderId) {
        model.removeOrder(orderId);
    }

    // Other controller methods for handling user interactions
}

