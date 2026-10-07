package com.intelliplace.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.intelliplace.util.DBConnection;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

    public void showLogin(Stage stage) {

        // =========================
        // LEFT SIDE - BRANDING
        // =========================

        Label brandTitle =
                new Label("IntelliPlace");

        brandTitle.setStyle(
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label brandSubtitle =
                new Label(
                        "Smart Campus Placement\nManagement System"
                );

        brandSubtitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #cbd5e1;" +
                "-fx-line-spacing: 5px;"
        );

        Label description =
                new Label(
                        "Manage students, companies,\n" +
                        "job opportunities and placements\n" +
                        "in one simple platform."
                );

        description.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #94a3b8;" +
                "-fx-line-spacing: 6px;"
        );

        VBox leftContent =
                new VBox(25);

        leftContent.setAlignment(Pos.CENTER_LEFT);

        leftContent.setPadding(
                new Insets(50)
        );

        leftContent.getChildren().addAll(
                brandTitle,
                brandSubtitle,
                description
        );

        VBox leftPanel =
                new VBox();

        leftPanel.setPrefWidth(430);

        leftPanel.setAlignment(Pos.CENTER_LEFT);

        leftPanel.setStyle(
                "-fx-background-color: #0f172a;"
        );

        leftPanel.getChildren().add(
                leftContent
        );


        // =========================
        // RIGHT SIDE - LOGIN
        // =========================

        Label loginTitle =
                new Label("Welcome Back");

        loginTitle.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Label loginSubtitle =
                new Label(
                        "Sign in to continue to IntelliPlace"
                );

        loginSubtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );


        // Username

        Label usernameLabel =
                new Label("Username");

        usernameLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );

        TextField usernameField =
                new TextField();

        usernameField.setPromptText(
                "Enter your username"
        );

        usernameField.setPrefHeight(45);

        usernameField.setMaxWidth(330);

        usernameField.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-border-color: #cbd5e1;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 0 12px;" +
                "-fx-font-size: 14px;"
        );


        // Password

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefHeight(45);

        passwordField.setMaxWidth(330);

        passwordField.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-border-color: #cbd5e1;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 0 12px;" +
                "-fx-font-size: 14px;"
        );


        // Login Button

        Button loginButton =
                new Button("SIGN IN");

        loginButton.setPrefWidth(330);

        loginButton.setPrefHeight(45);

        loginButton.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );


        // Message

        Label message =
                new Label();

        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #dc2626;"
        );

        message.setWrapText(true);

        message.setMaxWidth(330);


        // =========================
        // LOGIN BUTTON ACTION
        // =========================

        loginButton.setOnAction(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    passwordField.getText();

            // Empty validation

            if (username.isEmpty()
                    || password.isEmpty()) {

                message.setText(
                        "Please enter username and password."
                );

                return;
            }


            // SQL Query

            String sql =
                    "SELECT * FROM users " +
                    "WHERE username = ? AND password = ?";


            try {

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                statement.setString(
                        1,
                        username
                );

                statement.setString(
                        2,
                        password
                );

                ResultSet result =
                        statement.executeQuery();


                // Login Success

                if (result.next()) {

                    String role =
                            result.getString("role");


                    result.close();

                    statement.close();

                    connection.close();


                    // Open Dashboard

                    DashboardController dashboard =
                            new DashboardController();

                    dashboard.showDashboard(
                            stage,
                            username,
                            role
                    );

                }

                // Login Failed

                else {

                    message.setText(
                            "Invalid username or password."
                    );

                    passwordField.clear();

                    result.close();

                    statement.close();

                    connection.close();
                }


            } catch (Exception ex) {

                message.setText(
                        "Database connection error."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // LOGIN FORM
        // =========================

        VBox loginForm =
                new VBox(10);

        loginForm.setMaxWidth(330);

        loginForm.getChildren().addAll(
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton,
                message
        );


        // =========================
        // RIGHT CONTENT
        // =========================

        VBox rightContent =
                new VBox(25);

        rightContent.setAlignment(
                Pos.CENTER
        );

        rightContent.setPadding(
                new Insets(40)
        );

        rightContent.getChildren().addAll(
                loginTitle,
                loginSubtitle,
                loginForm
        );


        // =========================
        // RIGHT PANEL
        // =========================

        VBox rightPanel =
                new VBox();

        rightPanel.setAlignment(
                Pos.CENTER
        );

        rightPanel.setStyle(
                "-fx-background-color: #ffffff;"
        );

        rightPanel.getChildren().add(
                rightContent
        );


        // =========================
        // MAIN LAYOUT
        // =========================

        BorderPane root =
                new BorderPane();

        root.setLeft(leftPanel);

        root.setCenter(rightPanel);

        root.setStyle(
                "-fx-background-color: white;"
        );


        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1000,
                        600
                );


        stage.setTitle(
                "IntelliPlace - Login"
        );

        stage.setScene(scene);

        stage.setResizable(false);

        stage.show();
    }
}