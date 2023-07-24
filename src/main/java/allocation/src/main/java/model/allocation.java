package model;

public class allocation {
    private int assignID;
    private int orderID;
    private int empID;
    private String empEmail;
    private String customerEmail;

    public allocation(int assignID, int orderID, int empID, String empEmail, String customerEmail) {
        this.assignID = assignID;
        this.orderID = orderID;
        this.empID = empID;
        this.empEmail = empEmail;
        this.customerEmail = customerEmail;
    }

    public int getAssignID() {
        return assignID;
    }
    public int getOrderID() {
        return orderID;
    }

    public int getEmpID() {
        return empID;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
