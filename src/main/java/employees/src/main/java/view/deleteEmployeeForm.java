package view;

import controller.employeeController;

import javax.swing.*;

public class deleteEmployeeForm extends JFrame {
    private JTextField txtEmpID;
    private JButton btnDeleteEmployee;
    private JLabel lblEmpID;
    private JPanel deleteEmployeePane;

    public deleteEmployeeForm() {
        employeeController employeeController = new employeeController();
        btnDeleteEmployee.addActionListener(e -> {
            String empIDStr = txtEmpID.getText();
            int empID = Integer.parseInt(empIDStr);

            employeeController.deleteFromJSON(empID);
            JOptionPane.showMessageDialog(deleteEmployeePane, "Employee deleted successfully.");
        });
    }

    //display delete employee form (used in Main.java)
    public void displayDeleteEmployeeForm() {
        JFrame frame = new JFrame("Delete Employee");
        frame.setContentPane(new deleteEmployeeForm().deleteEmployeePane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            deleteEmployeeForm deleteEmployeeForm = new deleteEmployeeForm();
            deleteEmployeeForm.displayDeleteEmployeeForm();
        });
    }
}
