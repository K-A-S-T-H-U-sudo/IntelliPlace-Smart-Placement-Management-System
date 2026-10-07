package com.intelliplace.controller;

import com.intelliplace.dao.ApplicationDAO;
import com.intelliplace.model.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class ApplicationController {

    private TextField studentIdField;
    private TextField jobIdField;

    private DatePicker applicationDatePicker;

    private ComboBox<String> statusComboBox;

    private TableView<Application> table;

    private ApplicationDAO applicationDAO =
            new ApplicationDAO();


    public void showApplications(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("Application Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );


        Label subtitle =
                new Label(
                        "Manage student job applications and application status"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );


        // =========================
        // INPUT FIELDS
        // =========================

        studentIdField =
                createField("Student ID");

        jobIdField =
                createField("Job ID");


        // =========================
        // DATE PICKER
        // =========================

        applicationDatePicker =
                new DatePicker();

        applicationDatePicker.setPrefWidth(220);

        applicationDatePicker.setPrefHeight(38);


        // =========================
        // STATUS COMBO BOX
        // =========================

        statusComboBox =
                new ComboBox<>();

        statusComboBox.getItems().addAll(
                "Applied",
                "Shortlisted",
                "Selected",
                "Rejected"
        );

        statusComboBox.setValue(
                "Applied"
        );

        statusComboBox.setPrefWidth(220);

        statusComboBox.setPrefHeight(38);


        // =========================
        // FORM
        // =========================

        GridPane form =
                new GridPane();

        form.setHgap(15);

        form.setVgap(12);


        form.add(
                createLabel("Student ID"),
                0,
                0
        );

        form.add(
                studentIdField,
                1,
                0
        );


        form.add(
                createLabel("Job ID"),
                2,
                0
        );

        form.add(
                jobIdField,
                3,
                0
        );


        form.add(
                createLabel("Application Date"),
                0,
                1
        );

        form.add(
                applicationDatePicker,
                1,
                1
        );


        form.add(
                createLabel("Status"),
                2,
                1
        );

        form.add(
                statusComboBox,
                3,
                1
        );


        // =========================
        // BUTTONS
        // =========================

        Button addButton =
                new Button("Add Application");

        Button viewButton =
                new Button("View Applications");

        Button updateButton =
                new Button("Update Status");

        Button deleteButton =
                new Button("Delete Application");

        Button clearButton =
                new Button("Clear");


        stylePrimaryButton(addButton);

        styleSecondaryButton(viewButton);

        styleUpdateButton(updateButton);

        styleDangerButton(deleteButton);

        styleSecondaryButton(clearButton);


        // =========================
        // MESSAGE
        // =========================

        Label message =
                new Label();

        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #2563eb;"
        );


        // =========================
        // ADD APPLICATION
        // =========================

        addButton.setOnAction(e -> {

            try {

                int studentId =
                        Integer.parseInt(
                                studentIdField
                                        .getText()
                                        .trim()
                        );


                int jobId =
                        Integer.parseInt(
                                jobIdField
                                        .getText()
                                        .trim()
                        );


                if (applicationDatePicker
                        .getValue() == null) {

                    message.setText(
                            "Please select application date."
                    );

                    return;
                }


                Application application =
                        new Application(
                                0,
                                studentId,
                                jobId,
                                applicationDatePicker
                                        .getValue(),
                                statusComboBox
                                        .getValue()
                        );


                applicationDAO.addApplication(
                        application
                );


                message.setText(
                        "Application added successfully!"
                );


                clearFields();

                loadApplications();

            } catch (NumberFormatException ex) {

                message.setText(
                        "Please enter valid Student ID and Job ID."
                );

            } catch (Exception ex) {

                message.setText(
                        "Error adding application."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // VIEW APPLICATIONS
        // =========================

        viewButton.setOnAction(e -> {

            loadApplications();

            message.setText(
                    "Application records loaded."
            );
        });


        // =========================
        // UPDATE STATUS
        // =========================

        updateButton.setOnAction(e -> {

            Application selectedApplication =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selectedApplication == null) {

                message.setText(
                        "Please select an application."
                );

                return;
            }


            try {

                String newStatus =
                        statusComboBox.getValue();


                applicationDAO.updateStatus(
                        selectedApplication
                                .getApplicationId(),
                        newStatus
                );


                message.setText(
                        "Application status updated!"
                );


                loadApplications();

            } catch (Exception ex) {

                message.setText(
                        "Error updating status."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // DELETE APPLICATION
        // =========================

        deleteButton.setOnAction(e -> {

            Application selectedApplication =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selectedApplication == null) {

                message.setText(
                        "Please select an application to delete."
                );

                return;
            }


            try {

                applicationDAO.deleteApplication(
                        selectedApplication
                                .getApplicationId()
                );


                message.setText(
                        "Application deleted successfully!"
                );


                loadApplications();

            } catch (Exception ex) {

                message.setText(
                        "Error deleting application."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // CLEAR
        // =========================

        clearButton.setOnAction(e -> {

            clearFields();

            message.setText("");
        });


        // =========================
        // TABLE
        // =========================

        table =
                new TableView<>();


        TableColumn<Application, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "applicationId"
                )
        );


        TableColumn<Application, Integer> studentColumn =
                new TableColumn<>("Student ID");

        studentColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "studentId"
                )
        );


        TableColumn<Application, Integer> jobColumn =
                new TableColumn<>("Job ID");

        jobColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "jobId"
                )
        );


        TableColumn<Application, java.time.LocalDate> dateColumn =
                new TableColumn<>("Application Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "applicationDate"
                )
        );


        TableColumn<Application, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );


        table.getColumns().addAll(
                idColumn,
                studentColumn,
                jobColumn,
                dateColumn,
                statusColumn
        );


        table.setPrefHeight(350);


        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // =========================
        // ROW CLICK
        // =========================

        table.setOnMouseClicked(e -> {

            Application selected =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selected != null) {

                studentIdField.setText(
                        String.valueOf(
                                selected.getStudentId()
                        )
                );


                jobIdField.setText(
                        String.valueOf(
                                selected.getJobId()
                        )
                );


                applicationDatePicker.setValue(
                        selected.getApplicationDate()
                );


                statusComboBox.setValue(
                        selected.getStatus()
                );
            }
        });


        // =========================
        // ACTION BUTTONS
        // =========================

        HBox actionButtons =
                new HBox(10);


        actionButtons.setAlignment(
                Pos.CENTER_LEFT
        );


        actionButtons.getChildren().addAll(
                addButton,
                viewButton,
                updateButton,
                deleteButton,
                clearButton
        );


        // =========================
        // BACK TO DASHBOARD
        // =========================

        Button backButton =
                new Button(
                        "←  Back to Dashboard"
                );


        backButton.setPrefWidth(190);

        backButton.setPrefHeight(42);


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


        HBox bottomBar =
                new HBox(10);


        bottomBar.setAlignment(
                Pos.CENTER_LEFT
        );


        bottomBar.getChildren().addAll(
                backButton,
                spacer
        );


        // =========================
        // CONTENT
        // =========================

        VBox heading =
                new VBox(
                        5,
                        title,
                        subtitle
                );


        VBox content =
                new VBox(20);


        content.setPadding(
                new Insets(30)
        );


        content.getChildren().addAll(
                heading,
                form,
                actionButtons,
                message,
                table,
                bottomBar
        );


        // =========================
        // ROOT
        // =========================

        BorderPane root =
                new BorderPane();


        root.setCenter(content);


        root.setStyle(
                "-fx-background-color: #f8fafc;"
        );


        // =========================
        // LOAD DATA
        // =========================

        loadApplications();


        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1150,
                        700
                );


        stage.setTitle(
                "IntelliPlace - Applications"
        );


        stage.setScene(scene);

        stage.show();
    }


    // =================================================
    // CREATE FIELD
    // =================================================

    private TextField createField(
            String prompt) {

        TextField field =
                new TextField();


        field.setPromptText(
                prompt
        );


        field.setPrefWidth(220);

        field.setPrefHeight(38);


        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #cbd5e1;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 0 10px;" +
                "-fx-font-size: 13px;"
        );


        return field;
    }


    // =================================================
    // LABEL
    // =================================================

    private Label createLabel(
            String text) {

        Label label =
                new Label(text);


        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #334155;"
        );


        return label;
    }


    // =================================================
    // LOAD APPLICATIONS
    // =================================================

    private void loadApplications() {

        try {

            ObservableList<Application> data =
                    FXCollections.observableArrayList(
                            applicationDAO
                                    .getAllApplications()
                    );


            table.setItems(data);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =================================================
    // CLEAR FIELDS
    // =================================================

    private void clearFields() {

        studentIdField.clear();

        jobIdField.clear();

        applicationDatePicker.setValue(
                null
        );

        statusComboBox.setValue(
                "Applied"
        );
    }


    // =================================================
    // PRIMARY BUTTON
    // =================================================

    private void stylePrimaryButton(
            Button button) {

        button.setPrefHeight(38);


        button.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }


    // =================================================
    // SECONDARY BUTTON
    // =================================================

    private void styleSecondaryButton(
            Button button) {

        button.setPrefHeight(38);


        button.setStyle(
                "-fx-background-color: #e2e8f0;" +
                "-fx-text-fill: #334155;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }


    // =================================================
    // UPDATE BUTTON
    // =================================================

    private void styleUpdateButton(
            Button button) {

        button.setPrefHeight(38);


        button.setStyle(
                "-fx-background-color: #16a34a;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }


    // =================================================
    // DANGER BUTTON
    // =================================================

    private void styleDangerButton(
            Button button) {

        button.setPrefHeight(38);


        button.setStyle(
                "-fx-background-color: #dc2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );
    }
}