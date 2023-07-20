package view;

import controller.suppliersController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class suppliersView extends JFrame {
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private JPanel supplierPane;
    private JScrollPane supplierScroll;

    // Constructor
    public suppliersView() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Suppliers View");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        supplierTable = new JTable();
        tableModel = new DefaultTableModel(0, 5);
        supplierTable.setModel(tableModel);
        supplierScroll = new JScrollPane(supplierTable);
        supplierPane = new JPanel(new BorderLayout());
        supplierPane.add(supplierScroll, BorderLayout.CENTER);
        setContentPane(supplierPane);
    }
    // Display suppliers
    public void displaySuppliers(Object[][] data, String[] columns) {
        tableModel.setDataVector(data, columns);
    }
    // Load suppliers from controller
    public void loadSuppliersFromController() {
        suppliersController suppliersController = new suppliersController(); // Load suppliers from JSON file using suppliersController
        Object[][] data = suppliersController.viewFromJSON();
        String[] columns = {"Supplier ID", "Name", "Profession", "Contact Number", "Email"};
        displaySuppliers(data, columns);
    }
    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            suppliersView suppliersView = new suppliersView();
            suppliersView.loadSuppliersFromController();
        });
    }
}
