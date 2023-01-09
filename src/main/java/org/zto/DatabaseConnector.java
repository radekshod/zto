package org.zto;

import java.sql.*;

public class DatabaseConnector {
    public static final String HOST = "jdbc:postgresql://snuffleupagus.db.elephantsql.com/";
    public static final String DATABASE = "filwtnox";
    public static final String USERNAME = "filwtnox";
    public static final String PASSWD = "Meqpo3117K3YO3Ze3bzE-zoykU2zXkoU";
    public Connection connection;

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(HOST + DATABASE, USERNAME, PASSWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println(new StringBuilder()
                .append("Success! Connected to PostgreSql database: ")
                .append(DATABASE).toString());
    }

    public void createRecord(String sql) {
        try {
            Statement stm = this.connection.createStatement();
            stm.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet searchRecord(String sql) {
        try {
            Statement stm = this.connection.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateRecord(String sql) {
        try {
            Statement stm = this.connection.createStatement();
            return stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteRecord(String sql) {
        try {
            Statement stm = this.connection.createStatement();
            return stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
