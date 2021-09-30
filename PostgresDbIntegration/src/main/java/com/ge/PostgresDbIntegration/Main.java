package com.ge.PostgresDbIntegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

   public static void main(String args[]) {
        try {

            //First check your are able to connect to your database
            testConnection();


            //Clear previous contents of table before inserting new records
            clearDBContents();


            //Now create the database table
            DBColumn.CreateDBTable();

            //Insert rows
            DBColumn.InsertDBRow();

            //Print table contents
            printDBContents();

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public static void testConnection()
    {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBData.DB_URL, DBData.DB_USER_NAME, DBData.DB_PASSWORD);
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Database Connection Successful.\n");
    }

    public static void printDBContents()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBData.DB_URL, DBData.DB_USER_NAME, DBData.DB_PASSWORD);
            System.out.println(">>Database Contents: ");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ORG;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                System.out.println( "ID = " + id + " NAME = " + name);
            }

            stmt.close();
            rs.close();
            c.close();

            System.out.println("<<Database Contents display done.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static void clearDBContents()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBData.DB_URL, DBData.DB_USER_NAME, DBData.DB_PASSWORD);

            System.out.println(">>Deleting Database rows.");
            stmt = c.createStatement();
            String sql = "DELETE FROM ORG";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

            System.out.println("<<Database rows deleted:");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
