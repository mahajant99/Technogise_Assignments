package com.todoapplication;

import com.todoapplication.controller.TaskManager;
import com.todoapplication.repository.DatabaseManager;
import com.todoapplication.repository.TaskRepository;
import com.todoapplication.repository.UserRepository;
import com.todoapplication.service.TaskService;
import com.todoapplication.service.UserService;
import com.todoapplication.view.TaskUI;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserService(new UserRepository(DatabaseManager.getConnection()));
        TaskService taskService = new TaskService(new TaskRepository(DatabaseManager.getConnection()));
        TaskManager taskManager = new TaskManager(taskService, userService);
        TaskUI taskUI = new TaskUI();

        boolean loggedIn = false;
        String token = null;

        while (true) {
            if (!loggedIn) {
                taskUI.displayLoginMenu();
                int loginChoice = taskUI.getLoginChoice();

                switch (loginChoice) {
                    case 1:
                        String username = taskUI.getUsername();
                        String password = taskUI.getPassword();

                        token = userService.signIn(username, password);
                        if (token != null) {
                            taskUI.displayMessage("Login successful. Token: " + token);
                            loggedIn = true;
                        } else {
                            taskUI.displayMessage("Invalid credentials. Please try again.");
                        }
                        break;
                    case 2:
                        taskManager.signUp(taskUI);
                        break;
                    default:
                        taskUI.displayMessage("Invalid choice. Please try again.");
                }
            } else {
                taskUI.displayTaskListMenu();
                int taskListChoice = taskUI.getUserInput();

                String result;
                switch (taskListChoice) {
                    case 1:
                        result = taskManager.addTask(taskUI);
                        break;
                    case 2:
                        result = taskManager.viewTasks();
                        break;
                    case 3:
                        result = taskManager.editTask(taskUI);
                        break;
                    case 4:
                        result = taskManager.deleteTask(taskUI);
                        break;
                    case 5:
                        result = taskManager.markTaskAsCompleted(taskUI);
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        result = "Invalid input. Please try again.";
                }
                taskUI.displayMessage(result);
            }
        }
    }
}
