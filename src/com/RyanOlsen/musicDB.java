package com.RyanOlsen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class musicDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/musicDB";
    static final String USER = "Java";
    static final String PASSWORD = System.getenv("MySQL_Java_password");

    public static String getTrack(Integer track) {

            String r = "" ;

            try {
                Class.forName(JDBC_DRIVER); //try catch on it's own
            } catch (Exception ex) {
                System.out.println("Driver not instantiated");
            }

            try {
                Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                String prepSelect = "SELECT TrackName FROM Track WHERE '" + (track) + "' = TrackID";
                PreparedStatement psSelect = connection.prepareStatement(prepSelect);
                ResultSet rs = psSelect.executeQuery();
                r = rs.toString();
                psSelect.close();
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("mySQL unable to form connection");
            }
            return r;

        }

}
