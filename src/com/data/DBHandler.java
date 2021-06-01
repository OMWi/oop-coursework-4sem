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
            list.add(new OrderInfo(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6)));
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
        statement.execute("INSERT INTO clients (firstName, secondName, thirdName, visits) VALUES "+ String.format("(\"%s\", \"%s\", \"%s\", %d);" ,
                                client.getFirstName(), client.getSecondName(), client.getThirdName(), client.getVisits()));
    }

    public void addOrder(OrderInfo order) throws Exception {
        statement.execute("INSERT INTO orders (clientFirstName, clientSecondName, serviceName, receiptDate, returnDate) VALUES " +
                String.format("(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\");", order.getClientFirstName(), order.getClientSecondName(),
                        order.getServiceName(), order.getReceiptDate().toString(), order.getReturnDate().toString()));
    }

    public void addService(ServiceInfo service) throws Exception {
        String comm = "INSERT INTO services (type, name, price) VALUES " +
                String.format("(\"%s\", \"%s\", %s);", service.getType(), service.getName(), service.getPrice());
        statement.execute(comm);
    }

    public void deleteClient(ClientInfo client) throws Exception {
        statement.execute(String.format("DELETE FROM clients WHERE firstName=\"%s\" AND secondName=\"%s\" AND thirdName=\"%s\";",
                client.getFirstName(), client.getSecondName(), client.getThirdName()));
    }

    public void deleteService(ServiceInfo service) throws Exception {
        statement.execute(String.format("DELETE FROM services WHERE type=\"%s\" AND name=\"%s\" AND price=%f;",
                service.getType(), service.getName(), service.getPrice()));
    }

    public void deleteOrder(OrderInfo order) throws Exception {
        statement.execute(String.format("DELETE FROM orders WHERE clientFirstName=\"%s\" AND clientSecondName=\"%s\" AND serviceName=\"%s\"  AND receiptDate=\"%s\" AND returnDate=\"%s\";",
                order.getClientFirstName(), order.getClientSecondName(), order.getServiceName(), order.getReceiptDate(), order.getReturnDate()));
    }

    public DBHandler(String url, String user, String password) throws Exception {
        this.connection = DriverManager.getConnection(url, user, password);
        this.statement = this.connection.createStatement();
    }

}
