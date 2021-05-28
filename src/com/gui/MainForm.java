package com.gui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import com.data.clients.ClientInfo;
import com.data.clients.DBHandler;
import com.data.orders.OrderInfo;
import com.data.services.Serializer;
import com.data.services.ServiceInfo;

public class MainForm implements MenuListener {
    private JPanel panelMain;
    private JTable tableClients;
    private JMenuBar menuBar;
    JMenu menuOptions;

    private ArrayList<ClientInfo> clients;
    private ArrayList<ServiceInfo> services;
    private Serializer serializer;
    private DBHandler dbHandler;

    private String serviceFilePath = "services";
    private  String tableName = "clients";
    private String dbName = "oop_4sem";
    private String url = "jdbc:mysql://localhost:3306/oop_4sem";
    private String user = "root";
    private String password = "573458";

    public static void main(String[] args) {
        new MainForm();
        //OrderInfo order = new OrderInfo(new ServiceInfo("service", "service_type", 10),
        //        new ClientInfo("name", "name", "name"), LocalDate.now());
    }

    public MainForm() {
        try {
            serializer = new Serializer();
            services = (ArrayList<ServiceInfo>) serializer.load(serviceFilePath);
        } catch (Exception e) { }
        try {
            dbHandler = new DBHandler(dbName, tableName, url, user, password);
            clients = dbHandler.read();
        } catch (Exception e) { }

        JFrame frame = new JFrame("Химчистка");
        // frame.setContentPane(new MainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 480));

        frame.setJMenuBar(createMenuBar());
        ClientTableModel model = new ClientTableModel(clients);
        tableClients = new JTable(model);
        frame.add(new JScrollPane(tableClients));

        // frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        menuOptions.setMnemonic(KeyEvent.VK_O);
        // menu.getAccessibleContext().setAccessibleDescription("Options");
        JMenuItem menuItem = new JMenuItem("New order");
        menuOptions.add(menuItem);
        menuOptions.addMenuListener(this);


        menuBar.add(menuOptions);


        return menuBar;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource().equals(menuOptions)) {
            e.
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
