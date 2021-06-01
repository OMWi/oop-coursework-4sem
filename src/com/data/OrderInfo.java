package com.data;

import java.sql.Date;

public class OrderInfo {
    private static int globalID = 0;
    private double discount = 0.03;

    private int id;
    private int clientID;
    private int serviceID;
    private double price;
    private Date receiptDate;
    private Date returnDate;

    public int getID() {
        return id;
    }

    public int getClientID() {
        return clientID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public double getPrice() {
        return price;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public OrderInfo(ClientInfo client, ServiceInfo service, Date receiptDate) {
        id = globalID++;
        this.clientID = client.getId();
        this.serviceID = service.getId();
        this.price = service.getPrice();
        if (client.getVisits() > 2) {
            this.price *= (1 - discount);
        }
        this.receiptDate = receiptDate;
    }

    public OrderInfo(int clientID, int serviceID, double price, Date receiptDate, Date returnDate) {
        id = globalID++;
        this.clientID = clientID;
        this.serviceID = serviceID;
        this.price = price;
        this.receiptDate = receiptDate;
        this.returnDate = returnDate;
    }
}
