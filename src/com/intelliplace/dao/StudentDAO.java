package com.intelliplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.intelliplace.model.Student;
import com.intelliplace.util.DBConnection;

public class StudentDAO {

    // ==============================
    // ADD STUDENT
    // ==============================

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(name, department, year, cgpa, email, phone, skills) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, student.getName());
            statement.setString(2, student.getDepartment());
            statement.setInt(3, student.getYear());
            statement.setDouble(4, student.getCgpa());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getSkills());

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // ==============================
    // GET ALL STUDENTS
    // ==============================

    public List<Student> getAllStudents() {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Student student =
                        new Student();

                student.setStudentId(
                        result.getInt("student_id")
                );

                student.setName(
                        result.getString("name")
                );

                student.setDepartment(
                        result.getString("department")
                );

                student.setYear(
                        result.getInt("year")
                );

                student.setCgpa(
                        result.getDouble("cgpa")
                );

                student.setEmail(
                        result.getString("email")
                );

                student.setPhone(
                        result.getString("phone")
                );

                student.setSkills(
                        result.getString("skills")
                );

                students.add(student);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return students;
    }


    // ==============================
    // DELETE STUDENT
    // ==============================

    public boolean deleteStudent(int studentId) {

        String sql =
                "DELETE FROM students WHERE student_id = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    studentId
            );

            int rows =
                    statement.executeUpdate();

            statement.close();
            connection.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}

