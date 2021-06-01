package com.gui;

import com.data.ServiceInfo;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceTableModel extends AbstractTableModel {
    private List<ServiceInfo> services;

    public ServiceTableModel(List<ServiceInfo> services) {
        this.services = services;
    }

    @Override
    public int getRowCount() {
        return services.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        String name = "Unknown";
        switch (column) {
            case 0:
                name = "Service type";
                break;
            case 1:
                name = "Service name";
                break;
            case 2:
                name = "Price";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 2:
                type = double.class;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ServiceInfo service = services.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = service.getType();
                break;
            case 1:
                value = service.getName();
                break;
            case 2:
                value = service.getPrice();
                break;
        }
        return value;
    }
}
