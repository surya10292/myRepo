package com.ge.PostgresDbIntegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBColumn {
    public static void CreateDBTable() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBData.DB_URL, DBData.DB_USER_NAME, DBData.DB_PASSWORD);

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ORG " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.\n");

            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public static void InsertDBRow() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBData.DB_URL, DBData.DB_USER_NAME, DBData.DB_PASSWORD);

            stmt = c.createStatement();
            String sql = "INSERT INTO ORG (ID,NAME) "
                    + "VALUES (100, 'Test1' ) "
                    + "ON CONFLICT ON CONSTRAINT org_pkey "
                    + "DO NOTHING;";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ORG (ID,NAME) "
                    + "VALUES (200, 'Test2' ) "
                    + "ON CONFLICT ON CONSTRAINT org_pkey "
                    + "DO NOTHING;";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}