package com.intelliplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.intelliplace.model.Job;
import com.intelliplace.util.DBConnection;

public class JobDAO {

    // ==========================================
    // ADD JOB
    // ==========================================

    public boolean addJob(Job job) {

        String sql =
                "INSERT INTO jobs " +
                "(company_id, job_role, package_lpa, min_cgpa, required_skills) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    job.getCompanyId()
            );

            statement.setString(
                    2,
                    job.getJobRole()
            );

            statement.setDouble(
                    3,
                    job.getPackageLpa()
            );

            statement.setDouble(
                    4,
                    job.getMinCgpa()
            );

            statement.setString(
                    5,
                    job.getRequiredSkills()
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


    // ==========================================
    // GET ALL JOBS
    // ==========================================

    public List<Job> getAllJobs() {

        List<Job> jobs =
                new ArrayList<>();

        String sql =
                "SELECT * FROM jobs";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Job job =
                        new Job();

                job.setJobId(
                        result.getInt("job_id")
                );

                job.setCompanyId(
                        result.getInt("company_id")
                );

                job.setJobRole(
                        result.getString("job_role")
                );

                job.setPackageLpa(
                        result.getDouble("package_lpa")
                );

                job.setMinCgpa(
                        result.getDouble("min_cgpa")
                );

                job.setRequiredSkills(
                        result.getString("required_skills")
                );

                jobs.add(job);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return jobs;
    }


    // ==========================================
    // DELETE JOB
    // ==========================================

    public boolean deleteJob(int jobId) {

        String sql =
                "DELETE FROM jobs " +
                "WHERE job_id = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    jobId
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