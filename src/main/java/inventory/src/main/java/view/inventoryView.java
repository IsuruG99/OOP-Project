package view;

import controller.inventoryController;

import javax.swing.*;
import java.awt.*;

public class inventoryView extends JFrame{
    private JPanel inventoryPane;
    private JTable inventoryTable;
    private JScrollPane inventoryScroll;

    public inventoryView() {
        setContentPane(inventoryPane);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // initializing Table
    private void createUIComponents() {
        //Custom Create Table to View Inventory, the table is inside inventoryScroll
        String[] column ={"Item ID","Item Name","Item Price"};
        Object[][] data = new inventoryController().viewFromJSON(); // Get data from controller
        inventoryTable = new JTable(data, column); // Set rows and columns of the table to the data
        inventoryTable.setEnabled(false);// Set the table to be uneditable
        inventoryTable.setRowHeight(30);// Set the row height to 30
        inventoryTable.setFont(inventoryTable.getFont().deriveFont(15f));// Set the font size of the table content to 15
        inventoryTable.getTableHeader().setFont(inventoryTable.getFont().deriveFont(15f).deriveFont(Font.BOLD)); // Set the font size of the table header to 15 & bold
        inventoryScroll = new JScrollPane(inventoryTable); // Set the table to be scrollable
    }

    public static void main(String[] args) {
        new inventoryView();
    }
}
