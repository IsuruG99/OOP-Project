package controller;
import controller.OrderController;


public class reportController {

    public double getTotalIncome() {
        Object[][] data = new OrderController().getAllOrders();

        double totalIncome = 0;
        for (Object[] datum : data) {
            if (datum[5].equals("Completed")) {
                totalIncome += Double.parseDouble(datum[6].toString());
            }
        }
        return totalIncome;
    }

    public double getTotalExpense() {
        Object[][] data = new OrderController().getAllOrders();

        double totalExpense = 0;
        //expenses is taken as 20% of total payment of each order
        for (Object[] datum : data) {
            if (datum[5].equals("Completed")) {
                totalExpense += Double.parseDouble(datum[6].toString()) * 0.2;
            }
        }
        return totalExpense;
    }

    public int getTotalOrders() {
        Object[][] data = new OrderController().getAllOrders();

        int totalOrders = 0;
        for (Object[] datum : data) {
            if (datum[5].equals("Completed")) {
                totalOrders++;
            }
        }
        return totalOrders;
    }
}
