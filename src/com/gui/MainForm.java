package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.config.ConfigModel;
import com.config.ConfigProvider;
import com.data.ClientInfo;
import com.data.DBHandler;
import com.data.OrderInfo;
import com.data.ServiceInfo;



public class MainForm {
    private JPanel panelMain;
    private JTable tableClients;
    private ClientTableModel modelClients;
    private JTable tableOrders;
    private OrderTableModel modelOrders;
    private JTable tableServices;
    private ServiceTableModel modelServices;
    private JMenuBar menuBar;
    private JFrame frame;

    private ArrayList<ClientInfo> clients;
    private ArrayList<ServiceInfo> services;
    private ArrayList<OrderInfo> orders;
    private DBHandler dbHandler;

    private String configPath = "config";

    public static void main(String[] args) {
        new MainForm();
    }

    public MainForm() {
        ConfigProvider configProvider = new ConfigProvider(configPath);
        ConfigModel config;
        try {
            config = (ConfigModel) configProvider.load();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load configuration file");
            return;
        }

        try {
            dbHandler = new DBHandler(config.getUrl(), config.getUser(), config.getPassword());
            clients = dbHandler.readClients();
            orders = dbHandler.readOrders();
            services = dbHandler.readServices();
        } catch (Exception e) { }

        frame = createMainFrame("Химчистка");
        frame.setJMenuBar(createMenuBar());
        frame.add(createTabbedPane());
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
        JMenu menuNew = new JMenu("New");
        //menuNew.setMnemonic(KeyEvent.VK_N);
        JMenuItem newOrderMenuItem = new JMenuItem("New order...");
        newOrderMenuItem.setActionCommand("new order");
        newOrderMenuItem.addActionListener(this::actionPerformed);
        menuNew.add(newOrderMenuItem);

        JMenuItem newServiceMenuItem = new JMenuItem("New service...");
        newServiceMenuItem.setActionCommand("new service");
        newServiceMenuItem.addActionListener(this::actionPerformed);
        menuNew.add(newServiceMenuItem);

        JMenuItem newClientMenuItem = new JMenuItem("New client");
        newClientMenuItem.setActionCommand("new client");
        newClientMenuItem.addActionListener(this::actionPerformed);
        menuNew.add(newClientMenuItem);

        JMenu menuDelete = new JMenu("Delete");
        //menuDelete.setMnemonic(KeyEvent.VK_D);
        JMenuItem deleteOrderMenuItem = new JMenuItem("Delete order...");
        deleteOrderMenuItem.setActionCommand("delete order");
        deleteOrderMenuItem.addActionListener(this::actionPerformed);
        menuDelete.add(deleteOrderMenuItem);

        JMenuItem deleteServiceMenuItem = new JMenuItem("Delete service...");
        deleteServiceMenuItem.setActionCommand("delete service");
        deleteServiceMenuItem.addActionListener(this::actionPerformed);
        menuDelete.add(deleteServiceMenuItem);

        JMenuItem deleteClientMenuItem = new JMenuItem("Delete client");
        deleteClientMenuItem.setActionCommand("delete client");
        deleteClientMenuItem.addActionListener(this::actionPerformed);
        menuDelete.add(deleteClientMenuItem);

        menuBar.add(menuNew);
        menuBar.add(menuDelete);
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
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setLocationRelativeTo(frame);

        JPanel clientPanel = new JPanel();
        clientFrame.setContentPane(clientPanel);
        clientPanel.setLayout(new BorderLayout(0, 3));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 0, 2));

        inputPanel.add(new JLabel("First name", JLabel.CENTER));
        JTextField firstNameText = new JTextField();
        inputPanel.add(firstNameText);
        inputPanel.add(new JLabel("Second name", JLabel.CENTER));
        JTextField secondNameText = new JTextField();
        inputPanel.add(secondNameText);
        inputPanel.add(new JLabel("Third name", JLabel.CENTER));
        JTextField thirdNameText = new JTextField();
        inputPanel.add(thirdNameText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(320, 60));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean dataCorrect = !firstNameText.getText().isEmpty() && !secondNameText.getText().isEmpty() && !thirdNameText.getText().isEmpty();
                if (dataCorrect) {
                    ClientInfo newClient = new ClientInfo(firstNameText.getText(), secondNameText.getText(), thirdNameText.getText());
                    clients.add(newClient);
                    try {
                        dbHandler.addClient(newClient);
                    } catch (Exception dbExc) { }
                    modelClients.fireTableDataChanged();
                }
                else {
                    JOptionPane.showMessageDialog(clientFrame, "Bad input");
                }
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
        inputPanel.add(new JLabel("Service price", JLabel.CENTER));
        JTextField priceText = new JTextField();
        inputPanel.add(priceText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(320, 60));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean dataCorrect = !typeText.getText().isEmpty() && !nameText.getText().isEmpty() && !priceText.getText().isEmpty();
                if (dataCorrect) {
                    try {
                        String text = priceText.getText();
                        text = text.replace(",", ".");
                        double price = Double.parseDouble(text);

                        ServiceInfo newService = new ServiceInfo(typeText.getText(), nameText.getText(), price);
                        services.add(newService);
                        dbHandler.addService(newService);
                        modelServices.fireTableDataChanged();
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(serviceFrame, "Wrong price");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(serviceFrame, "Bad input");
                }
            }
        });

        servicePanel.add(inputPanel, BorderLayout.NORTH);
        servicePanel.add(addButton, BorderLayout.SOUTH);
        serviceFrame.pack();
        return serviceFrame;
    }

    public JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);

        JPanel panelClients = new JPanel();
        panelClients.setLayout(new GridLayout());
        JScrollPane scrollPaneClients = new JScrollPane();
        modelClients = new ClientTableModel(clients);

        tableClients = new JTable(modelClients);
        tableClients.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        scrollPaneClients.setViewportView(tableClients);
        panelClients.add(scrollPaneClients);
        tabbedPane.addTab("Clients", panelClients);

        JPanel panelOrders = new JPanel();
        panelOrders.setLayout(new GridLayout());
        JScrollPane scrollPaneOrders = new JScrollPane();
        modelOrders = new OrderTableModel(orders);
        tableOrders = new JTable(modelOrders);
        tableOrders.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        scrollPaneOrders.setViewportView(tableOrders);
        panelOrders.add(scrollPaneOrders);
        tabbedPane.addTab("Orders", panelOrders);

        JPanel panelServices = new JPanel();
        panelServices.setLayout(new GridLayout());
        JScrollPane scrollPaneServices = new JScrollPane();
        modelServices = new ServiceTableModel(services);
        tableServices = new JTable(modelServices);
        tableServices.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        scrollPaneServices.setViewportView(tableServices);
        panelServices.add(scrollPaneServices);
        tabbedPane.addTab("Services", panelServices);

        return tabbedPane;
    }

}
