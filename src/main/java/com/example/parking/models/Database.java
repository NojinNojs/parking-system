package com.example.parking.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:parking.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                // Create users table if it does not exist
                String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                                        + "username TEXT PRIMARY KEY, "
                                        + "password TEXT)";
                Statement stmt = conn.createStatement();
                stmt.execute(createUsersTable);

                // Create vehicles table if it does not exist
                String createVehiclesTable = "CREATE TABLE IF NOT EXISTS vehicles ("
                                           + "number TEXT PRIMARY KEY, "
                                           + "entryTime INTEGER, "
                                           + "isMotorcycle INTEGER)";
                stmt.execute(createVehiclesTable);

                // Insert a sample user for testing (ignore if user already exists)
                String insertUser = "INSERT OR IGNORE INTO users (username, password) VALUES ('admin', 'password')";
                stmt.execute(insertUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to authenticate a user
    public static User authenticate(String username, String password) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to add a new user
    public static void addUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String insertUser = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertUser);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a vehicle
    public static void addVehicle(Vehicle vehicle) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String insertVehicle = "INSERT INTO vehicles (number, entryTime, isMotorcycle) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertVehicle);
            pstmt.setString(1, vehicle.getNumber());
            pstmt.setLong(2, vehicle.getEntryTime());
            pstmt.setBoolean(3, vehicle.isMotorcycle());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get a vehicle
    public static Vehicle getVehicle(String number) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String query = "SELECT * FROM vehicles WHERE number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, number);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String vehicleNumber = rs.getString("number");
                long entryTime = rs.getLong("entryTime");
                boolean isMotorcycle = rs.getBoolean("isMotorcycle");
                return new Vehicle(vehicleNumber, entryTime, isMotorcycle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to remove a vehicle
    public static void removeVehicle(String number) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String deleteVehicle = "DELETE FROM vehicles WHERE number = ?";
            PreparedStatement pstmt = conn.prepareStatement(deleteVehicle);
            pstmt.setString(1, number);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
