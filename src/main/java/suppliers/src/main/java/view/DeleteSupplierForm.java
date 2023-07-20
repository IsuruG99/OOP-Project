package view;

import controller.suppliersController;
import javax.swing.*;

public class DeleteSupplierForm {
    private JLabel lblDeleteSupplierID;
    private JTextField txtDeleteSupplierID;
    private JButton deleteSupplierButton;
    private JPanel deleteSupplierPane;

    // do not dynamically create a new controller object
    // just use the one you already have
    //Create method DeleteSupplierForm
    public DeleteSupplierForm() {
        suppliersController suppliersController = new suppliersController();

        deleteSupplierButton.addActionListener(e -> {
            String supplierIdStr = txtDeleteSupplierID.getText();
            int supplierId = Integer.parseInt(supplierIdStr);


            //check return message from controller and if 1: success, 0: fail
            if(suppliersController.deleteFromJSON(supplierId) == 1){
                JOptionPane.showMessageDialog(deleteSupplierPane, "Supplier deleted successfully.");
            }else{
                JOptionPane.showMessageDialog(deleteSupplierPane, "Supplier not found.");
            }
        });
    }

    // display delete supplier form
    private void displayDeleteSupplierForm() {
        JFrame frame = new JFrame("Delete Supplier");
        frame.setContentPane(new DeleteSupplierForm().deleteSupplierPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteSupplierForm deleteSupplierForm = new DeleteSupplierForm();
            deleteSupplierForm.displayDeleteSupplierForm();
        });
    }
}
