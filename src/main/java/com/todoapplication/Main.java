package com.todoapplication;

import com.todoapplication.controller.TaskManager;
import com.todoapplication.repository.DatabaseManager;
import com.todoapplication.repository.TaskRepository;
import com.todoapplication.repository.UserRepository;
import com.todoapplication.service.TaskService;
import com.todoapplication.service.UserService;
import com.todoapplication.view.TaskUI;
import com.todoapplication.jwt.JwtUtils;;

public class Main {

    public static void main(String[] args) {
        TaskService taskService = new TaskService(new TaskRepository(DatabaseManager.getConnection()));
        UserService userService = new UserService(new UserRepository(DatabaseManager.getConnection()));
        TaskManager taskManager = new TaskManager(taskService, userService);
        TaskUI taskUI = new TaskUI();

        boolean loggedIn = false;
        String token = null;

        while (true) {
            taskUI.displayLoginMenu();
            int loginChoice = taskUI.getLoginChoice();

            switch (loginChoice) {
                case 1: // Sign In
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
                case 2: // Sign Up
                    taskManager.signUp(taskUI);
                    break;
                default:
                    taskUI.displayMessage("Invalid choice. Please try again.");
            }

            while (loggedIn) {
                taskUI.displayTaskListMenu();
                int taskListChoice = taskUI.getUserInput();

                String result;
                switch (taskListChoice) {
                    case 1:
                        String enteredToken = taskUI.getJwtToken();
                        if (JwtUtils.verifyJwt(enteredToken, "Test JWT", JwtUtils.getSecretKey())) {
                            result = taskManager.addTask(taskUI);
                        } else {
                            result = "JWT verification failed. Please try again.";
                        }
                        break;
                        case 2:
                        result = taskManager.performTask(2, taskUI);
                        break;
                    case 3:
                        result = taskManager.performTask(3, taskUI);
                        break;
                    case 4:
                        result = taskManager.performTask(4, taskUI);
                        break;
                    case 5:
                        result = taskManager.performTask(5, taskUI);
                        break;
                    case 6:
                        result = taskManager.performTask(taskListChoice, taskUI);
                        break;
                    default:
                        result = "Invalid input. Please try again.";
                }
                taskUI.displayMessage(result);
            }
        }
    }
}
