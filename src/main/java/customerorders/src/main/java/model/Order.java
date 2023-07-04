package model;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;
    private String type;
    private Date date;
    private String status;
    private double totalAmount;

    public Order(int orderId, int customerId, String type, Date date, String status, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.type = type;
        this.date = date;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Getters and setters for the order attributes

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String toJson() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"orderId\":").append(orderId).append(",");
        jsonBuilder.append("\"customerId\":").append(customerId).append(",");
        jsonBuilder.append("\"type\":\"").append(type).append("\",");
        jsonBuilder.append("\"date\":\"").append(date.toString()).append("\",");
        jsonBuilder.append("\"status\":\"").append(status).append("\",");
        jsonBuilder.append("\"totalAmount\":").append(totalAmount);
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public void saveToJsonFile(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(toJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Order fromJson(String json) throws ParseException {
        json = json.trim();
        if (json.charAt(0) == '{' && json.charAt(json.length() - 1) == '}') {
            json = json.substring(1, json.length() - 1);
        }

        String[] pairs = json.split(",");
        int orderId = 0;
        int customerId = 0;
        String type = "";
        Date date = null;
        String status = "";
        double totalAmount = 0.0;

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if ("orderId".equals(key)) {
                orderId = Integer.parseInt(value);
            } else if ("customerId".equals(key)) {
                customerId = Integer.parseInt(value);
            } else if ("type".equals(key)) {
                type = value.substring(1, value.length() - 1); // Remove double quotes
            } else if ("date".equals(key)) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(value.substring(1, value.length() - 1)); // Remove double quotes
            } else if ("status".equals(key)) {
                status = value.substring(1, value.length() - 1); // Remove double quotes
            } else if ("totalAmount".equals(key)) {
                totalAmount = Double.parseDouble(value);
            }
        }

        return new Order(orderId, customerId, type, date, status, totalAmount);
    }


    public static Order loadFromJsonFile(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            return fromJson(json);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
