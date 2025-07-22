package src;


import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // Create users table
            String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                               "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                               "username TEXT UNIQUE," +
                               "password TEXT)";
            stmt.execute(userTable);

            // Create books table
            String bookTable = "CREATE TABLE IF NOT EXISTS books (" +
                               "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                               "title TEXT," +
                               "author TEXT," +
                               "available BOOLEAN)";
            stmt.execute(bookTable);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}