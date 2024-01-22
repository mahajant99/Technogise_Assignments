package unit_testing;

import java.util.List;

import controller.*;
import model.*;
import repository.*;
import service.*;
import view.*;


public class TestTaskService {

    public static void main(String[] args) {
        testAddTask();
    }

    private static void testAddTask() {
        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        taskService.addTask("Test Task", "High");

        List<Task> tasks = taskRepository.getAllTasks();

        if (tasks.size() == 1) {
            Task addedTask = tasks.get(0);
            if ("Test Task".equals(addedTask.getName()) &&
                TaskPriority.High.equals(addedTask.getPriority()) &&
                TaskStatus.Pending.equals(addedTask.getStatus())) {
                System.out.println("testAddTask passed successfully!");
            } else {
                System.out.println("testAddTask failed: Incorrect task details.");
            }
        } else {
            System.out.println("testAddTask failed: Task not added.");
        }
    }
}
