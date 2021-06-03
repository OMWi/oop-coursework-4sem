package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalDate;
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
            JOptionPane.showMessageDialog(null, "Не удалось загрузить конфигурационный файл");
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
        else if ("return order".equals(e.getActionCommand())) {
            String input = JOptionPane.showInputDialog("Введите id заказа:");
            try {
                int id = Integer.parseInt(input);
                boolean isFound = false;
                for (OrderInfo elem : orders) {
                    if (elem.getID() == id) {
                        isFound = true;
                        elem.setReturnDate(Date.valueOf(LocalDate.now()));
                        dbHandler.updateOrderReturnDate(elem.getID(), Date.valueOf(LocalDate.now()));
                        modelOrders.fireTableDataChanged();
                        break;
                    }
                }
                if (!isFound) {
                    throw new Exception();
                }
            }
            catch (Exception kekwException) {
                JOptionPane.showMessageDialog(frame, "Заказ с таким id не найден");
            }
        }
    }

    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        JMenu menuNew = new JMenu("Добавить");
        menuNew.setFont(new Font("Arial", Font.BOLD, 15));
        //menuNew.setMnemonic(KeyEvent.VK_N);

        JMenuItem newClientMenuItem = new JMenuItem("Добавить клиента");
        newClientMenuItem.setFont(new Font("Arial", Font.BOLD, 15));
        newClientMenuItem.setActionCommand("new client");
        newClientMenuItem.addActionListener(this::actionPerformed);
        menuNew.add(newClientMenuItem);

        JMenuItem newServiceMenuItem = new JMenuItem("Добавить услугу");
        newServiceMenuItem.setFont(new Font("Arial", Font.BOLD, 15));
        newServiceMenuItem.setActionCommand("new service");
        newServiceMenuItem.addActionListener(this::actionPerformed);
        menuNew.add(newServiceMenuItem);

        JMenuItem newOrderMenuItem = new JMenuItem("Добавить заказ");
        newOrderMenuItem.setFont(new Font("Arial", Font.BOLD, 15));
        newOrderMenuItem.setActionCommand("new order");
        newOrderMenuItem.addActionListener(this::actionPerformed);
        menuNew.add(newOrderMenuItem);

        JMenu menuEdit = new JMenu("Изменить");
        menuEdit.setFont(new Font("Arial", Font.BOLD, 15));

        JMenuItem returnOrderItem = new JMenuItem("Выставить дату возврата заказа");
        returnOrderItem.setFont(new Font("Arial", Font.BOLD, 15));
        returnOrderItem.setActionCommand("return order");
        returnOrderItem.addActionListener(this::actionPerformed);
        menuEdit.add(returnOrderItem);

        menuBar.add(menuNew);
        menuBar.add(menuEdit);
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

        inputPanel.add(new JLabel("Фамилия", JLabel.CENTER));
        JTextField firstNameText = new JTextField();
        inputPanel.add(firstNameText);
        inputPanel.add(new JLabel("Имя", JLabel.CENTER));
        JTextField secondNameText = new JTextField();
        inputPanel.add(secondNameText);
        inputPanel.add(new JLabel("Отчество", JLabel.CENTER));
        JTextField thirdNameText = new JTextField();
        inputPanel.add(thirdNameText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Добавить");
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
                    JOptionPane.showMessageDialog(clientFrame, "Проверьте данные");
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
        inputPanel.setLayout(new GridLayout(2, 2, 0, 2));

        inputPanel.add(new JLabel("ID клиента", JLabel.CENTER));
        JTextField clientIdText = new JTextField();
        inputPanel.add(clientIdText);
        inputPanel.add(new JLabel("ID услуги", JLabel.CENTER));
        JTextField serviceIdText = new JTextField();
        inputPanel.add(serviceIdText);
        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Добавить");
        addButton.setPreferredSize(new Dimension(320, 60));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean isEmpty = clientIdText.getText().isEmpty() || serviceIdText.getText().isEmpty() || serviceIdText.getText().isEmpty();
                if (isEmpty) {
                    JOptionPane.showMessageDialog(orderFrame, "Введите данные");
                    return;
                }
                int clientId, serviceId;
                ClientInfo client = clients.get(0);
                try {
                    clientId = Integer.parseInt(clientIdText.getText());
                    boolean isExist = false;
                    for (ClientInfo elem : clients) {
                        if (elem.getId() == clientId) {
                            isExist = true;
                            client = elem;
                            break;
                        }
                    }
                    if (!isExist) {
                        JOptionPane.showMessageDialog(orderFrame, "Клиент не найден");
                        return;
                    }
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(orderFrame, "Неправильный id клиента");
                    return;
                }
                ServiceInfo service = services.get(0);
                try {
                    serviceId = Integer.parseInt(serviceIdText.getText());
                    boolean isExist = false;
                    for (ServiceInfo elem : services) {
                        if (elem.getId() == serviceId) {
                            isExist = true;
                            service = elem;
                            break;
                        }
                    }
                    if (!isExist) {
                        JOptionPane.showMessageDialog(orderFrame, "Услуга не найдена");
                        return;
                    }
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(orderFrame, "Неправильный id услуги");
                    return;
                }
                client.visit();
                modelClients.fireTableDataChanged();
                OrderInfo newOrder = new OrderInfo(client, service, Date.valueOf(LocalDate.now()));
                try {
                    orders.add(newOrder);
                    dbHandler.addOrder(newOrder);
                    modelOrders.fireTableDataChanged();
                    orderFrame.dispose();
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(orderFrame, e.getMessage());
                    return;
                }
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
        inputPanel.setLayout(new GridLayout(3, 2, 0, 1));

        inputPanel.add(new JLabel("Тип услуги", JLabel.CENTER));
        JTextField typeText = new JTextField();
        inputPanel.add(typeText);


        inputPanel.add(new JLabel("Наименование услуги", JLabel.CENTER));
        JTextField nameText = new JTextField();
        inputPanel.add(nameText);

        inputPanel.add(new JLabel("Стоимость услуги", JLabel.CENTER));
        JTextField priceText = new JTextField();
        inputPanel.add(priceText);

        for (Component c : inputPanel.getComponents()) {
            c.setFont(new Font("Arial", Font.BOLD, 16));
        }

        JButton addButton = new JButton("Добавить");
        addButton.setPreferredSize(new Dimension(600, 60));
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
                        JOptionPane.showMessageDialog(serviceFrame, "Неправильный формат цены");
                        return;
                    }
                    nameText.setText("");
                    priceText.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(serviceFrame, "Проверьте данные");
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
        tableClients.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        scrollPaneClients.setViewportView(tableClients);
        panelClients.add(scrollPaneClients);
        tabbedPane.addTab("Клиенты", panelClients);

        JPanel panelServices = new JPanel();
        panelServices.setLayout(new GridLayout());
        JScrollPane scrollPaneServices = new JScrollPane();
        modelServices = new ServiceTableModel(services);
        tableServices = new JTable(modelServices);
        tableServices.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        scrollPaneServices.setViewportView(tableServices);
        panelServices.add(scrollPaneServices);
        tabbedPane.addTab("Услуги", panelServices);

        JPanel panelOrders = new JPanel();
        panelOrders.setLayout(new GridLayout());
        JScrollPane scrollPaneOrders = new JScrollPane();
        modelOrders = new OrderTableModel(orders);
        tableOrders = new JTable(modelOrders);
        tableOrders.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        scrollPaneOrders.setViewportView(tableOrders);
        panelOrders.add(scrollPaneOrders);
        tabbedPane.addTab("Заказы", panelOrders);

        tabbedPane.setFont(new Font("Arial", Font.BOLD, 15));

        return tabbedPane;
    }

}
