package com.data;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHandler {
    private  String tableName = "clients";
    private String url = "jdbc:mysql://localhost:3306/oop_4sem";
    private String user = "root";
    private String password = "573458";
    private Connection connection;
    private Statement statement;

    public void read() throws Exception {
        ArrayList<ClientInfo> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from" + tableName);
        while (resultSet.next()) {
            list.add(new ClientInfo(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4)));
        }
        System.out.println(list);
    }

    public void add(ClientInfo client) throws  Exception {
        statement.execute("INSERT INTO " + tableName + " (firstName, secondName, thirdName, visits) VALUES "+ String.format("(\"%s\", \"%s\", \"%s\", %d);" ,
                                client.getFirstName(), client.getSecondName(), client.getThirdName(), client.getVisits()));
    }

    public void delete(int id) throws Exception {
        statement.execute("DELETE FROM " + tableName + " WHERE id=" + id);
    }

    public DBHandler(String tableName, String url, String user, String password) throws Exception {
        this.tableName = tableName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.connection = DriverManager.getConnection(url, user, password);
        this.statement = this.connection.createStatement();
    }

}
