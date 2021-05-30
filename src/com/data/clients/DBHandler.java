package com.data.clients;
import com.data.clients.ClientInfo;
import com.data.orders.OrderInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBHandler {
    private  String tableName;
    private String dbName;
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<ClientInfo> readClients() throws Exception {
        ArrayList<ClientInfo> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            while (resultSet.next()) {
                list.add(new ClientInfo(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getInt(5)));
            }
        }
        catch (Exception e) { }
        return list;
    }

    public ArrayList<OrderInfo> readOrders() throws Exception {
        ArrayList<OrderInfo> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            while (resultSet.next()) {
                list.add(new OrderInfo(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getDate(5), resultSet.getDate(6)));
            }
        }
        catch (Exception e) { }
        return list;
    }

    public void add(ClientInfo client) throws  Exception {
        statement.execute("INSERT INTO " + tableName + " (firstName, secondName, thirdName, visits) VALUES "+ String.format("(\"%s\", \"%s\", \"%s\", %d);" ,
                                client.getFirstName(), client.getSecondName(), client.getThirdName(), client.getVisits()));
    }

    public void delete(int id) throws Exception {
        statement.execute("DELETE FROM " + tableName + " WHERE id=" + id);
    }

    public void delete(ClientInfo client) throws Exception {
        statement.execute("DELETE FROM " + dbName + "WHERE ");
    }

    public DBHandler(String dbName, String tableName, String url, String user, String password) throws Exception {
        this.dbName = dbName;
        this.tableName = tableName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.connection = DriverManager.getConnection(url, user, password);
        this.statement = this.connection.createStatement();
    }

}
