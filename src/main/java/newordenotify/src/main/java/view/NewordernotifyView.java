package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewordernotifyView {
    private JPanel mainnotify;
    private JComboBox<String> Empselect;
    private JComboBox EmpIDView;
    private JButton GetID;

    private JButton SendEmail;
    private JLabel emp;
    private JLabel EmpID;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NewordernotifyView view = new NewordernotifyView();
            view.createAndShowGUI();
        });
    }

    public NewordernotifyView() {
        mainnotify = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Adjust gaps here
        mainnotify.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 10)); // Add margin (top, left, bottom, right)


        // First Row
        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        emp = new JLabel("Employee:");
        Empselect = new JComboBox<>();
        Empselect.setPreferredSize(new Dimension(200, 30));
        GetID = new JButton("Get ID");
        EmpID = new JLabel("Employee ID:");
        EmpIDView = new JComboBox();
        EmpIDView.setPreferredSize(new Dimension(200, 30));

        firstRowPanel.add(emp);
        firstRowPanel.add(Empselect);
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

        GetID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your action logic here
            }
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("NewordernotifyView");
        frame.setContentPane(mainnotify);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setMinimumSize(new Dimension(700, 500));

        frame.pack();
        frame.setVisible(true);
    }
}