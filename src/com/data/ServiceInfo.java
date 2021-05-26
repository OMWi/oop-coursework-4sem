package com.data;

import java.io.Serializable;
public class ServiceInfo implements Serializable{
    private String type;
    private String name;
    private double price;

    public ServiceInfo(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "com.data.ServiceInfo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
