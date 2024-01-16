import java.util.*;

class Task{
    String Name;
    String priority;

    Task(String Name, String priority){
        this.Name=Name;
        this.priority=priority;
    }

    public String toString() {
        return "Name: " + Name + ", Priority: " + priority;
    }
}

class TaskList{
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            System.out.println("\nTask List Menu:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Enter your input: ");
    
            int input = sc.nextInt();
            sc.nextLine();
            
            switch (input) {
                case 1:
                    addTasks();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    editTasks();
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

    static void addTasks(){
        System.out.print("Enter task name: ");
        String Name = sc.nextLine();
        System.out.print("Enter task priority (high, medium, low): ");
        String priority = sc.nextLine();

        tasks.add(new Task(Name, priority));
        System.out.println("Task added successfully!");
    }

    static void viewTasks(){
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    static void editTasks() {
        System.out.print("Enter the number of the task to edit: ");
        int taskNumber = sc.nextInt() - 1;
        Task task = tasks.get(taskNumber);

        System.out.print("Enter new name");
        String newName = sc.nextLine();
        task.Name = newName;
            
        System.out.print("Enter new priority ");
        String newPriority = sc.nextLine();
        task.priority = newPriority;
        
        System.out.println("Task updated successfully!");
        
    }

    static void deleteTasks() {
        System.out.print("Enter the number of the task to delete: ");
        int taskNumber = sc.nextInt() - 1;
        tasks.remove(taskNumber);
        System.out.println("Task deleted successfully!");
    }

}