package com.data.orders;

import com.data.clients.ClientInfo;
import com.data.services.ServiceInfo;

import java.sql.Date;
import java.time.LocalDate;

public class OrderInfo {
    private String clientFirstName;
    private String clientSecondName;
    private String serviceName;
    private Date receiptDate;
    private Date returnDate;

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientSecondName() {
        return clientSecondName;
    }

    public String getServiceName() {
        return serviceName;
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

    public OrderInfo(String clientFirstName, String clientSecondName, String serviceName, Date receiptDate) {
        this.clientFirstName = clientFirstName;
        this.clientSecondName = clientSecondName;
        this.serviceName = serviceName;
        this.receiptDate = receiptDate;
        this.returnDate = Date.valueOf(LocalDate.MAX);
    }

    public OrderInfo(String clientFirstName, String clientSecondName, String serviceName, Date receiptDate, Date returnDate) {
        this.clientFirstName = clientFirstName;
        this.clientSecondName = clientSecondName;
        this.serviceName = serviceName;
        this.receiptDate = receiptDate;
        this.returnDate = returnDate;
    }
}
