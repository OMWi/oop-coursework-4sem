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
        //reading data
        try {
            serializer = new Serializer();
            services = (ArrayList<ServiceInfo>) serializer.load(serviceFilePath);
        } catch (Exception e) { }
        try {
            dbHandler = new DBHandler(dbName, tableName, url, user, password);
            clients = dbHandler.read();
        } catch (Exception e) { }

        //creating mainFrame
        frame = createMainFrame("Химчистка");
        frame.setJMenuBar(createMenuBar());
        addTables(frame);
        /*
        ClientTableModel model = new ClientTableModel(clients);
        tableClients = new JTable(model);
        frame.add(new JScrollPane(tableClients));
         */
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if ("new order".equals(e.getActionCommand())) {

        }
        else if ("new service".equals(e.getActionCommand())) {
            JOptionPane.showMessageDialog(frame, "new service");
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

    public void simplePanels(JFrame frame) {
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("label1");
        label1.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(label1);
        JPanel inPanel = new JPanel();
        JLabel label2 = new JLabel("label2");
        label1.setHorizontalAlignment(JLabel.CENTER);
        inPanel.add(label2);
        panel1.add(inPanel);
        frame.getContentPane().add(panel1);
    }

    public void addTables(JFrame frame) {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1));

        JScrollPane scrollPane = new JScrollPane();
        ClientTableModel model = new ClientTableModel(clients);
        tableClients = new JTable(model);
        scrollPane.setViewportView(tableClients);

        panel1.add(scrollPane);
        tabbedPane.addTab("Clients", panel1);
        //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        frame.add(tabbedPane, BorderLayout.CENTER);
    }

}
