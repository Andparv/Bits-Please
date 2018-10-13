package com.example.demo.entities;

import java.sql.*;

public class Registration {
    public void submitData(String user, String password, String email) throws SQLException, ClassNotFoundException{
        Connection con = null;

        try {
            Class.forName("oracle.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        con = DriverManager.getConnection("mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_6e9e2e43ea89231?DATABASE_USER=b864d9547483d4&DATABASE_PASSWORD=900c8909");
        Statement statement = con.createStatement();

        String command = "INSERT INTO users(name,password,email) " +
                "VALUES ("+user+","+password+","+email+");";
        statement.executeUpdate(command);
        con.close();
    }

}

//https://stackoverflow.com/questions/16747570/how-i-can-connect-html-to-java-to-add-values-in-database

