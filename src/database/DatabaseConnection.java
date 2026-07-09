package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Trigger analisi SonarCloud
public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/sedutelaurea";
    private static final String USER = "postgres";
    private static final String PASSWORD = "201106";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
            return null;
        }
    }
}