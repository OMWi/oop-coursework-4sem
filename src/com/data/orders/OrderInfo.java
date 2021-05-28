package com.data.orders;

import com.data.clients.ClientInfo;
import com.data.services.ServiceInfo;

import java.time.LocalDate;

public class OrderInfo {
    private ServiceInfo service;
    private ClientInfo client;
    private LocalDate receiptDate;
    private LocalDate returnDate;

    public ServiceInfo getService() {
        return service;
    }

    public ClientInfo getClient() {
        return client;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public OrderInfo(ServiceInfo service, ClientInfo client, LocalDate receiptDate) {
        this.service = service;
        this.client = client;
        this.receiptDate = receiptDate;
        this.returnDate = LocalDate.MAX;
        System.out.println(returnDate);
    }
}
