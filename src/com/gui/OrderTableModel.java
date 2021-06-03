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
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        String name = "Unknown";
        switch (column) {
            case 0:
                name = "ID";
                break;
            case 1:
                name = "ID клиента";
                break;
            case 2:
                name = "ID услуги";
                break;
            case 3:
                name = "Цена";
                break;
            case 4:
                name = "Дата приема";
                break;
            case 5:
                name = "Дата возврата";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = Object.class;
        switch (columnIndex) {
            case 3:
                type = double.class;
                break;
            case 4:
            case 5:
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
                value = order.getID();
                break;
            case 1:
                value = order.getClientID();
                break;
            case 2:
                value = order.getServiceID();
                break;
            case 3:
                value = order.getPrice();
                break;
            case 4:
                value = order.getReceiptDate();
                break;
            case 5:
                value = order.getReturnDate();
                break;
        }
        return value;
    }
}
