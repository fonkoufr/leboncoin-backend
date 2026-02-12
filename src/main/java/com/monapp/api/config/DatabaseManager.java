package com.monapp.api.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // ✅ On utilise System.getenv pour lire les variables configurées sur Render
    private static final String DB_URL = System.getenv("SPRING_DATASOURCE_URL");
    private static final String USER = System.getenv("SPRING_DATASOURCE_USERNAME");
    private static final String PASSWORD = System.getenv("SPRING_DATASOURCE_PASSWORD");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            // ✅ On vérifie que les variables ne sont pas nulles avant de se connecter
            if (DB_URL == null || USER == null || PASSWORD == null) {
                throw new SQLException("Erreur : Variables d'environnement manquantes sur le serveur !");
            }
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver Oracle manquant dans le projet", e);
        }
    }
}