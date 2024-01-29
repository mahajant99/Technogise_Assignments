package com.todoapplication.controller;

import java.util.ArrayList;
import java.util.List;

import com.todoapplication.model.Task;
import com.todoapplication.model.TaskPriority;
import com.todoapplication.repository.DatabaseManager;
import com.todoapplication.repository.UserRepository;
import com.todoapplication.service.TaskService;
import com.todoapplication.view.TaskUI;
import com.todoapplication.service.UserService;
import com.todoapplication.jwt.JwtUtils;

public class TaskManager {

    private final TaskService taskService;
    private final UserService userService;
    private boolean loggedIn;
    private String token;
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
        this.loggedIn = false;
    }

    public boolean login(TaskUI taskUI) {
        String username = taskUI.getUsername();
        String password = taskUI.getPassword();
        this.token = userService.signIn(username, password);
        this.loggedIn = (this.token != null);
        return loggedIn;
    }
    

    public void signUp(TaskUI taskUI) {
        String username = taskUI.getUsername();
        String password = taskUI.getPassword();
        userService.signUp(username, password);
        taskUI.displayMessage("Signup successful! Please login.");
    }

    public void showTaskMenu(TaskUI taskUI) {
        if (this.loggedIn) {
            taskUI.displayTaskListMenu();
        } else {
            taskUI.displayMessage("Please login to access tasks.");
        }
    }

    public String performTask(int input, TaskUI taskUI) {
        if (this.loggedIn) {
            if (verifyToken(taskUI)) {
                switch (input) {
                    case 1:
                        return addTask(taskUI);
                    case 2:
                        return viewTasks();
                    case 3:
                        return editTask(taskUI);
                    case 4:
                        return deleteTask(taskUI);
                    case 5:
                        return markTaskAsCompleted(taskUI);
                    case 6:
                        System.exit(0);
                    default:
                        return "Invalid input. Please try again.";
                }
            } else {
                return "JWT verification failed. Please login again.";
            }
        } else {
            return "Please login to access tasks.";
        }
    }

    public String addTask(TaskUI taskUI){
        if (this.loggedIn) {
            String name = taskUI.getTaskName();
            TaskPriority priority = getPriorityChoice(taskUI.getPriorityChoice());

            Task task = new Task(name, priority);
            taskService.addTask(task);
            return "Task added successfully!";
        } else {
            return "Please login to add tasks.";
        }
    }

    public String viewTasks(){
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            return "No tasks in the list.";
        } else {
            StringBuilder result = new StringBuilder("Your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return result.toString();
        }
    }

    public String editTask(TaskUI taskUI) {
        int taskNumber = taskUI.getTaskNumber();

        if (isValidTaskNumber(taskNumber)){
            Task task = tasks.get(taskNumber);

            String newName = taskUI.getTaskName();
            task.setName(newName);

            TaskPriority newPriority = getPriorityChoice(taskUI.getPriorityChoice());

            task.setPriority(newPriority);

            return "Task updated successfully!";
        } else {
            return "Invalid task number!";
        }
    }

    public String deleteTask(TaskUI taskUI) {
        int taskNumber = taskUI.getTaskNumber();

        if(isValidTaskNumber(taskNumber)){
            taskService.deleteTask(taskNumber);
            return "Task deleted successfully!";
        } else {
            return "Invalid task number!";
        }
    }

    private boolean isValidTaskNumber(int taskNumber){
        return taskNumber >=0 && taskNumber < tasks.size();
    }

    private TaskPriority getPriorityChoice(int priorityChoice) {
        switch (priorityChoice) {
            case 1:
                return TaskPriority.High;
            case 2:
                return TaskPriority.Medium;
            case 3:
                return TaskPriority.Low;
            default:
                return null;
        }
    }

    public String markTaskAsCompleted(TaskUI taskUI){
        int taskNumber = taskUI.getTaskNumber();

        if (isValidTaskNumber(taskNumber)) {
            Task task = tasks.get(taskNumber);
            task.markAsCompleted();
            return "Task marked as completed!";
        } else {
            return "Invalid task number.";
        }
    }

    private boolean verifyToken(TaskUI taskUI) {
        String enteredToken = taskUI.getJwtToken();
        if (enteredToken == null || enteredToken.isEmpty()) {
            return false;
        }
        return JwtUtils.verifyJwt(enteredToken, "Test JWT", JwtUtils.getSecretKey());
    }    

    public void closeDatabaseConnection() {
        DatabaseManager.closeConnection();
    }
}
