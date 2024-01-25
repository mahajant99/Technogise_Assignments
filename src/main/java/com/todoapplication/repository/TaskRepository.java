package com.todoapplication.repository;

import com.todoapplication.model.Task;
import com.todoapplication.model.TaskPriority;
import com.todoapplication.model.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository{

    private Connection connection;

    public TaskRepository(Connection connection){
        this.connection = connection;
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (name, priority, status) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getPriority().toString());
            preparedStatement.setString(3, task.getStatus().toString());

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

    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                TaskPriority priority = TaskPriority.valueOf(resultSet.getString("priority"));
                TaskStatus status = TaskStatus.valueOf(resultSet.getString("status"));

                Task task = new Task(name, priority);
                task.setId(id);
                task.setStatus(status);

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
