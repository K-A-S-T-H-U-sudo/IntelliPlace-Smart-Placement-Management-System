package com.intelliplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.intelliplace.model.Application;
import com.intelliplace.util.DBConnection;

public class ApplicationDAO {

    // Add Application
    public boolean addApplication(Application application) {

        String sql =
                "INSERT INTO applications " +
                "(student_id, job_id, application_date, status) " +
                "VALUES (?, ?, ?, ?)";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    application.getStudentId()
            );

            statement.setInt(
                    2,
                    application.getJobId()
            );

            statement.setDate(
                    3,
                    java.sql.Date.valueOf(
                            application.getApplicationDate()
                    )
            );

            statement.setString(
                    4,
                    application.getStatus()
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


    // View All Applications
    public List<Application> getAllApplications() {

        List<Application> applications =
                new ArrayList<>();

        String sql =
                "SELECT * FROM applications";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Application application =
                        new Application();

                application.setApplicationId(
                        result.getInt("application_id")
                );

                application.setStudentId(
                        result.getInt("student_id")
                );

                application.setJobId(
                        result.getInt("job_id")
                );

                application.setApplicationDate(
                        result.getDate(
                                "application_date"
                        ).toLocalDate()
                );

                application.setStatus(
                        result.getString("status")
                );

                applications.add(application);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return applications;
    }


    // Delete Application
    public boolean deleteApplication(
            int applicationId) {

        String sql =
                "DELETE FROM applications " +
                "WHERE application_id = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    applicationId
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


    // Update Application Status
    public boolean updateStatus(
            int applicationId,
            String status) {

        String sql =
                "UPDATE applications " +
                "SET status = ? " +
                "WHERE application_id = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    status
            );

            statement.setInt(
                    2,
                    applicationId
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