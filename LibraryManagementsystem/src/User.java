package src;
import java.sql.*;
public class User {
    public static boolean register(String username, String password) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean login(String username, String password) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return false;
        }
    }
}