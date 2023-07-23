package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Order {
    private int orderId;
    private int customerId;
    private String email;
    private String type;
    private Date date;
    private String status;
    private double totalAmount;

    public Order(int orderId, int customerId, String email,String type, Date date, String status, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.email = email;
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

    public String getEmail() {
            return email;
        }

    public void setEmail(String email) {
            this.email = email;
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
}
