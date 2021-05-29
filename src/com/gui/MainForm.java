package com.gui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import com.data.clients.ClientInfo;
import com.data.clients.DBHandler;
import com.data.orders.OrderInfo;
import com.data.services.Serializer;
import com.data.services.ServiceInfo;



public class MainForm {
    private JPanel panelMain;
    private JDesktopPane desktopPane;
    private JTable tableClients;
    private JMenuBar menuBar;
    private JFrame frame;

    private ArrayList<ClientInfo> clients;
    private ArrayList<ServiceInfo> services;
    private Serializer serializer;
    private DBHandler dbHandler;

    private String serviceFilePath = "services";
    private String tableName = "clients";
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
        } catch (Exception e) {
        }
        try {
            dbHandler = new DBHandler(dbName, tableName, url, user, password);
            clients = dbHandler.read();
        } catch (Exception e) {
        }
        frame = new JFrame("Химчистка");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 720));

        frame.setJMenuBar(createMenuBar());
        ClientTableModel model = new ClientTableModel(clients);
        tableClients = new JTable(model);
        desktopPane = new JDesktopPane();
        desktopPane.add(new JScrollPane(tableClients));
        frame.add(desktopPane);

        // frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JMenuBar createMenuBar() {
        // todo: add mnemonics
        menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        //menuOptions.setMnemonic(KeyEvent.VK_O);
        // menu.getAccessibleContext().setAccessibleDescription("Options");
        JMenuItem newOrderMenuItem = new JMenuItem("New order...");
        newOrderMenuItem.setActionCommand("new order");
        newOrderMenuItem.addActionListener(this::actionPerformed);
        menuOptions.add(newOrderMenuItem);

        JMenuItem newServiceMenuItem = new JMenuItem("New service...");
        newServiceMenuItem.setActionCommand("new service");
        newServiceMenuItem.addActionListener(this::actionPerformed);
        menuOptions.add(newServiceMenuItem);

        menuBar.add(menuOptions);
        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        if ("new order".equals(e.getActionCommand())) {

        }
        else if ("new service".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "new service");
        }
    }


}
