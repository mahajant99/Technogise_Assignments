package repository;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
