package view;

import controller.employeeController;

import javax.swing.*;

public class deleteEmployeeForm extends JFrame {
    private JTextField txtEmpID;
    private JButton btnDeleteEmployee;
    private JLabel lblEmpID;
    private JPanel deleteEmployeePane;
    private JLabel lblEmail;
    private JTextField txtProfession;
    private JTextField txtContact;
    private JTextField txtEmail;
    private JLabel lblContact;
    private JLabel lblProfession;
    private JLabel lblName;
    private JComboBox cbName;

    public deleteEmployeeForm() {
        employeeController employeeController = new employeeController();
        // populate fields
        populateFields();

        // delete employee from JSON file
        btnDeleteEmployee.addActionListener(e -> {
            // ask for confirmation before deleting employee
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }
            int empID = Integer.parseInt(txtEmpID.getText());

            // delete employee from JSON file
            employeeController.deleteFromJSON(empID);
            JOptionPane.showMessageDialog(null, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
    }

    //new method to populate fields
    private void populateFields() {
        employeeController employeeController = new employeeController();
        Object[][] data = employeeController.viewFromJSON();
        //populate all employee names in the combo box
        for (Object[] datum : data) {
            cbName.addItem(datum[1]);
        }
        // set the first employee name as default
        cbName.setSelectedIndex(0);
        txtEmpID.setText(data[0][0].toString());
        txtProfession.setText(data[0][2].toString());
        txtContact.setText(data[0][3].toString());
        txtEmail.setText(data[0][4].toString());
        //when employee name is selected from combo box, populate the other fields
        cbName.addActionListener(e1 -> {
            String name = cbName.getSelectedItem().toString();
            for (Object[] datum : data) {
                if (datum[1].equals(name)) {
                    txtEmpID.setText(datum[0].toString());
                    txtProfession.setText(datum[2].toString());
                    txtContact.setText(datum[3].toString());
                    txtEmail.setText(datum[4].toString());
                }
            }
        });
    }

    //display delete employee form (used in Main.java)
    public void displayDeleteEmployeeForm() {
        JFrame frame = new JFrame("Delete Employee");
        frame.setContentPane(new deleteEmployeeForm().deleteEmployeePane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
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
