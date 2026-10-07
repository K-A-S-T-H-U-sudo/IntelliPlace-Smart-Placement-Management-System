package com.intelliplace.controller;

import com.intelliplace.dao.JobDAO;
import com.intelliplace.model.Job;

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

public class JobController {

    private TextField companyIdField;
    private TextField jobRoleField;
    private TextField packageField;
    private TextField minCgpaField;
    private TextField requiredSkillsField;

    private TableView<Job> table;

    private JobDAO jobDAO =
            new JobDAO();

    public void showJobs(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("Job Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Label subtitle =
                new Label(
                        "Manage job opportunities and eligibility requirements"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );


        // =========================
        // INPUT FIELDS
        // =========================

        companyIdField =
                createField("Company ID");

        jobRoleField =
                createField("Job Role");

        packageField =
                createField("Package (LPA)");

        minCgpaField =
                createField("Minimum CGPA");

        requiredSkillsField =
                createField("Required Skills");


        // =========================
        // FORM
        // =========================

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(12);


        form.add(
                createLabel("Company ID"),
                0,
                0
        );

        form.add(
                companyIdField,
                1,
                0
        );


        form.add(
                createLabel("Job Role"),
                2,
                0
        );

        form.add(
                jobRoleField,
                3,
                0
        );


        form.add(
                createLabel("Package (LPA)"),
                0,
                1
        );

        form.add(
                packageField,
                1,
                1
        );


        form.add(
                createLabel("Minimum CGPA"),
                2,
                1
        );

        form.add(
                minCgpaField,
                3,
                1
        );


        form.add(
                createLabel("Required Skills"),
                0,
                2
        );

        form.add(
                requiredSkillsField,
                1,
                2,
                3,
                1
        );


        // =========================
        // BUTTONS
        // =========================

        Button addButton =
                new Button("Add Job");

        Button viewButton =
                new Button("View Jobs");

        Button deleteButton =
                new Button("Delete Job");

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
        // ADD JOB
        // =========================

        addButton.setOnAction(e -> {

            try {

                int companyId =
                        Integer.parseInt(
                                companyIdField
                                        .getText()
                                        .trim()
                        );

                String jobRole =
                        jobRoleField
                                .getText()
                                .trim();

                double packageLpa =
                        Double.parseDouble(
                                packageField
                                        .getText()
                                        .trim()
                        );

                double minCgpa =
                        Double.parseDouble(
                                minCgpaField
                                        .getText()
                                        .trim()
                        );

                String requiredSkills =
                        requiredSkillsField
                                .getText()
                                .trim();


                if (jobRole.isEmpty()) {

                    message.setText(
                            "Please enter job role."
                    );

                    return;
                }


                Job job =
                        new Job(
                                0,
                                companyId,
                                jobRole,
                                packageLpa,
                                minCgpa,
                                requiredSkills
                        );


                jobDAO.addJob(job);


                message.setText(
                        "Job added successfully!"
                );


                clearFields();

                loadJobs();

            } catch (NumberFormatException ex) {

                message.setText(
                        "Please enter valid numeric values."
                );

            } catch (Exception ex) {

                message.setText(
                        "Error adding job."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // VIEW JOBS
        // =========================

        viewButton.setOnAction(e -> {

            loadJobs();

            message.setText(
                    "Job records loaded."
            );
        });


        // =========================
        // DELETE JOB
        // =========================

        deleteButton.setOnAction(e -> {

            Job selectedJob =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selectedJob == null) {

                message.setText(
                        "Please select a job to delete."
                );

                return;
            }


            try {

                jobDAO.deleteJob(
                        selectedJob.getJobId()
                );


                message.setText(
                        "Job deleted successfully!"
                );


                loadJobs();

            } catch (Exception ex) {

                message.setText(
                        "Error deleting job."
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


        TableColumn<Job, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "jobId"
                )
        );


        TableColumn<Job, Integer> companyColumn =
                new TableColumn<>("Company ID");

        companyColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "companyId"
                )
        );


        TableColumn<Job, String> roleColumn =
                new TableColumn<>("Job Role");

        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "jobRole"
                )
        );


        TableColumn<Job, Double> packageColumn =
                new TableColumn<>("Package LPA");

        packageColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "packageLpa"
                )
        );


        TableColumn<Job, Double> cgpaColumn =
                new TableColumn<>("Min CGPA");

        cgpaColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "minCgpa"
                )
        );


        TableColumn<Job, String> skillsColumn =
                new TableColumn<>("Required Skills");

        skillsColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "requiredSkills"
                )
        );


        table.getColumns().addAll(
                idColumn,
                companyColumn,
                roleColumn,
                packageColumn,
                cgpaColumn,
                skillsColumn
        );


        table.setPrefHeight(350);


        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // =========================
        // ROW CLICK
        // =========================

        table.setOnMouseClicked(e -> {

            Job selected =
                    table.getSelectionModel()
                            .getSelectedItem();


            if (selected != null) {

                companyIdField.setText(
                        String.valueOf(
                                selected.getCompanyId()
                        )
                );

                jobRoleField.setText(
                        selected.getJobRole()
                );

                packageField.setText(
                        String.valueOf(
                                selected.getPackageLpa()
                        )
                );

                minCgpaField.setText(
                        String.valueOf(
                                selected.getMinCgpa()
                        )
                );

                requiredSkillsField.setText(
                        selected.getRequiredSkills()
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

        loadJobs();


        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1100,
                        680
                );


        stage.setTitle(
                "IntelliPlace - Jobs"
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
    // LOAD JOBS
    // =================================================

    private void loadJobs() {

        try {

            ObservableList<Job> data =
                    FXCollections.observableArrayList(
                            jobDAO.getAllJobs()
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

        companyIdField.clear();

        jobRoleField.clear();

        packageField.clear();

        minCgpaField.clear();

        requiredSkillsField.clear();
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