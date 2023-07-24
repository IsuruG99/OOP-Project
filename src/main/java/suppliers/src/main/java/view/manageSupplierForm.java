package view;

import controller.suppliersController;
import javax.swing.*;

public class manageSupplierForm extends JFrame{
    private JLabel lblDeleteSupplierID;
    private JTextField txtDeleteSupplierID;
    private JButton btnDeleteSupplier;
    private JPanel deleteSupplierPane;
    private JLabel lblDeleteName;
    private JLabel lblDeleteContact;
    private JTextField txtDeleteContact;
    private JLabel lblAddEmail;
    private JTextField txtAddEmail;
    private JLabel lblAddProfession;
    private JTextField txtProfession;
    private JTextField txtName;
    private JComboBox cbSpID;
    private JComboBox cbProfession;
    private JButton btnUpdateSupplier;
    private JComboBox cbName;

    // do not dynamically create a new controller object
    // just use the one you already have
    // Created method DeleteSupplierForm
    public manageSupplierForm() {
        setTitle("Manage Supplier Form");
        setContentPane(deleteSupplierPane);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        suppliersController suppliersController = new suppliersController();
        //new method to populate fields
        populateFields();

        btnUpdateSupplier.addActionListener(e -> {
            // ask for confirmation before deleting supplier
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this supplier?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }
            int supplierId = Integer.parseInt(cbSpID.getSelectedItem().toString());
            String name = txtName.getText();
            String profession = cbProfession.getSelectedItem().toString();
            String contact = txtDeleteContact.getText();
            String email = txtAddEmail.getText();

            // update supplier in JSON file
            suppliersController.updateInJSON(supplierId, name, profession, contact, email);
            JOptionPane.showMessageDialog(deleteSupplierPane, "Supplier updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deleteSupplierPane);
            frame.dispose();
        });

        btnDeleteSupplier.addActionListener(e -> {
            // ask for confirmation before deleting supplier
            int dialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this supplier?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialog == JOptionPane.NO_OPTION) {
                return;
            }
            int supplierId = Integer.parseInt(txtDeleteSupplierID.getText());

            //check return message from controller and if 1: success, 0: fail
            if(suppliersController.deleteFromJSON(supplierId) == 1){
                JOptionPane.showMessageDialog(deleteSupplierPane, "Supplier deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                //quit the form
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deleteSupplierPane);
                frame.dispose();
            }else{
                //error message
                JOptionPane.showMessageDialog(deleteSupplierPane, "Invalid supplier ID.", "Error", JOptionPane.ERROR_MESSAGE);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(deleteSupplierPane);
                frame.dispose();
            }
        });
    }

    private void populateFields() {
        suppliersController suppliersController = new suppliersController();
        Object[][] data = suppliersController.viewFromJSON();
        //populate all supplier names in the combo box
        for (Object[] datum : data) {
            cbSpID.addItem(datum[0]);
            if (txtName.getText().isEmpty()) {
                txtName.setText(datum[1].toString());
                cbProfession.setSelectedItem(datum[2].toString());
                txtDeleteContact.setText(datum[3].toString());
                txtAddEmail.setText(datum[4].toString());
            }
        }
        // action listener for combo box
        cbSpID.addActionListener(e -> {
            int spID = Integer.parseInt(cbSpID.getSelectedItem().toString());
            for (Object[] datum : data) {
                if (datum[0].toString().equals(spID)) {
                    txtName.setText(datum[1].toString());
                    cbProfession.setSelectedItem(datum[2].toString());
                    txtDeleteContact.setText(datum[3].toString());
                    txtAddEmail.setText(datum[4].toString());
                }
            }
        });
    }

    // Main method
    public static void main(String[] args) {
        new manageSupplierForm();
    }
}
