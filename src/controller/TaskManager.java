package controller;

import java.util.ArrayList;

import model.Task;
import model.TaskPriority;
import repository.DatabaseManager;
import service.TaskService;
import view.TaskUI;

public class TaskManager {

    private final TaskService taskService;
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager(TaskService taskService) {
        this.taskService = taskService;
    }

    public String addTask(TaskUI taskUI){
        String name = taskUI.getTaskName();
        TaskPriority priority = getPriorityChoice(taskUI.getPriorityChoice());

        Task task = new Task(name, priority);
        taskService.addTask(task);
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
            tasks.remove(taskNumber);
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

    public void closeDatabaseConnection() {
        DatabaseManager.closeConnection();
    }
}
