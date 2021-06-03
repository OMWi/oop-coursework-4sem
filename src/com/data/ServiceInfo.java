package com.data;

import java.io.Serializable;
public class ServiceInfo implements Serializable{
    private static int globalID = 1;
    private int id;
    private String type;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ServiceInfo(String type, String name, double price) {
        this.id = globalID++;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "com.data.database.ServiceInfo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

