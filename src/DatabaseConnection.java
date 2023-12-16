import java.sql.*;

public class DatabaseConnection {
    private Connection connection;
    public DatabaseConnection() {
        String DB_URL = "jdbc:mysql://localhost:3306/test_db";
        String DB_USER = "root";
        String DB_PASSWORD = "griqdfaz83";
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection.");
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close database connection.");
        }
    }

    public int executeUpdate(String query, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i+1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute update.");
        }
    }


    public ResultSet queryItem(String tableName, String fieldName, String fieldValue) {
        String query = "SELECT * FROM ? WHERE ? = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tableName);
            statement.setString(2, fieldName);
            statement.setString(3, fieldValue);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query.");
        }
    }

    public ResultSet queryItems(String tableName) {
        String query = "SELECT * FROM ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tableName);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query.");
        }
    }

    
}
