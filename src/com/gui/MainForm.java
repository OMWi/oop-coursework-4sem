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
    private JTable tableOrders;
    private JTable tableServices;
    private JMenuBar menuBar;
    private JFrame frame;

    private ArrayList<ClientInfo> clients;
    private ArrayList<ServiceInfo> services;
    private ArrayList<OrderInfo> orders;
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
    }


    public MainForm() {
        //reading data
        try {
            serializer = new Serializer();
            services = (ArrayList<ServiceInfo>) serializer.load(serviceFilePath);
        } catch (Exception e) { }
        try {
            dbHandler = new DBHandler(dbName, tableName, url, user, password);
            clients = dbHandler.readClients();
            dbHandler.setTableName("orders");
            orders = dbHandler.readOrders();
        } catch (Exception e) { }

        //creating mainFrame
        frame = createMainFrame("Химчистка");
        frame.setJMenuBar(createMenuBar());
        addTables(frame);


        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if ("new order".equals(e.getActionCommand())) {
            JFrame orderFrame = createAddOrderFrame();
            orderFrame.setVisible(true);
        }
        else if ("new service".equals(e.getActionCommand())) {
            JFrame serviceFrame = createAddService();
            serviceFrame.setVisible(true);
        }
        else if ("new client".equals(e.getActionCommand())) {
            JFrame clientFrame = createAddClientFrame();
            clientFrame.setVisible(true);
        }
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

        JMenuItem newClientMenuItem = new JMenuItem("New client");
        newClientMenuItem.setActionCommand("new client");
        newClientMenuItem.addActionListener(this::actionPerformed);
        menuOptions.add(newClientMenuItem);

        menuBar.add(menuOptions);
        return menuBar;
    }

    public JFrame createMainFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 720));
        frame.setLocationRelativeTo(null);
        frame.pack();
        return frame;
    }

    public JFrame createAddClientFrame() {
        JFrame clientFrame = new JFrame();
        clientFrame.setResizable(false);
        clientFrame.setAlwaysOnTop(true);
        //clientFrame.setPreferredSize(new Dimension(400, 220));
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setLocationRelativeTo(frame);

        JPanel clientPanel = new JPanel();
        clientFrame.setContentPane(clientPanel);
        clientPanel.setLayout(new BorderLayout(0, 3));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 0, 2));

        inputPanel.add(new JLabel("First name", JLabel.CENTER));
        JTextField firstNameText = new JTextField();
        inputPanel.add(firstNameText);
        inputPanel.add(new JLabel("Second name", JLabel.CENTER));
        JTextField secondNameText = new JTextField();
        inputPanel.add(secondNameText);
        inputPanel.add(new JLabel("Third name", JLabel.CENTER));
        JTextField thirdNameText = new JTextField();
        inputPanel.add(thirdNameText);
        inputPanel.add(new JLabel("Visits", JLabel.CENTER));
        JTextField visitsText = new JTextField();
        inputPanel.add(visitsText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(320, 60));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //some code
            }
        });

        clientPanel.add(inputPanel, BorderLayout.NORTH);
        clientPanel.add(addButton, BorderLayout.SOUTH);
        clientFrame.pack();
        return clientFrame;
    }

    public JFrame createAddOrderFrame() {
        JFrame orderFrame = new JFrame();
        orderFrame.setResizable(false);
        orderFrame.setAlwaysOnTop(true);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setLocationRelativeTo(frame);

        JPanel orderPanel = new JPanel();
        orderFrame.setContentPane(orderPanel);
        orderPanel.setLayout(new BorderLayout(0, 3));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 0, 2));

        inputPanel.add(new JLabel("Client first name", JLabel.CENTER));
        JTextField firstNameText = new JTextField();
        inputPanel.add(firstNameText);
        inputPanel.add(new JLabel("Client second name", JLabel.CENTER));
        JTextField secondNameText = new JTextField();
        inputPanel.add(secondNameText);
        inputPanel.add(new JLabel("Service name", JLabel.CENTER));
        JTextField serviceNameText = new JTextField();
        inputPanel.add(serviceNameText);
        inputPanel.add(new JLabel("Receipt date", JLabel.CENTER));
        JTextField dateText = new JTextField();
        inputPanel.add(dateText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(320, 60));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //some code
            }
        });

        orderPanel.add(inputPanel, BorderLayout.NORTH);
        orderPanel.add(addButton, BorderLayout.SOUTH);
        orderFrame.pack();
        return orderFrame;
    }

    public JFrame createAddService() {
        JFrame serviceFrame = new JFrame();
        serviceFrame.setResizable(false);
        serviceFrame.setAlwaysOnTop(true);
        serviceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        serviceFrame.setLocationRelativeTo(frame);

        JPanel servicePanel = new JPanel();
        serviceFrame.setContentPane(servicePanel);
        servicePanel.setLayout(new BorderLayout(0, 3));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 0, 2));

        inputPanel.add(new JLabel("Service type", JLabel.CENTER));
        JTextField typeText = new JTextField();
        inputPanel.add(typeText);
        inputPanel.add(new JLabel("Service name", JLabel.CENTER));
        JTextField nameText = new JTextField();
        inputPanel.add(nameText);
        inputPanel.add(new JLabel("Service name", JLabel.CENTER));
        JTextField priceText = new JTextField();
        inputPanel.add(priceText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(320, 60));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //some code
            }
        });

        servicePanel.add(inputPanel, BorderLayout.NORTH);
        servicePanel.add(addButton, BorderLayout.SOUTH);
        serviceFrame.pack();
        return serviceFrame;
    }

    public void addTables(JFrame frame) {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelClients = new JPanel();
        panelClients.setLayout(new GridLayout(1, 1));
        JScrollPane scrollPaneClients = new JScrollPane();
        ClientTableModel modelClients = new ClientTableModel(clients);
        tableClients = new JTable(modelClients);
        scrollPaneClients.setViewportView(tableClients);
        panelClients.add(scrollPaneClients);
        tabbedPane.addTab("Clients", panelClients);

        JPanel panelOrders = new JPanel();
        panelOrders.setLayout(new GridLayout(1, 1));
        JScrollPane scrollPaneOrders = new JScrollPane();
        OrderTableModel modelOrders = new OrderTableModel(orders);
        tableOrders = new JTable(modelOrders);
        scrollPaneOrders.setViewportView(tableOrders);
        panelOrders.add(scrollPaneOrders);
        tabbedPane.addTab("Orders", panelOrders);

        JPanel panelServices = new JPanel();
        panelServices.setLayout(new GridLayout(1, 1));
        JScrollPane scrollPaneServices = new JScrollPane();
        OrderTableModel modelServices = new OrderTableModel(orders);
        tableServices = new JTable(modelServices);
        scrollPaneServices.setViewportView(tableServices);
        panelServices.add(scrollPaneServices);
        tabbedPane.addTab("Orders", panelServices);

        frame.add(tabbedPane, BorderLayout.CENTER);
    }

}
