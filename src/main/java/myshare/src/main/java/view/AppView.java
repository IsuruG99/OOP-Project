package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppView extends JFrame {
    private JTabbedPane mainPane;
    private JPanel orderPane;
    private JPanel inventoryPane;
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
    private JButton btnAllocateEmployees;

    public AppView() {
        setTitle("MyShare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Set the main panel as the content pane
        setContentPane(mainAppPanel);

        setVisible(true);

        // Customer Order buttons
        btnDisplayOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderView orderView = new OrderView();
                orderView.loadOrdersFromController();
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
                suppliersView supplierView = new suppliersView();
                supplierView.loadSuppliersFromController();
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppView appView = new AppView();
        });
    }
}
