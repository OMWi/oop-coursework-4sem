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
            JFrame frameAdd = new JFrame();
            frameAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameAdd.setMinimumSize(new Dimension(400, 400));
            frameAdd.setLocationRelativeTo(frame);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 1));
            JEditorPane editorPane = new JEditorPane();




            frameAdd.setVisible(true);
        }
        else if ("new service".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "new service");
        }
        else if ("new client".equals(e.getActionCommand())) {

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
        //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel panelOrders = new JPanel();
        panelOrders.setLayout(new GridLayout(1, 1));

        JScrollPane scrollPaneOrders = new JScrollPane();
        OrderTableModel modelOrders = new OrderTableModel(orders);
        tableOrders = new JTable(modelOrders);
        scrollPaneOrders.setViewportView(tableOrders);

        panelOrders.add(scrollPaneOrders);
        tabbedPane.addTab("Orders", panelOrders);


        frame.add(tabbedPane, BorderLayout.CENTER);
    }

}
