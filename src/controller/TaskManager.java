package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Task;

public class TaskManager {
    
    private ArrayList<Task> tasks = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public String addTasks(){
        System.out.print("Enter task name: ");
        String name = sc.nextLine();

        System.out.println("Choose task priority: ");
        System.out.println("1. High");
        System.out.println("2. Medium");
        System.out.println("3. Low");
        System.out.print("Enter your choice (1/2/3): ");
        int priorityChoice = sc.nextInt();
        sc.nextLine();

        String priority = getPriorityString(priorityChoice);

        Task task = new Task(name, priority);
        tasks.add(task);
        return "Task added successfully!";
    }

    public String viewTasks(){
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

    public String editTasks() {
        System.out.print("Enter the number of the task to edit: ");
        int taskNumber = sc.nextInt() - 1;
        sc.nextLine();

        if (isValidTaskNumber(taskNumber)){
            Task task = tasks.get(taskNumber);

            System.out.print("Enter new name: ");
            String newName = sc.nextLine();
            task.setName(newName);

            System.out.println("Choose new task priority: ");
            System.out.println("1. High");
            System.out.println("2. Medium");
            System.out.println("3. Low");
            System.out.print("Enter your choice (1/2/3): ");
            int priorityChoice = sc.nextInt();
            sc.nextLine();

            String newPriority = getPriorityString(priorityChoice);
            task.setPriority(newPriority);

            return "Task updated successfully!";
        } else {
            return "Invalid task number!";
        }
    }

    public String deleteTasks() {
        System.out.print("Enter the number of the task to delete: ");
        int taskNumber = sc.nextInt() - 1;
        sc.nextLine();

        if(isValidTaskNumber(taskNumber)){
            tasks.remove(taskNumber);
            return "Task deleted successfully!";
        } else {
            return "Invalid task number!";
        }
    }

    private boolean isValidTaskNumber(int taskNumber){
        return taskNumber >=0 && taskNumber < tasks.size();
    }

    private String getPriorityString(int priorityChoice){
        switch (priorityChoice) {
            case 1:
                return "High";
            case 2:
                return "Medium";
            case 3:
                return "Low";
            default:
                return "Invalid Priority";
        }
    }

    public String markTaskAsCompleted(){
        System.out.print("Enter the number of the task to mark as completed: ");
        int taskNumber = sc.nextInt() - 1;
        sc.nextLine();

        if (isValidTaskNumber(taskNumber)) {
            Task task = tasks.get(taskNumber);
            task.markAsCompleted();
            return "Task marked as completed!";
        } else {
            return "Invalid task number.";
        }
    }
}
