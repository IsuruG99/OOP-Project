package view;

import controller.NewordernotifyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class NewordernotifyView extends JFrame {
    private JPanel mainnotify;
    private JComboBox<String> profession;
    private JComboBox<Integer> EmpIDView;
    private JButton GetID;
    private JButton SendEmail;
    private JLabel emp;
    private JLabel EmpID;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NewordernotifyView newOrderNotifyView = new NewordernotifyView();
            newOrderNotifyView.displayNewordernotify();
        });
    }

    public NewordernotifyView() {
        NewordernotifyController newOrderNotifyController = new NewordernotifyController();
        Object[][] data = newOrderNotifyController.getFromJSON();
        profession = new JComboBox<>();
        EmpIDView = new JComboBox<>();
        EmpIDView.setPreferredSize(new Dimension(200, 30));

        // Use a Set to keep track of unique professions
        Set<String> uniqueProfessions = new HashSet<>();

        // Load unique professions from data to profession JComboBox
        for (Object[] employeeData : data) {
            String professionStr = (String) employeeData[2]; // Using index 2 for profession
            uniqueProfessions.add(professionStr);
        }

        // Add unique professions to the profession JComboBox
        for (String professionStr : uniqueProfessions) {
            profession.addItem(professionStr);
        }

        mainnotify = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        mainnotify.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 10));

        // First Row
        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        emp = new JLabel("Employee:");
        GetID = new JButton("Get ID");
        EmpID = new JLabel("Employee ID:");

        firstRowPanel.add(emp);
        firstRowPanel.add(profession);
        firstRowPanel.add(GetID);
        firstRowPanel.add(EmpID);
        firstRowPanel.add(EmpIDView);

        // Second Row
        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        SendEmail = new JButton("Send Email");

        secondRowPanel.add(SendEmail);

        // Add both rows to the main panel
        mainnotify.add(firstRowPanel);
        mainnotify.add(secondRowPanel);

        // ActionListener for profession select JComboBox
        profession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProfession = (String) profession.getSelectedItem();
                EmpIDView.removeAllItems(); // Clear previous items

                // Add employee IDs with the selected profession to EmpIDView
                for (Object[] employeeData : data) {
                    String professionStr = (String) employeeData[2]; // Using index 2 for profession
                    if (employeeData[1].toString().equals(selectedProfession)) {
                        int employeeID = (int) employeeData[0];
                        EmpIDView.addItem(employeeID);
                    }
                }
            }
        });

        // ActionListener for GetID button
        GetID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProfession = (String) profession.getSelectedItem();
                EmpIDView.removeAllItems(); // Clear previous items

                // Add employee IDs (empId) with the selected profession to EmpIDView JComboBox
                for (Object[] employeeData : data) {
                    String profession = (String) employeeData[2]; // Using index 2 for profession
                    if (profession.equals(selectedProfession)) {
                        int employeeID = (int) employeeData[0]; // Using index 0 for empId
                        EmpIDView.addItem(employeeID);
                    }
                }
            }
        });

        // ActionListener for SendEmail button
        SendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the SendEmail button is clicked
                JOptionPane.showMessageDialog(SendEmail, "Notification sent");
            }
        });
    }

    public void displayNewordernotify() {
        JFrame frame = new JFrame("New Order Notification");
        frame.setContentPane(this.mainnotify);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
