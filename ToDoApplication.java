import java.util.*;

class Task{
    String name;
    String priority;

    Task(String name, String priority){
        this.name=name;
        this.priority=priority;
    }

    public String toString() {
        return "Name: " + name + ", Priority: " + priority;
    }
}

class TaskManager{

    static ArrayList<Task> tasks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    void addTasks(){
        System.out.print("Enter task name: ");
        String name = sc.nextLine();
        System.out.print("Enter task priority (high, medium, low): ");
        String priority = sc.nextLine();

        tasks.add(new Task(name, priority));
        System.out.println("Task added successfully!");
    }

    void viewTasks(){
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    
    void editTasks() {
        System.out.print("Enter the number of the task to edit: ");
        int taskNumber = sc.nextInt() - 1;
        Task task = tasks.get(taskNumber);

        System.out.print("Enter new name");
        String newName = sc.nextLine();
        task.name = newName;
            
        System.out.print("Enter new priority ");
        String newPriority = sc.nextLine();
        task.priority = newPriority;
        
        System.out.println("Task updated successfully!");
        
    }
}

class TaskList{
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        
        while (true){
            System.out.println("\nTask List Menu:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Enter your input: ");
    
            int input = taskManager.sc.nextInt();
            taskManager.sc.nextLine();
            
            switch (input) {
                case 1:
                    taskManager.addTasks();
                    break;
                case 2:
                    taskManager.viewTasks();
                    break;
                case 3:
                    taskManager.editTasks();
                    break;
                case 4:
                    deleteTasks();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
    static void deleteTasks() {
        System.out.print("Enter the number of the task to delete: ");
        int taskNumber = sc.nextInt() - 1;
        tasks.remove(taskNumber);
        System.out.println("Task deleted successfully!");
    }

}