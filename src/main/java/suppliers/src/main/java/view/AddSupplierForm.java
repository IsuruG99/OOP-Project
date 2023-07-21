package view;

import controller.suppliersController;
import javax.swing.*;
import java.util.Objects;

public class AddSupplierForm extends JFrame {
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
    private JComboBox cbAddProfession;

    public AddSupplierForm() {
        // do not dynamically create a new controller object
        // just use the one you already have

        suppliersController suppliersController = new suppliersController();

        addSupplierButton.addActionListener(e -> {
            String name = txtAddName.getText();

            String profession = Objects.requireNonNull(cbAddProfession.getSelectedItem()).toString();

            String contactNumber = txtAddContact.getText();

            String email = txtAddEmail.getText();

            suppliersController.addToJSON(name, profession, contactNumber, email);
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
