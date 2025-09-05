package com.studentmgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection connection = null;

    private DBConnection() { } // private constructor

    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = DBConnection.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {

                Properties prop = new Properties();
                prop.load(input);

                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Database connected successfully!");

            } catch (Exception e) {
                System.err.println("❌ DB Connection failed: " + e.getMessage());
            }
        }
        return connection;
    }
}
