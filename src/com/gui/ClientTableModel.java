package com.gui;

import com.data.ClientInfo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {
    private List<ClientInfo> clients;

    public ClientTableModel(List<ClientInfo> clients) {
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        String name = "Unknown";
        switch (column) {
            case 0:
                name = "ID";
                break;
            case 1:
                name = "Фамилия";
                break;
            case 2:
                name = "Имя";
                break;
            case 3:
                name = "Отчество";
                break;
            case 4:
                name = "Количество посещений";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 0:
            case 4:
                type = Object.class;
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
                value = client.getId();
                break;
            case 1:
                value = client.getFirstName();
                break;
            case 2:
                value = client.getSecondName();
                break;
            case 3:
                value = client.getThirdName();
                break;
            case 4:
                value = client.getVisits();
                break;
        }
        return value;
    }
}
