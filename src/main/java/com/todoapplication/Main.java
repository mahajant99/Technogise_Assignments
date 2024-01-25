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

        while (true) {
            taskUI.displayLoginMenu();
            int loginChoice = taskUI.getLoginChoice();

            switch (loginChoice) {
                case 1:
                    if (taskManager.login(taskUI)) {
                        taskManager.showTaskMenu(taskUI);
                        int input = taskUI.getUserInput();

                        String result = taskManager.performTask(input, taskUI);
                        taskUI.displayMessage(result);
                    } else {
                        taskUI.displayMessage("Login failed. Please try again.");
                    }
                    break;
                case 2:
                    taskManager.signUp(taskUI);
                    break;
                default:
                    taskUI.displayMessage("Invalid choice. Please try again.");
            }
        }
    }
}
