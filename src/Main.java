import controller.TaskManager;
import view.TaskUI;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        TaskUI taskUI = new TaskUI();

        while (true){

            taskUI.displayTaskListMenu();
            int input = taskUI.getUserInput();
            
            String result;
            switch (input) {
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
