package view;

import controller.employeeController;

import javax.swing.*;
import java.util.Objects;

public class addEmployeeForm extends JFrame {
    private JLabel lblAddName;
    private JTextField txtAddName;
    private JLabel lblAddContact;
    private JTextField txtAddContact;
    private JLabel lblAddEmail;
    private JPanel addEmployeePane;
    private JLabel lblAddProfession;
    private JComboBox cbAddProfession;
    private JTextField txtAddEmail;
    private JButton btnAddEmployee;

    public addEmployeeForm() {
        btnAddEmployee.addActionListener(e -> {
            employeeController employeeController = new employeeController();
            String name = txtAddName.getText();
            String profession = Objects.requireNonNull(cbAddProfession.getSelectedItem()).toString();
            String contactNumber = txtAddContact.getText();
            String email = txtAddEmail.getText();
            employeeController.addToJSON(name, profession, contactNumber, email, "Available");
            JOptionPane.showMessageDialog(addEmployeePane, "Employee added successfully.");
        });
    }

    //display add employee form (used in Main.java)
    public void displayAddEmployeeForm() {
        JFrame frame = new JFrame("Add Employee");
        frame.setContentPane(new addEmployeeForm().addEmployeePane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            addEmployeeForm addEmployeeForm = new addEmployeeForm();
        });
    }
}
