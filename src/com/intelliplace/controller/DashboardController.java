package com.intelliplace.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController {

    public void showDashboard(
            Stage stage,
            String username,
            String role) {

        // =========================================
        // TOP HEADER
        // =========================================

        Label logo =
                new Label("IntelliPlace");

        logo.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label systemName =
                new Label(
                        " | Campus Placement Management System"
                );

        systemName.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #cbd5e1;"
        );

        HBox logoArea =
                new HBox(
                        8,
                        logo,
                        systemName
                );

        logoArea.setAlignment(
                Pos.CENTER_LEFT
        );

        Label welcome =
                new Label(
                        "Welcome, " +
                        username +
                        "  •  " +
                        role
                );

        welcome.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Region headerSpacer =
                new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        HBox topBar =
                new HBox(
                        logoArea,
                        headerSpacer,
                        welcome
                );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(
                        18,
                        25,
                        18,
                        25
                )
        );

        topBar.setStyle(
                "-fx-background-color: #0f172a;"
        );

        // =========================================
        // SIDEBAR
        // =========================================

        Label menuTitle =
                new Label("MAIN MENU");

        menuTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #94a3b8;"
        );

        Button dashboardButton =
                createMenuButton(
                        "Dashboard"
                );

        Button studentsButton =
                createMenuButton(
                        "Students"
                );

        Button companiesButton =
                createMenuButton(
                        "Companies"
                );

        Button jobsButton =
                createMenuButton(
                        "Jobs"
                );

        Button applicationsButton =
                createMenuButton(
                        "Applications"
                );

        Button reportsButton =
                createMenuButton(
                        "Reports"
                );

        Region menuSpacer =
                new Region();

        VBox.setVgrow(
                menuSpacer,
                Priority.ALWAYS
        );

        Button logoutButton =
                createMenuButton(
                        "Logout"
                );

        logoutButton.setStyle(
                "-fx-background-color: #fee2e2;" +
                "-fx-text-fill: #b91c1c;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 15px;" +
                "-fx-cursor: hand;"
        );

        VBox sidebar =
                new VBox(8);

        sidebar.setPadding(
                new Insets(25, 15, 20, 15)
        );

        sidebar.setPrefWidth(210);

        sidebar.setStyle(
                "-fx-background-color: #f1f5f9;"
        );

        sidebar.getChildren().addAll(
                menuTitle,
                dashboardButton,
                studentsButton,
                companiesButton,
                jobsButton,
                applicationsButton,
                reportsButton,
                menuSpacer,
                logoutButton
        );

        // =========================================
        // MAIN CONTENT
        // =========================================

        Label pageTitle =
                new Label(
                        "Dashboard"
                );

        pageTitle.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0f172a;"
        );

        Label pageSubtitle =
                new Label(
                        "Manage and monitor campus placement activities"
                );

        pageSubtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );

        VBox pageHeading =
                new VBox(
                        5,
                        pageTitle,
                        pageSubtitle
                );

        // =========================================
        // DASHBOARD CARDS
        // =========================================

        VBox studentsCard =
                createDashboardCard(
                        "STUDENTS",
                        "Manage student profiles",
                        "#2563eb"
                );

        VBox companiesCard =
                createDashboardCard(
                        "COMPANIES",
                        "Manage recruiting companies",
                        "#7c3aed"
                );

        VBox jobsCard =
                createDashboardCard(
                        "JOBS",
                        "Manage job opportunities",
                        "#059669"
                );

        VBox applicationsCard =
                createDashboardCard(
                        "APPLICATIONS",
                        "Track student applications",
                        "#ea580c"
                );

        HBox row1 =
                new HBox(
                        20,
                        studentsCard,
                        companiesCard
                );

        HBox row2 =
                new HBox(
                        20,
                        jobsCard,
                        applicationsCard
                );

        // =========================================
        // QUICK ACTION SECTION
        // =========================================

        Label quickTitle =
                new Label(
                        "Quick Actions"
                );

        quickTitle.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Button addStudentButton =
                new Button(
                        "+  Add Student"
                );

        Button addCompanyButton =
                new Button(
                        "+  Add Company"
                );

        Button viewReportsButton =
                new Button(
                        "View Reports"
                );

        styleActionButton(
                addStudentButton
        );

        styleActionButton(
                addCompanyButton
        );

        styleActionButton(
                viewReportsButton
        );

        HBox quickActions =
                new HBox(
                        12,
                        addStudentButton,
                        addCompanyButton,
                        viewReportsButton
                );

        VBox content =
                new VBox(
                        25
                );

        content.setPadding(
                new Insets(35)
        );

        content.getChildren().addAll(
                pageHeading,
                row1,
                row2,
                quickTitle,
                quickActions
        );

        // =========================================
        // BUTTON ACTIONS
        // =========================================

        dashboardButton.setOnAction(e -> {

            showDashboard(
                    stage,
                    username,
                    role
            );
        });

        studentsButton.setOnAction(e -> {

            StudentController controller =
                    new StudentController();

            controller.showStudents(stage);
        });

        companiesButton.setOnAction(e -> {

            CompanyController controller =
                    new CompanyController();

            controller.showCompanies(stage);
        });

        jobsButton.setOnAction(e -> {

            JobController controller =
                    new JobController();

            controller.showJobs(stage);
        });

        applicationsButton.setOnAction(e -> {

            ApplicationController controller =
                    new ApplicationController();

            controller.showApplications(stage);
        });

        reportsButton.setOnAction(e -> {

            ReportsController controller =
                    new ReportsController();

            controller.showReports(stage);
        });

        addStudentButton.setOnAction(e -> {

            StudentController controller =
                    new StudentController();

            controller.showStudents(stage);
        });

        addCompanyButton.setOnAction(e -> {

            CompanyController controller =
                    new CompanyController();

            controller.showCompanies(stage);
        });

        viewReportsButton.setOnAction(e -> {

            ReportsController controller =
                    new ReportsController();

            controller.showReports(stage);
        });

        logoutButton.setOnAction(e -> {

            LoginController loginController =
                    new LoginController();

            loginController.showLogin(stage);
        });

        // =========================================
        // MAIN LAYOUT
        // =========================================

        BorderPane root =
                new BorderPane();

        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f8fafc;"
        );

        // =========================================
        // SCENE
        // =========================================

        Scene scene =
                new Scene(
                        root,
                        1150,
                        700
                );

        stage.setTitle(
                "IntelliPlace - Dashboard"
        );

        stage.setScene(scene);
        stage.show();
    }

    // =============================================
    // MENU BUTTON STYLE
    // =============================================

    private Button createMenuButton(
            String text) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #334155;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 15px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =============================================
    // DASHBOARD CARD
    // =============================================

    private VBox createDashboardCard(
            String title,
            String description,
            String accentColor) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0f172a;"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #64748b;"
        );

        Label viewLabel =
                new Label(
                        "Open module  →"
                );

        viewLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                accentColor +
                ";"
        );

        VBox card =
                new VBox(
                        10,
                        titleLabel,
                        descriptionLabel,
                        viewLabel
                );

        card.setPrefWidth(400);
        card.setPrefHeight(145);

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #e2e8f0;" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;"
        );

        return card;
    }

    // =============================================
    // QUICK ACTION BUTTON
    // =============================================

    private void styleActionButton(
            Button button) {

        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 0 18px;" +
                "-fx-cursor: hand;"
        );
    }
}