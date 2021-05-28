package com.data.clients;

import java.lang.ref.Cleaner;

public class ClientInfo {
    private String firstName;
    private String secondName;
    private String thirdName;
    private int visits;

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public int getVisits() {
        return visits;
    }

    public ClientInfo(String firstName, String secondName, String thirdName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.visits = 0;
    }
    public ClientInfo(String firstName, String secondName, String thirdName, int visits) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", visits=" + visits +
                '}';
    }
}
