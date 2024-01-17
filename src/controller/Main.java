package controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        
        while (true){
            System.out.println("\nTask List Menu:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Mark task as completed");
            System.out.println("6. Exit");
            System.out.print("Enter your input: ");

            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            
            String result;
            switch (input) {
                case 1:
                    result = taskManager.addTasks();
                    break;
                case 2:
                    result = taskManager.viewTasks();
                    break;
                case 3:
                    result = taskManager.editTasks();
                    break;
                case 4:
                    result = taskManager.deleteTasks();
                    break;
                case 5: 
                    result = taskManager.markTaskAsCompleted();
                    break;
                case 6:
                    System.exit(0);
                default:
                result = "Invalid input. Please try again.";
            }
            System.out.println(result);
        }
    }
}
