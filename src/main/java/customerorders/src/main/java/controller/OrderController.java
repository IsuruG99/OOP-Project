package controller;

import model.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderController {
    private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/data/orders.json";

    // Get all orders from JSON file
    public Object[][] getAllOrders() {
        // Read the JSON file and parse orders
        List<Order> orders = readOrdersFromJson();

        // Prepare the data for the table
        Object[][] data = new Object[orders.size()][6];
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = order.getOrderId();
            data[i][1] = order.getCustomerId();
            data[i][2] = order.getType();
            data[i][3] = formatDate(order.getDate());
            data[i][4] = order.getStatus();
            data[i][5] = order.getTotalAmount();
        }
        return data;
    }

    // Read orders from JSON file
    private List<Order> readOrdersFromJson() {
        List<Order> orders = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            json = json.replace("[", "").replace("]", ""); // Remove square brackets from the JSON string
            String[] orderJsonArray = json.split("\\{"); // Split the JSON string by curly braces to separate individual order objects
            for (String orderJson : orderJsonArray) {
                if (!orderJson.isEmpty()) {
                    String[] keyValuePairs = orderJson.split(","); // Split the orderJson by commas

                    int orderId = 0;
                    int customerId = 0;
                    String type = "";
                    Date date = new Date();
                    String status = "";
                    double totalAmount = 0.0;
                    boolean validOrder = false; // Flag to check if the order is valid
                    if (keyValuePairs.length > 0) { // Skip the first empty element
                        int startIndex = 0;
                        if (keyValuePairs[0].trim().isEmpty()) {
                            startIndex = 1;
                        }
                        for (int i = startIndex; i < keyValuePairs.length; i++) {
                            String[] keyValue = keyValuePairs[i].split(":"); // Split each key-value pair by colon
                            if (keyValue.length == 2) {
                                String key = keyValue[0].trim();
                                String value = keyValue[1].trim();

                                switch (key) { // Extract values for each key
                                    case "\"orderId\"" -> orderId = Integer.parseInt(value);
                                    case "\"customerId\"" -> customerId = Integer.parseInt(value);
                                    case "\"type\"" -> type = value.replaceAll("\"", "");
                                    case "\"date\"" -> {
                                        value = value.replaceAll("\"", "");
                                        if (!value.isEmpty()) {
                                            try {
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                                date = dateFormat.parse(value);
                                                validOrder = true; // Date is parsed successfully, consider it a valid order
                                            } catch (ParseException e) { // Handle parsing error if needed
                                                date = new Date(); // Set a default date value
                                            }
                                        } else {
                                            System.out.println("Value is empty. Set default date: ");
                                            date = new Date();
                                        }
                                    }
                                    case "\"status\"" -> status = value.replaceAll("\"", "");
                                    case "\"totalAmount\"" -> {
                                        value = value.replaceAll("[^\\d.]", "");
                                        totalAmount = Double.parseDouble(value);
                                    }
                                }
                            }
                        }
                    }
                    if (validOrder && orderId != 0 && customerId != 0) { // Validity Check & Positive ID Check
                        Order order = new Order(orderId, customerId, type, date, status, totalAmount);
                        orders.add(order);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Add order to JSON file
    public void addOrder(int orderId, int customerId, String type, Date date, String status, double totalAmount) {
        List<Order> orders = readOrdersFromJson();

        Order order = new Order(orderId, customerId, type, date, status, totalAmount);
        orders.add(order);

        saveOrdersToJson(orders);
    }

    // Save orders to JSON file
    private void saveOrdersToJson(List<Order> orders) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            jsonBuilder.append(order.toJson());
            if (i < orders.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");

        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(jsonBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        List<Order> orders = readOrdersFromJson();

        // Find the order with the given orderId and remove it from the list
        Order orderToDelete = null;
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                orderToDelete = order;
                break;
            }
        }

        if (orderToDelete != null) {
            orders.remove(orderToDelete);
            saveOrdersToJson(orders);
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    // Format date to yyyy-MM-dd
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    // Get the last order ID and increment it by 1
    public String getLastOrderId() {
        List<Order> orders = readOrdersFromJson();
        if (orders.size() > 0) {
            Order lastOrder = orders.get(orders.size() - 1);
            return String.valueOf(lastOrder.getOrderId() + 1);
        } else {
            return "1";
        }
    }
}
