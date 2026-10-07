package com.intelliplace;

import java.sql.Connection;

import com.intelliplace.util.DBConnection;

public class DBTest {

    public static void main(String[] args) {

        try {

            Connection connection = DBConnection.getConnection();

            System.out.println("Database Connected Successfully!");

            connection.close();

        } catch (Exception e) {

            System.out.println("Database Connection Failed!");

            e.printStackTrace();
        }
    }
}