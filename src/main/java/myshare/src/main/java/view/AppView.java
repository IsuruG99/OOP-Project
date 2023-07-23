package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppView extends JFrame {
    private JTabbedPane mainPane;
    private JPanel orderPane;
    private JPanel supplierPane;
    private JPanel mainAppPanel;
    private JButton btnAddOrder;
    private JButton btnDisplayOrders;
    private JButton btnRemoveOrder;
    private JButton btnAddSupplier;
    private JButton btnDisplaySupplier;
    private JButton btnDeleteSupplier;
    private JLabel lblHeader;
    private JLabel lblSubtitle;
    private JButton btnAddEmployees;
    private JButton btnViewEmployees;
    private JButton btnRemoveEmployees;
    private JButton btnDeallocateEmployees;
    private JButton btnViewInventory;
    private JPanel inventoryPane;
    private JPanel employeePane;
    private JPanel notifiyPane;
    private JPanel homePane;
    private JPanel allocationPane;
    private JButton btnAllocateEmployees;

    public AppView() {
        setTitle("MyShare Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        // Set the main panel as the content pane
        setContentPane(mainAppPanel);
        setVisible(true);

        // Customer Order buttons
        btnDisplayOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderView();
            }
        });
        btnRemoveOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteOrderForm deleteOrderForm = new DeleteOrderForm();
                deleteOrderForm.displayForm();
            }
        });
        btnAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrderForm addOrderForm = new AddOrderForm();
                addOrderForm.displayForm();
            }
        });

        // Supplier buttons
        btnAddSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSupplierForm addSupplierForm = new AddSupplierForm();
                addSupplierForm.displayAddSupplierForm();
            }
        });
        btnDisplaySupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new suppliersView();
            }
        });
        btnDeleteSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteSupplierForm deleteSupplierForm = new DeleteSupplierForm();
                deleteSupplierForm.displayDeleteSupplierForm();
            }
        });

        // Employee buttons
        btnAddEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeForm addEmployeeForm = new addEmployeeForm();
                addEmployeeForm.displayAddEmployeeForm();
            }
        });
        btnViewEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeView employeeView = new employeeView();
                employeeView.loadEmployeeFromController();
            }
        });
        btnRemoveEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployeeForm deleteEmployeeForm = new deleteEmployeeForm();
                deleteEmployeeForm.displayDeleteEmployeeForm();
            }
        });

        // Inventory buttons
        btnViewInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new inventoryView();
            }
        });

        // Allocation buttons
        btnAllocateEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new allocationView();
            }
        });
        btnDeallocateEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deallocationView();
            }
        });
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppView appView = new AppView();
        });
    }
}
