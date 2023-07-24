package view;

import controller.suppliersController;

import javax.swing.*;
import java.awt.*;

public class suppliersView extends JFrame {
    private JTable supplierTable;
    private JPanel supplierPane;
    private JScrollPane supplierScroll;

    // Constructor
    public suppliersView() {
        setContentPane(supplierPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Suppliers View");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        Object[][] data = new suppliersController().viewFromJSON();
        String[] columns = {"Supplier ID", "Name", "Profession", "Contact Number", "Email"};
        supplierTable = new JTable(data, columns);
        supplierTable.setEnabled(false); // Set the table to be uneditable
        supplierTable.setRowHeight(20);
        supplierTable.setFont(supplierTable.getFont().deriveFont(15f));
        supplierTable.getTableHeader().setFont(supplierTable.getFont().deriveFont(15f).deriveFont(Font.BOLD));
        supplierScroll = new JScrollPane(supplierTable);
    }

    // Main method
    public static void main(String[] args) {
        new suppliersView();
    }


}
