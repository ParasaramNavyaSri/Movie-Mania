package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/movie"; // Ensure this points to your actual DB
        String username = "root";  // Change as needed
        String password = "navya";  // Change as needed
        return DriverManager.getConnection(url, username, password);
    }
}
