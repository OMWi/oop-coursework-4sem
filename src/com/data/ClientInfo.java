package com.data;

public class ClientInfo {
    private static int globalID = 1;
    private int id;
    private String firstName;
    private String secondName;
    private String thirdName;
    private int visits;

    public int getId() {
        return id;
    }

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

    public void visit() {
        this.visits++;
    }

    public ClientInfo(String firstName, String secondName, String thirdName) {
        this.id = globalID++;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.visits = 0;
    }
    public ClientInfo(String firstName, String secondName, String thirdName, int visits) {
        this.id = globalID++;
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
