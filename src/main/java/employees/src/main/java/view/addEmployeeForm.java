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
        setTitle("Add Employee Form");
        setContentPane(addEmployeePane);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnAddEmployee.addActionListener(e -> {
            employeeController employeeController = new employeeController();
            String name = txtAddName.getText();
            String profession = Objects.requireNonNull(cbAddProfession.getSelectedItem()).toString();
            String contactNumber = txtAddContact.getText();
            String email = txtAddEmail.getText();
            // check if any field is empty
            if (name.isEmpty() || profession.isEmpty() || contactNumber.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                employeeController.addToJSON(name, profession, contactNumber, email, "Available");
                JOptionPane.showMessageDialog(null, "Added employee successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                txtAddName.setText("");
                txtAddContact.setText("");
                txtAddEmail.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new addEmployeeForm();
    }
}
