package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHandler {
    private Connection connection;
    private Statement statement;


    public ArrayList<ClientInfo> readClients() throws Exception {
        ArrayList<ClientInfo> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
        while (resultSet.next()) {
            list.add(new ClientInfo(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getInt(5)));
        }
        return list;
    }

    public ArrayList<OrderInfo> readOrders() throws Exception {
        ArrayList<OrderInfo> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM orders;");
        while (resultSet.next()) {
            list.add(new OrderInfo(resultSet.getInt(2), resultSet.getInt(3),
                    resultSet.getDouble(4), resultSet.getDate(5), resultSet.getDate(6)));
        }
        return list;
    }

    public ArrayList<ServiceInfo> readServices() throws Exception {
        ArrayList<ServiceInfo> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM services;");
        while (resultSet.next()) {
            list.add(new ServiceInfo(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getDouble(4)));
        }
        return list;
    }

    public void addClient(ClientInfo client) throws  Exception {
        statement.execute("INSERT INTO clients (id, firstName, secondName, thirdName, visits) VALUES "+ String.format("(%d, \"%s\", \"%s\", \"%s\", %d);" ,
                                client.getId(), client.getFirstName(), client.getSecondName(), client.getThirdName(), client.getVisits()));
    }

    public void addOrder(OrderInfo order) throws Exception {
        statement.execute("INSERT INTO orders (id, clientID, servicID, price, receiptDate) VALUES " +
                String.format("(%d, %d, %d, %f, \"%s\");", order.getID(), order.getClientID(),
                        order.getServiceID(), order.getPrice(), order.getReceiptDate().toString()));
    }

    public void addService(ServiceInfo service) throws Exception {
        String comm = "INSERT INTO services (id, type, name, price) VALUES " +
                String.format("(%d, \"%s\", \"%s\", %s);", service.getId(), service.getType(), service.getName(), service.getPrice());
        statement.execute(comm);
    }

    public DBHandler(String url, String user, String password) throws Exception {
        this.connection = DriverManager.getConnection(url, user, password);
        this.statement = this.connection.createStatement();
    }

}
