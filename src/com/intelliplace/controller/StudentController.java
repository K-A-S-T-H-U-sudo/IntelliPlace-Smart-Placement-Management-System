package com.intelliplace.controller;

import com.intelliplace.model.Student;
import com.intelliplace.dao.StudentDAO;

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

public class StudentController {

    private TextField nameField;
    private TextField departmentField;
    private TextField yearField;
    private TextField cgpaField;
    private TextField emailField;
    private TextField phoneField;
    private TextField skillsField;

    private TableView<Student> table;

    private StudentDAO studentDAO =
            new StudentDAO();

    public void showStudents(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label title =
                new Label("Student Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1e293b;"
        );

        Label subtitle =
                new Label(
                        "Manage student information and records"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #64748b;"
        );


        // =========================
        // INPUT FIELDS
        // =========================

        nameField =
                createField("Student Name");

        departmentField =
                createField("Department");

        yearField =
                createField("Year");

        cgpaField =
                createField("CGPA");

        emailField =
                createField("Email");

        phoneField =
                createField("Phone");

        skillsField =
                createField("Skills");


        // =========================
        // FORM GRID
        // =========================

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(12);

        form.add(
                createLabel("Name"),
                0,
                0
        );

        form.add(
                nameField,
                1,
                0
        );

        form.add(
                createLabel("Department"),
                2,
                0
        );

        form.add(
                departmentField,
                3,
                0
        );


        form.add(
                createLabel("Year"),
                0,
                1
        );

        form.add(
                yearField,
                1,
                1
        );

        form.add(
                createLabel("CGPA"),
                2,
                1
        );

        form.add(
                cgpaField,
                3,
                1
        );


        form.add(
                createLabel("Email"),
                0,
                2
        );

        form.add(
                emailField,
                1,
                2
        );

        form.add(
                createLabel("Phone"),
                2,
                2
        );

        form.add(
                phoneField,
                3,
                2
        );


        form.add(
                createLabel("Skills"),
                0,
                3
        );

        form.add(
                skillsField,
                1,
                3,
                3,
                1
        );


        // =========================
        // BUTTONS
        // =========================

        Button addButton =
                new Button("Add Student");

        Button viewButton =
                new Button("View Students");

        Button deleteButton =
                new Button("Delete Student");

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
        // ADD STUDENT
        // =========================

        addButton.setOnAction(e -> {

            try {

                String name =
                        nameField.getText().trim();

                String department =
                        departmentField.getText().trim();

                int year =
                        Integer.parseInt(
                                yearField.getText().trim()
                        );

                double cgpa =
                        Double.parseDouble(
                                cgpaField.getText().trim()
                        );

                String email =
                        emailField.getText().trim();

                String phone =
                        phoneField.getText().trim();

                String skills =
                        skillsField.getText().trim();


                if (name.isEmpty()) {

                    message.setText(
                            "Please enter student name."
                    );

                    return;
                }


                Student student =
                        new Student(
                                0,
                                name,
                                department,
                                year,
                                cgpa,
                                email,
                                phone,
                                skills
                        );


                studentDAO.addStudent(student);

                message.setText(
                        "Student added successfully!"
                );

                clearFields();

                loadStudents();

            } catch (NumberFormatException ex) {

                message.setText(
                        "Please enter valid Year and CGPA."
                );

            } catch (Exception ex) {

                message.setText(
                        "Error adding student."
                );

                ex.printStackTrace();
            }
        });


        // =========================
        // VIEW STUDENTS
        // =========================

        viewButton.setOnAction(e -> {

            loadStudents();

            message.setText(
                    "Student records loaded."
            );
        });


        // =========================
        // DELETE STUDENT
        // =========================

        deleteButton.setOnAction(e -> {

            Student selectedStudent =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selectedStudent == null) {

                message.setText(
                        "Please select a student to delete."
                );

                return;
            }


            try {

                studentDAO.deleteStudent(
                        selectedStudent.getStudentId()
                );

                message.setText(
                        "Student deleted successfully!"
                );

                loadStudents();

            } catch (Exception ex) {

                message.setText(
                        "Error deleting student."
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


        TableColumn<Student, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "studentId"
                )
        );


        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "name"
                )
        );


        TableColumn<Student, String> deptColumn =
                new TableColumn<>("Department");

        deptColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "department"
                )
        );


        TableColumn<Student, Integer> yearColumn =
                new TableColumn<>("Year");

        yearColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "year"
                )
        );


        TableColumn<Student, Double> cgpaColumn =
                new TableColumn<>("CGPA");

        cgpaColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "cgpa"
                )
        );


        TableColumn<Student, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "email"
                )
        );


        TableColumn<Student, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "phone"
                )
        );


        TableColumn<Student, String> skillsColumn =
                new TableColumn<>("Skills");

        skillsColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "skills"
                )
        );


        table.getColumns().addAll(
                idColumn,
                nameColumn,
                deptColumn,
                yearColumn,
                cgpaColumn,
                emailColumn,
                phoneColumn,
                skillsColumn
        );


        table.setPrefHeight(300);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // =========================
        // ROW CLICK
        // =========================

        table.setOnMouseClicked(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                nameField.setText(
                        selected.getName()
                );

                departmentField.setText(
                        selected.getDepartment()
                );

                yearField.setText(
                        String.valueOf(
                                selected.getYear()
                        )
                );

                cgpaField.setText(
                        String.valueOf(
                                selected.getCgpa()
                        )
                );

                emailField.setText(
                        selected.getEmail()
                );

                phoneField.setText(
                        selected.getPhone()
                );

                skillsField.setText(
                        selected.getSkills()
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

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(30)
        );

        content.getChildren().addAll(
                new VBox(
                        5,
                        title,
                        subtitle
                ),
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

        loadStudents();


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
                "IntelliPlace - Students"
        );

        stage.setScene(scene);

        stage.show();
    }


    // =================================================
    // CREATE TEXT FIELD
    // =================================================

    private TextField createField(
            String prompt) {

        TextField field =
                new TextField();

        field.setPromptText(prompt);

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
    // LOAD STUDENTS
    // =================================================

    private void loadStudents() {

        try {

            ObservableList<Student> data =
                    FXCollections.observableArrayList(
                            studentDAO.getAllStudents()
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

        nameField.clear();

        departmentField.clear();

        yearField.clear();

        cgpaField.clear();

        emailField.clear();

        phoneField.clear();

        skillsField.clear();
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