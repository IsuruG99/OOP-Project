package view;

import controller.employeeController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class employeeView extends JFrame {
    private JTable empTable;
    private JPanel empPane;
    private JScrollPane empScroll;

    //employeeView
    public employeeView() {
        setContentPane(empPane);
        setTitle("Employee View");
        setSize(900, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createUIComponents() {
        employeeController employeeController = new employeeController();
        Object[][] data = employeeController.viewFromJSON();
        String[] columns = {"Employee ID", "Name", "Profession", "Contact", "Email", "Work Status"};
        empTable = new JTable(data, columns);
        empTable.setEnabled(false); // Set the table to be uneditable
        empTable.setRowHeight(20);
        empTable.setFont(empTable.getFont().deriveFont(15f));
        empTable.getTableHeader().setFont(empTable.getFont().deriveFont(15f).deriveFont(Font.BOLD));
        empScroll = new JScrollPane(empTable);
    }

    //main
    public static void main(String[] args) {
        new employeeView();
    }
}
