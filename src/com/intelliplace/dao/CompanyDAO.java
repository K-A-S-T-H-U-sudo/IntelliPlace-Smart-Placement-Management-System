package com.intelliplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.intelliplace.model.Company;
import com.intelliplace.util.DBConnection;

public class CompanyDAO {

    // ADD COMPANY
    public boolean addCompany(Company company) {

        String sql =
                "INSERT INTO companies " +
                "(company_name, location) " +
                "VALUES (?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    company.getCompanyName()
            );

            statement.setString(
                    2,
                    company.getLocation()
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


    // GET ALL COMPANIES
    public List<Company> getAllCompanies() {

        List<Company> companies =
                new ArrayList<>();

        String sql =
                "SELECT * FROM companies";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Company company =
                        new Company();

                company.setCompanyId(
                        result.getInt("company_id")
                );

                company.setCompanyName(
                        result.getString("company_name")
                );

                company.setLocation(
                        result.getString("location")
                );

                companies.add(company);
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return companies;
    }


    // DELETE COMPANY
    public boolean deleteCompany(int companyId) {

        String sql =
                "DELETE FROM companies " +
                "WHERE company_id = ?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(
                    1,
                    companyId
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