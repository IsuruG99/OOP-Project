package controller;

import model.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderController {
    private static final String JSON_FILE_PATH = "C:/OOP-Project/data/orders.json";

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

    private List<Order> readOrdersFromJson() {
        List<Order> orders = new ArrayList<>();

        try {
            String json = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));

            // Remove square brackets from the JSON string
            json = json.replace("[", "").replace("]", "");

            // Split the JSON string by curly braces to separate individual order objects
            String[] orderJsonArray = json.split("\\{");

            for (String orderJson : orderJsonArray) {
                if (!orderJson.isEmpty()) {
                    // Split the orderJson by commas to separate key-value pairs
                    String[] keyValuePairs = orderJson.split(",");

                    int orderId = 0;
                    int customerId = 0;
                    String type = "";
                    Date date = new Date();
                    String status = "";
                    double totalAmount = 0.0;

                    for (String pair : keyValuePairs) {
                        // Split each key-value pair by colon to get key and value
                        String[] keyValue = pair.split(":");

                        if (keyValue.length == 2) {
                            String key = keyValue[0].trim();
                            String value = keyValue[1].trim();

                            // Extract values for each key
                            switch (key) {
                                case "\"orderId\"" -> orderId = Integer.parseInt(value);
                                case "\"customerId\"" -> customerId = Integer.parseInt(value);
                                case "\"type\"" -> type = value.replaceAll("\"", "");
                                case "\"date\"" -> {
                                    value = value.replaceAll("\"", "");
                                    if (!value.isEmpty()) {
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        date = dateFormat.parse(value);
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

                    Order order = new Order(orderId, customerId, type, date, status, totalAmount);
                    orders.add(order);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
