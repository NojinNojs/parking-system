package com.example.parking.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.parking.models.Database;
import com.example.parking.models.Vehicle;

public class ParkingController {
    @FXML
    private TextField vehicleNumberField;
    @FXML
    private Label statusLabel;

    @FXML
    public void handleEntry() {
        String vehicleNumber = vehicleNumberField.getText();
        Vehicle vehicle = new Vehicle(vehicleNumber, System.currentTimeMillis());
        Database.addVehicle(vehicle);
        statusLabel.setText("Vehicle " + vehicleNumber + " entered.");
    }

    @FXML
    public void handleExit() {
        String vehicleNumber = vehicleNumberField.getText();
        Vehicle vehicle = Database.getVehicle(vehicleNumber);
        if (vehicle != null) {
            long parkedTime = System.currentTimeMillis() - vehicle.getEntryTime();
            int cost = calculateParkingCost(parkedTime, vehicle.isMotorcycle(), vehicle.getEntryTime());
            Database.removeVehicle(vehicleNumber);
            statusLabel.setText("Vehicle " + vehicleNumber + " exited. Cost: Rp" + cost);
        } else {
            statusLabel.setText("Vehicle not found.");
        }
    }

    private int calculateParkingCost(long parkedTime, boolean isMotorcycle, long entryTime) {
        int hours = (int) (parkedTime / (1000 * 60 * 60)) + 1; // round up to next hour
        int cost = 0;
        if (isMotorcycle) {
            cost = 2000 + (hours - 1) * 1000;
            if (crossedMidnight(entryTime)) {
                cost += 20000;
            }
        } else {
            cost = 5000 + (hours - 1) * 4000;
            if (crossedMidnight(entryTime)) {
                cost += 50000;
            }
        }
        return cost;
    }

    private boolean crossedMidnight(long entryTime) {
        // Get the current time
        long currentTime = System.currentTimeMillis();

        // Get the day of entry and current day
        java.util.Calendar entryCalendar = java.util.Calendar.getInstance();
        entryCalendar.setTimeInMillis(entryTime);
        int entryDay = entryCalendar.get(java.util.Calendar.DAY_OF_YEAR);

        java.util.Calendar currentCalendar = java.util.Calendar.getInstance();
        currentCalendar.setTimeInMillis(currentTime);
        int currentDay = currentCalendar.get(java.util.Calendar.DAY_OF_YEAR);

        // Check if the days are different
        return entryDay != currentDay;
    }
}
