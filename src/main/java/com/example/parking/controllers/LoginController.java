package com.example.parking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.parking.models.Database;
import com.example.parking.models.User;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = Database.authenticate(username, password);
        if (user != null) {
            loadParkingScene();
        } else {
            System.out.println("Invalid username or password");
        }
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        loadRegisterScene();
    }

    private void loadParkingScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/parking.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRegisterScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
