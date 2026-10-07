package com.intelliplace.controller;

import com.intelliplace.dao.CompanyDAO;
import com.intelliplace.model.Company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class CompanyController {

    private TextField companyNameField;
    private TextField locationField;

    private TableView<Company> table;

    private CompanyDAO companyDAO =
            new CompanyDAO();

    public void showCompanies(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("Company Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Label subtitle =
                new Label(
                        "Manage recruiting companies and locations"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );


        // =========================
        // INPUT FIELDS
        // =========================

        companyNameField =
                createField("Company Name");

        locationField =
                createField("Location");


        // =========================
        // FORM
        // =========================

        GridPane form =
                new GridPane();

        form.setHgap(15);

        form.setVgap(12);


        Label companyLabel =
                createLabel("Company Name");

        Label locationLabel =
                createLabel("Location");


        form.add(
                companyLabel,
                0,
                0
        );

        form.add(
                companyNameField,
                1,
                0
        );

        form.add(
                locationLabel,
                2,
                0
        );

        form.add(
                locationField,
                3,
                0
        );


        // =========================
        // BUTTONS
        // =========================

        Button addButton =
                new Button("Add Company");

        Button viewButton =
                new Button("View Companies");

        Button deleteButton =
                new Button("Delete Company");

        Button clearButton =
                new Button("Clear");


        stylePrimaryButton(addButton);

        styleSecondaryButton(viewButton);

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
        // ADD COMPANY
        // =========================

        addButton.setOnAction(e -> {

            String companyName =
                    companyNameField
                            .getText()
                            .trim();

            String location =
                    locationField
                            .getText()
                            .trim();


            if (companyName.isEmpty()) {

                message.setText(
                        "Please enter company name."
                );

                return;
            }


            try {

                Company company =
                        new Company(
                                0,
                                companyName,
                                location
                        );


                companyDAO.addCompany(
                        company
                );


                message.setText(
                        "Company added successfully!"
                );


                clearFields();

                loadCompanies();

            } catch (Exception ex) {

                message.setText(
                        "Error adding company."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // VIEW COMPANIES
        // =========================

        viewButton.setOnAction(e -> {

            loadCompanies();

            message.setText(
                    "Company records loaded."
            );
        });


        // =========================
        // DELETE COMPANY
        // =========================

        deleteButton.setOnAction(e -> {

            Company selectedCompany =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selectedCompany == null) {

                message.setText(
                        "Please select a company to delete."
                );

                return;
            }


            try {

                companyDAO.deleteCompany(
                        selectedCompany.getCompanyId()
                );


                message.setText(
                        "Company deleted successfully!"
                );


                loadCompanies();

            } catch (Exception ex) {

                message.setText(
                        "Error deleting company."
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


        TableColumn<Company, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "companyId"
                )
        );


        TableColumn<Company, String> nameColumn =
                new TableColumn<>("Company Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "companyName"
                )
        );


        TableColumn<Company, String> locationColumn =
                new TableColumn<>("Location");

        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "location"
                )
        );


        table.getColumns().addAll(
                idColumn,
                nameColumn,
                locationColumn
        );


        table.setPrefHeight(400);


        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // =========================
        // ROW CLICK
        // =========================

        table.setOnMouseClicked(e -> {

            Company selected =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selected != null) {

                companyNameField.setText(
                        selected.getCompanyName()
                );

                locationField.setText(
                        selected.getLocation()
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

        loadCompanies();


        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1000,
                        650
                );


        stage.setTitle(
                "IntelliPlace - Companies"
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
    // LOAD COMPANIES
    // =================================================

    private void loadCompanies() {

        try {

            ObservableList<Company> data =
                    FXCollections.observableArrayList(
                            companyDAO.getAllCompanies()
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

        companyNameField.clear();

        locationField.clear();
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