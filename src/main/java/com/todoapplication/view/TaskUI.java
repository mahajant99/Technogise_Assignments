package com.todoapplication.view;

import java.util.Scanner;

public class TaskUI {
    
    private Scanner sc = new Scanner(System.in);

    public String getUsername() {
        System.out.print("Enter username: ");
        sc.nextLine();
        return sc.nextLine();
    }

    public String getPassword() {
        System.out.print("Enter password: ");
        return sc.nextLine();
    }

    public void displayLoginMenu() {
        System.out.println("\nLogin Menu:");
        System.out.println("1. Login");
        System.out.println("2. Signup");
    }

    public int getLoginChoice() {
        System.out.print("Enter your choice (1/2): ");
        return sc.nextInt();
    }

    public String getTaskName(){
        System.out.print("Enter task name: ");
        sc.nextLine();
        return sc.nextLine();
    }

    public int getPriorityChoice(){
        System.out.println("Choose task priority: ");
        System.out.println("1. High");
        System.out.println("2. Medium");
        System.out.println("3. Low");
        System.out.print("Enter your choice (1/2/3): ");
        return sc.nextInt();
    }

    public int getTaskNumber(){
        System.out.print("Enter the number of the task to edit: ");
        return sc.nextInt() - 1;
    }

    public void displayTaskListMenu(){
        System.out.println("\nTask List Menu:");
        System.out.println("1. Add task");
        System.out.println("2. View tasks");
        System.out.println("3. Edit task");
        System.out.println("4. Delete task");
        System.out.println("5. Mark task as completed");
        System.out.println("6. Exit");
    }

    public int getUserInput(){
        System.out.print("Enter your input: ");
        return sc.nextInt();
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public String getJwtToken() {
        System.out.print("Enter JWT token: ");
        sc.nextLine();
        return sc.nextLine();
    }

}
