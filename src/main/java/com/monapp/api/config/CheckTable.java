package com.monapp.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CheckTable {

    private static final Logger log = LoggerFactory.getLogger(CheckTable.class);

    public static void main(String[] args) {
        log.info("Vérification de la table...");

        String sql = "SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'ANNONCES'";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                log.info("TROUVÉ ! La table {} existe bien.", rs.getString("TABLE_NAME"));
                afficherColonnes(conn);
            } else {
                log.warn("La table ANNONCES n'existe pas.");
            }

        } catch (Exception e) {
            log.error("Erreur lors de la vérification de la table", e);
        }
    }

    private static void afficherColonnes(Connection conn) throws SQLException {
        String sql = "SELECT COLUMN_NAME, DATA_TYPE FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'ANNONCES'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            log.info("Colonnes de la table :");
            while (rs.next()) {
                log.info("  - {} ({})", rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"));
            }
        }
    }
}
