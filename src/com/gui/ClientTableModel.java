package com.gui;

import com.data.clients.ClientInfo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {
    private List<ClientInfo> clients;

    public ClientTableModel(List<ClientInfo> clients) {
        this.clients = new ArrayList<>(clients);
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        String name = "Unknown";
        switch (column) {
            case 0:
                name = "First name";
                break;
            case 1:
                name = "Second name";
                break;
            case 2:
                name = "Third name";
                break;
            case 3:
                name = "Visits";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 3:
                type = Integer.class;
                break;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClientInfo client = clients.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = client.getFirstName();
                break;
            case 1:
                value = client.getSecondName();
                break;
            case 2:
                value = client.getThirdName();
                break;
            case 3:
                value = client.getVisits();
                break;
        }
        return value;
    }
}
