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
    private JTextField txtName;
    private JComboBox cbEmpID;
    private JButton btnUpdateEmployee;
    private JComboBox cbProfession;
    private JLabel lblStatus;
    private JComboBox cbStatus;
    private JComboBox cbName;

    public deleteEmployeeForm() {
        setTitle("Delete Employee Form");
        setContentPane(deleteEmployeePane);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        employeeController employeeController = new employeeController();
        // populate fields
        populateFields();

        btnUpdateEmployee.addActionListener(e -> {
            // ask for confirmation before deleting employee
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this employee?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }
            int empID = Integer.parseInt(cbEmpID.getSelectedItem().toString());
            String name = txtName.getText();
            String profession = cbProfession.getSelectedItem().toString();
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            String status = cbStatus.getSelectedItem().toString();

            // update employee in JSON file
            employeeController.updateInJSON(empID, name, profession, contact, email, status);
            JOptionPane.showMessageDialog(deleteEmployeePane, "Employee updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deleteEmployeePane);
            frame.dispose();
        });

        // delete employee from JSON file
        btnDeleteEmployee.addActionListener(e -> {
            // ask for confirmation before deleting employee
            int dialogResult = JOptionPane.showConfirmDialog(deleteEmployeePane, "Are you sure you want to delete this employee?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }
            int empID = Integer.parseInt(txtEmpID.getText());

            // delete employee from JSON file
            employeeController.deleteFromJSON(empID);
            JOptionPane.showMessageDialog(deleteEmployeePane, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deleteEmployeePane);
            frame.dispose();
        });
    }

    //new method to populate fields
    private void populateFields() {
        employeeController employeeController = new employeeController();
        Object[][] data = employeeController.viewFromJSON();
        //populate all employee names in the combo box
        for (Object[] datum : data) {
            cbEmpID.addItem(datum[0]);
            if (txtName.getText().isEmpty()){
                txtName.setText(datum[1].toString());
                cbProfession.setSelectedItem(datum[2].toString());
                txtContact.setText(datum[3].toString());
                txtEmail.setText(datum[4].toString());
                cbStatus.setSelectedItem(datum[5].toString());
            }
        }
        //when employee name is selected from combo box, populate the other fields
        cbEmpID.addActionListener(e1 -> {
            int empID = Integer.parseInt(cbEmpID.getSelectedItem().toString());
            for (Object[] datum : data) {
                if (datum[0].equals(empID)) {
                    txtName.setText(datum[1].toString());
                    cbProfession.setSelectedItem(datum[2].toString());
                    txtContact.setText(datum[3].toString());
                    txtEmail.setText(datum[4].toString());
                    cbStatus.setSelectedItem(datum[5].toString());
                }
            }
        });
    }

    public static void main(String[] args) {
        new deleteEmployeeForm();
    }
}
