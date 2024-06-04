package com.example.parking.models;

public class Vehicle {
    private String number;
    private long entryTime;
    private boolean isMotorcycle;

    public Vehicle(String number, long entryTime) {
        this.number = number;
        this.entryTime = entryTime;
        this.isMotorcycle = number.toLowerCase().startsWith("m");
    }

    public Vehicle(String number, long entryTime, boolean isMotorcycle) {
        this.number = number;
        this.entryTime = entryTime;
        this.isMotorcycle = isMotorcycle;
    }

    public String getNumber() {
        return number;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public boolean isMotorcycle() {
        return isMotorcycle;
    }
}
