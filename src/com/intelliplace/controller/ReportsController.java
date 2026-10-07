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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportsController {

    private Label studentsValue;
    private Label companiesValue;
    private Label jobsValue;
    private Label applicationsValue;
    private Label selectedValue;

    public void showReports(Stage stage) {

        // =========================================
        // HEADER
        // =========================================

        Label title =
                new Label("Placement Analytics");

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Label subtitle =
                new Label(
                        "Overview of campus placement activities"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );

        VBox heading =
                new VBox(5);

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // =========================================
        // STATISTIC CARDS
        // =========================================

        studentsValue =
                new Label("0");

        companiesValue =
                new Label("0");

        jobsValue =
                new Label("0");

        applicationsValue =
                new Label("0");

        selectedValue =
                new Label("0");

        VBox studentsCard =
                createCard(
                        "TOTAL STUDENTS",
                        studentsValue,
                        "Registered students"
                );

        VBox companiesCard =
                createCard(
                        "TOTAL COMPANIES",
                        companiesValue,
                        "Recruiting companies"
                );

        VBox jobsCard =
                createCard(
                        "TOTAL JOBS",
                        jobsValue,
                        "Available opportunities"
                );

        VBox applicationsCard =
                createCard(
                        "APPLICATIONS",
                        applicationsValue,
                        "Student applications"
                );

        VBox selectedCard =
                createCard(
                        "SELECTED STUDENTS",
                        selectedValue,
                        "Successfully selected"
                );

        // =========================================
        // CARD GRID
        // =========================================

        GridPane cards =
                new GridPane();

        cards.setHgap(20);
        cards.setVgap(20);

        cards.add(
                studentsCard,
                0,
                0
        );

        cards.add(
                companiesCard,
                1,
                0
        );

        cards.add(
                jobsCard,
                2,
                0
        );

        cards.add(
                applicationsCard,
                0,
                1
        );

        cards.add(
                selectedCard,
                1,
                1
        );

        // =========================================
        // REFRESH BUTTON
        // =========================================

        Button refreshButton =
                new Button("↻  Refresh Data");

        refreshButton.setPrefWidth(150);
        refreshButton.setPrefHeight(40);

        refreshButton.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        refreshButton.setOnAction(e -> {

            loadReportData();
        });

        // =========================================
        // BACK BUTTON
        // =========================================

        Button backButton =
                new Button("←  Back to Dashboard");

        backButton.setPrefWidth(180);
        backButton.setPrefHeight(40);

        backButton.setStyle(
                "-fx-background-color: #e2e8f0;" +
                "-fx-text-fill: #334155;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> {

            DashboardController dashboard =
                    new DashboardController();

            dashboard.showDashboard(
                    stage,
                    "admin",
                    "ADMIN"
            );
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                javafx.scene.layout.Priority.ALWAYS
        );

        HBox actions =
                new HBox(
                        10,
                        backButton,
                        spacer,
                        refreshButton
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================
        // CONTENT
        // =========================================

        VBox content =
                new VBox(25);

        content.setPadding(
                new Insets(35)
        );

        content.getChildren().addAll(
                heading,
                cards,
                actions
        );

        // =========================================
        // ROOT
        // =========================================

        BorderPane root =
                new BorderPane();

        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f8fafc;"
        );

        // =========================================
        // LOAD DATA
        // =========================================

        loadReportData();

        // =========================================
        // SCENE
        // =========================================

        Scene scene =
                new Scene(
                        root,
                        1100,
                        650
                );

        stage.setTitle(
                "IntelliPlace - Placement Analytics"
        );

        stage.setScene(scene);

        stage.show();
    }

    // =============================================
    // CREATE STATISTIC CARD
    // =============================================

    private VBox createCard(
            String title,
            Label value,
            String description) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #64748b;"
        );

        value.setStyle(
                "-fx-font-size: 34px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #94a3b8;"
        );

        VBox card =
                new VBox(10);

        card.setPrefWidth(300);
        card.setPrefHeight(150);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #e2e8f0;" +
                "-fx-border-radius: 12px;"
        );

        card.getChildren().addAll(
                titleLabel,
                value,
                descriptionLabel
        );

        return card;
    }

    // =============================================
    // LOAD REPORT DATA
    // =============================================

    private void loadReportData() {

        String sql =
                "SELECT " +
                "(SELECT COUNT(*) FROM students) AS total_students, " +
                "(SELECT COUNT(*) FROM companies) AS total_companies, " +
                "(SELECT COUNT(*) FROM jobs) AS total_jobs, " +
                "(SELECT COUNT(*) FROM applications) AS total_applications, " +
                "(SELECT COUNT(*) FROM applications " +
                "WHERE status = 'Selected') AS selected_students";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                studentsValue.setText(
                        String.valueOf(
                                result.getInt(
                                        "total_students"
                                )
                        )
                );

                companiesValue.setText(
                        String.valueOf(
                                result.getInt(
                                        "total_companies"
                                )
                        )
                );

                jobsValue.setText(
                        String.valueOf(
                                result.getInt(
                                        "total_jobs"
                                )
                        )
                );

                applicationsValue.setText(
                        String.valueOf(
                                result.getInt(
                                        "total_applications"
                                )
                        )
                );

                selectedValue.setText(
                        String.valueOf(
                                result.getInt(
                                        "selected_students"
                                )
                        )
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

            studentsValue.setText("-");
            companiesValue.setText("-");
            jobsValue.setText("-");
            applicationsValue.setText("-");
            selectedValue.setText("-");
        }
    }
}