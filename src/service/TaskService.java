package service;

import java.util.List;

import model.Task;
import repository.TaskRepository;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteTask(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
    
}
