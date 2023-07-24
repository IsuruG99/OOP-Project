package view;

import controller.suppliersController;
import javax.swing.*;

public class deleteSupplierForm extends JFrame{
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
    private JComboBox cbName;

    // do not dynamically create a new controller object
    // just use the one you already have
    // Created method DeleteSupplierForm
    public deleteSupplierForm() {
        setTitle("Delete Supplier Form");
        setContentPane(deleteSupplierPane);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        suppliersController suppliersController = new suppliersController();
        //new method to populate fields
        populateFields();

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
                System.exit(0);
            }else{
                //error message
                JOptionPane.showMessageDialog(deleteSupplierPane, "Invalid supplier ID.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        });
    }

    private void populateFields() {
        suppliersController suppliersController = new suppliersController();
        Object[][] data = suppliersController.viewFromJSON();
        //populate all supplier names in the combo box
        for (Object[] datum : data) {
            cbName.addItem(datum[1]);
        }
        // action listener for combo box
        cbName.addActionListener(e -> {
            String name = cbName.getSelectedItem().toString();
            for (Object[] datum : data) {
                if (datum[1].toString().equals(name)) {
                    txtDeleteSupplierID.setText(datum[0].toString());
                    txtProfession.setText(datum[2].toString());
                    txtDeleteContact.setText(datum[3].toString());
                    txtAddEmail.setText(datum[4].toString());
                }
            }
        });
    }

    // Main method
    public static void main(String[] args) {
        new deleteSupplierForm();
    }
}
