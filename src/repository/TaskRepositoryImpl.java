package repository;

import model.*;

import java.sql.*;

public class TaskRepositoryImpl implements TaskRepository{

    private final Connection connection;

    public TaskRepositoryImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (name, priority, status) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getPriority().toString());
            preparedStatement.setString(3, task.geStatus().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        task.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
