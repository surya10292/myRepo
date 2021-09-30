package com.ge.PostgresDbIntegration;

public class DBData {
    public static final String DB_URL = "jdbc:postgresql://eis-common-postgres-postgresql.edison-system:"+ System.getenv("PGPORT") + "/"+  System.getenv("PGDATABASE");
    public static final String DB_USER_NAME = System.getenv("PGUSER");
    public static final String DB_PASSWORD = System.getenv("PGPASSWORD");
}

