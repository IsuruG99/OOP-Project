package view;

import controller.OrderController;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {
    private JTable orderTable;
    private JScrollPane orderScroll;
    private JPanel orderPane;

    public OrderView() {
        setContentPane(orderPane);
        setTitle("Orders View");
        setSize(900, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // initializing Table
    private void createUIComponents() {
        //get data from controller
        Object[][] data = new OrderController().getAllOrders();
        String[] column = {"Order ID", "Customer ID", "Email", "Type", "Date", "Status", "Total Amount"};
        orderTable = new JTable(data, column);
        orderTable.setEnabled(false); // Set the table to be uneditable
        orderTable.setRowHeight(20);
        orderTable.setFont(orderTable.getFont().deriveFont(15f));
        orderTable.getTableHeader().setFont(orderTable.getFont().deriveFont(15f).deriveFont(Font.BOLD));
        orderScroll = new JScrollPane(orderTable);
    }

    public static void main(String[] args) {
        new OrderView();
    }
}
