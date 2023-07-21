package view;

import controller.employeeController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class employeeView extends JFrame {
    private JTable empTable;
    private JPanel empPane;
    private JScrollPane empScroll;
    private DefaultTableModel tableModel;

    //employeeView
    public employeeView() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Employee View");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //initializeComponents
    private void initializeComponents() {
        empTable = new JTable();
        tableModel = new DefaultTableModel(0, 6);
        empTable.setModel(tableModel);
        empScroll = new JScrollPane(empTable);
        empPane = new JPanel(new BorderLayout());
        empPane.add(empScroll, BorderLayout.CENTER);
        setContentPane(empPane);
    }

    //displayEmployee
    public void displayEmployee(Object[][] data, String[] columns) {
        tableModel.setDataVector(data, columns);
    }

    //loadEmployeeFromController
    public void loadEmployeeFromController() {
        employeeController employeeController = new employeeController();
        Object[][] data = employeeController.viewFromJSON();
        String[] columns = {"Employee ID", "Name", "Profession", "Contact", "Email", "Work Status"};
        displayEmployee(data, columns);
    }

    //main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            employeeView employeeView = new employeeView();
            employeeView.loadEmployeeFromController();
        });
    }
}
