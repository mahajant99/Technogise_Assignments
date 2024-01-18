package controller;

import java.util.ArrayList;

import model.Task;
import view.TaskUI;;

public class TaskManager {
    
    private ArrayList<Task> tasks = new ArrayList<>();

    public String addTasks(TaskUI taskUI){
        String name = taskUI.getTaskName();
        int priorityChoice = taskUI.getPriorityChoice();
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

    public String editTasks(TaskUI taskUI) {
        int taskNumber = taskUI.getTaskNumber();

        if (isValidTaskNumber(taskNumber)){
            Task task = tasks.get(taskNumber);

            String newName = taskUI.getTaskName();
            task.setName(newName);

            int priorityChoice = taskUI.getPriorityChoice();
            String newPriority = getPriorityString(priorityChoice);

            task.setPriority(newPriority);

            return "Task updated successfully!";
        } else {
            return "Invalid task number!";
        }
    }

    public String deleteTasks(TaskUI taskUI) {
        int taskNumber = taskUI.getTaskNumber();

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
}
