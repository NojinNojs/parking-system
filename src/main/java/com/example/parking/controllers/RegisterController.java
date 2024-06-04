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

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    public void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.equals(confirmPassword)) {
            Database.addUser(username, password);
            loadLoginScene();
        } else {
            // Show error message
            System.out.println("Passwords do not match.");
        }
    }

    @FXML
    public void handleBackToLogin(ActionEvent event) {
        loadLoginScene();
    }

    private void loadLoginScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
