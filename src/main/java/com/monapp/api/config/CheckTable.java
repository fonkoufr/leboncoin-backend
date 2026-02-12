package com.monapp.api.config;

import java.sql.*;

public class CheckTable {
    public static void main(String[] args) {
        System.out.println("🔍 Vérification de la table...");

        String sql = "SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'ANNONCES'";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("✅ TROUVÉ ! La table " + rs.getString("TABLE_NAME") + " existe bien.");
                
                // Bonus : Afficher les colonnes
                afficherColonnes(conn);
            } else {
                System.out.println("❌ La table ANNONCES n'existe pas.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void afficherColonnes(Connection conn) throws SQLException {
        String sql = "SELECT COLUMN_NAME, DATA_TYPE FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'ANNONCES'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📋 Colonnes de la table :");
            while(rs.next()) {
                System.out.println("   - " + rs.getString("COLUMN_NAME") + " (" + rs.getString("DATA_TYPE") + ")");
            }
        }
    }
}