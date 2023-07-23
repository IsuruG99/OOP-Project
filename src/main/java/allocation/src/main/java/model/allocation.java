package model;

public class allocation {
    private int orderID;
    private int empID;
    private String empEmail;
    private String customerEmail;

    public allocation(int orderID, int empID, String empEmail, String customerEmail) {
        this.orderID = orderID;
        this.empID = empID;
        this.empEmail = empEmail;
        this.customerEmail = customerEmail;
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
