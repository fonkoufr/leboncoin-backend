package com.monapp.api.config;
import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {
        System.out.println("⏳ Test VS Code...");
        try (Connection conn = DatabaseManager.getConnection()) {
            System.out.println("✅ SUCCÈS ! Connecté à Oracle Cloud.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}