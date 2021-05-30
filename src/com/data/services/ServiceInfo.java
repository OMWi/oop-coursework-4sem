package com.data.services;

import java.io.Serializable;
public class ServiceInfo implements Serializable{
    private String type;
    private String name;
    private double price;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double price() {
        return price;
    }

    public ServiceInfo(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "com.data.services.ServiceInfo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

