package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.data.Serializer;
import com.data.ServiceInfo;

public class MainForm {
    private JPanel PanelMain;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new MainForm().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(600, 480));
        frame.setJMenuBar(createMenuBar());

        // todo: dump on close (or not)
        ArrayList<ServiceInfo> services;
        String fileName = "services";
        Serializer serializer = new Serializer();
        try {
            services = (ArrayList<ServiceInfo>) serializer.load(fileName);
        }
        catch (Exception e) {
            // todo: do smth
        }


        frame.setVisible(true);
    }

    public static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        //add menu, alt+m to click
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription("KekW menu");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("KekW option 1");
        menu.add(menuItem);
        return menuBar;
    }
}
