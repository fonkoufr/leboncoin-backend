package com.monapp.api.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // METS TON CHEMIN WALLET ICI (Avec des / pas des \)
    private static final String CHEMIN_WALLET = "C:/Projets/Wallet_Leboncoin/Wallet_OK9S903RHPADR9PI"; 
    private static final String PASSWORD = "Djoussi2017@"; 
    private static final String DB_ALIAS = "ok9s903rhpadr9pi_high"; 

    private static final String DB_URL = "jdbc:oracle:thin:@" + DB_ALIAS + "?TNS_ADMIN=" + CHEMIN_WALLET;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(DB_URL, "ADMIN", PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver manquant", e);
        }
    }
}