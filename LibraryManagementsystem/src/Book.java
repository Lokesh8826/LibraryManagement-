package src;

import java.sql.*;;

public class Book {
    public static void addBook(String title, String author) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books(title, author, available) VALUES (?, ?, ?)")) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setBoolean(3, true);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Add book failed: " + e.getMessage());
        }
    }

    public static void viewBooks() {
        try (Connection conn = DBHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            System.out.println("Books in Library:");
            while (rs.next()) {
                System.out.printf("ID: %d | Title: %s | Author: %s | Available: %s\n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getBoolean("available") ? "Yes" : "No");
            }
        } catch (SQLException e) {
            System.out.println("View books failed: " + e.getMessage());
        }
    }

    public static void borrowBook(int bookId) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE books SET available = false WHERE id = ? AND available = true")) {
            pstmt.setInt(1, bookId);
            int rows = pstmt.executeUpdate();
            if (rows > 0)
                System.out.println("Book borrowed successfully.");
            else
                System.out.println("Book is not available.");
        } catch (SQLException e) {
            System.out.println("Borrow failed: " + e.getMessage());
        }
    }

    public static void returnBook(int bookId) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE books SET available = true WHERE id = ?")) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
            System.out.println("Book returned successfully.");
        } catch (SQLException e) {
            System.out.println("Return failed: " + e.getMessage());
        }
    }
}