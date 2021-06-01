package com.gui;

import com.data.OrderInfo;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private List<OrderInfo> orders;

    public OrderTableModel(List<OrderInfo> orders) {
        this.orders = orders;
    }

    @Override
    public int getRowCount() {
        return orders.size();
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
                name = "Client id";
                break;
            case 1:
                name = "Service id";
                break;
            case 2:
                name = "Price";
                break;
            case 3:
                name = "Receipt date";
                break;
            case 4:
                name = "Return date";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = int.class;
        switch (columnIndex) {
            case 2:
                type = double.class;
                break;
            case 3:
            case 4:
                type = LocalDate.class;
                break;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderInfo order = orders.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = order.getClientID();
                break;
            case 1:
                value = order.getServiceID();
                break;
            case 2:
                value = order.getPrice();
                break;
            case 3:
                value = order.getReceiptDate();
                break;
            case 4:
                value = order.getReturnDate();
                break;
        }
        return value;
    }
}
