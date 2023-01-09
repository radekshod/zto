package org.zto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
    int id;
    String username;
    String email;
    String pass;
    Boolean enabled;

    public Person(int id, String username, String email, String pass, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.enabled = enabled;
    }

    // DB operations

    public void create(DatabaseConnector dbc) {
        String insert = new StringBuilder()
                .append("insert into person values ('")
                .append(this.username).append("', '")
                .append(this.email).append("', '")
                .append(this.pass).append("', ")
                .append(this.enabled.toString())
                .append(", ").append(this.id)
                .append(")").toString();
        dbc.createRecord(insert);
    }

    public void read(DatabaseConnector dbc) throws SQLException {
        String sql = "select * from person where username = '" + this.username + "'";
        ResultSet rs = dbc.searchRecord(sql);

        if (rs.next() == false) {
            System.out.println("ResultSet is empty.");
        } else {
            do {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String pass = rs.getString("password");
                boolean enabled = rs.getBoolean("enabled");
                System.out.println(new StringBuilder()
                        .append("User details: \n")
                        .append("{\n").append("id: ").append(id)
                        .append(", username: ").append(username)
                        .append(", email: ").append(email)
                        .append(", password: ").append(pass)
                        .append(", enabled: ").append(enabled)
                        .append("\n}").toString());
            } while (rs.next());
        }
    }

    public void update(DatabaseConnector dbc) {
        String sql = "update person set email = '" + this.email + "', password = '" + this.pass + "', enabled = '" + this.enabled +"'  where username = '" + this.username + "'";
        int nr;
        nr = dbc.updateRecord(sql);
        System.out.println("Update records : " + nr);
    }

    public void delete(DatabaseConnector dbc) {
        String sql = "delete from person where username = '" + this.username + "'";
        int nr;
        nr = dbc.deleteRecord(sql);
        System.out.println("Delete records : " + nr);
    }

    public static void addAge(DatabaseConnector dbc, String username, int age) {
        String sql = "insert into ages values ('" +
                username + "', '" +
                age + "')";
        dbc.createRecord(sql);
    }

    public static Integer getAge(DatabaseConnector dbc, String username) throws SQLException {
        String sql = "select * from ages where username = '" + username + "'";
        ResultSet rs = dbc.searchRecord(sql);
        int age = 0;
        while (rs.next()) {
            age = rs.getInt("age");
            System.out.println("Predicted age: " + age + " for user: " + username);
        }
        return age;
    }

    public static void deleteAge(DatabaseConnector dbc, String username) {
        String sql = "delete from ages where username = '" + username + "'";
        int nr = dbc.deleteRecord(sql);
        System.out.println("Deleted records: " + nr);
    }
}

