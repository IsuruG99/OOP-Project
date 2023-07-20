package view;

import controller.suppliersController;
import javax.swing.*;

public class AddSupplierForm extends JFrame {
    private JLabel lblAddSupplierID;
    private JTextField txtAddSupplierID;
    private JLabel lblAddName;
    private JTextField txtAddName;
    private JTextField txtAddProfession;
    private JLabel lblAddContact;
    private JTextField txtAddContact;
    private JLabel lblAddEmail;
    private JTextField txtAddEmail;
    private JButton addSupplierButton;
    private JLabel lblAddProfession;
    private JPanel addSupplierPane;

    public AddSupplierForm() {
        // do not dynamically create a new controller object
        // just use the one you already have

        suppliersController suppliersController = new suppliersController();

        addSupplierButton.addActionListener(e -> {
            String supplierIdStr = txtAddSupplierID.getText();
            int supplierId = Integer.parseInt(supplierIdStr);

            String name = txtAddName.getText();

            String profession = txtAddProfession.getText();

            String contactNumber = txtAddContact.getText();

            String email = txtAddEmail.getText();

            suppliersController.addToJSON(supplierId, name, profession, contactNumber, email);
            JOptionPane.showMessageDialog(addSupplierPane, "Supplier added successfully.");
        });
    }

    // display add supplier form (used in Main.java)
    public void displayAddSupplierForm() {
        JFrame frame = new JFrame("Add Supplier");
        frame.setContentPane(new AddSupplierForm().addSupplierPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddSupplierForm addSupplierForm = new AddSupplierForm();
            addSupplierForm.displayAddSupplierForm();
        });
    }

    // getters
    public JPanel getAddSupplierPane() {
        return addSupplierPane;
    }
}
