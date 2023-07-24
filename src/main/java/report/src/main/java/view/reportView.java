package view;

import controller.reportController;

import javax.swing.*;

public class reportView extends JFrame{
    private JPanel reportPane;
    private JButton btnGenerate;
    private JLabel lblIncome;
    private JTextField txtIncome;
    private JLabel lblOrderCount;
    private JTextField txtOrderCount;
    private JTextField txtExpense;
    private JTextField txtProfit;
    private JLabel lblProfit;
    private JLabel lblExpense;
    private JLabel lblReportHeader;

    public reportView() {
        setTitle("Monthly Report");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        // Set the main panel as the content pane
        setContentPane(reportPane);
        setVisible(true);
        lblReportHeader.setVisible(false);
        lblExpense.setVisible(false);
        txtExpense.setVisible(false);
        lblProfit.setVisible(false);
        txtProfit.setVisible(false);
        lblIncome.setVisible(false);
        txtIncome.setVisible(false);
        lblOrderCount.setVisible(false);
        txtOrderCount.setVisible(false);

        // Report Button click should display the Report & Hide the Button
        btnGenerate.addActionListener(e -> {
            // Create an instance of the controller
            reportController reportControl = new reportController();
            // get Total Income, add Rs. sign
            txtIncome.setText("Rs. " + reportControl.getTotalIncome());
            // get Total Expense and price format it, add Rs. sign
            txtExpense.setText("Rs. " + reportControl.getTotalExpense());
            // get Total Profit by just subtracting Expense from Income and add Rs. sign
            txtProfit.setText("Rs. " + (reportControl.getTotalIncome() - reportControl.getTotalExpense()));
            // get Total Orders, output is an int
            txtOrderCount.setText(String.valueOf(reportControl.getTotalOrders()));

            lblReportHeader.setVisible(true);
            lblExpense.setVisible(true);
            txtExpense.setVisible(true);
            lblProfit.setVisible(true);
            txtProfit.setVisible(true);
            lblIncome.setVisible(true);
            txtIncome.setVisible(true);
            lblOrderCount.setVisible(true);
            txtOrderCount.setVisible(true);
            btnGenerate.setVisible(false);
        });
    }

    public static void main(String[] args) {
        new reportView();
    }
}
