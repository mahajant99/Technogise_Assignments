enum TaskStatus{
    Pending, Completed
}

public class Task {
    private String name;
    private String priority;
    private TaskStatus status;

    Task(String name, String priority){
        this.name=name;
        this.priority=priority;
        this.status=TaskStatus.Pending;
    }

    public String getName(){
        return name;
    }

    public String getPriority(){
        return priority;
    }

    public TaskStatus geStatus(){
        return status;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPriority(String priority){
        this.priority=priority;
    }

    public void markAsCompleted(){
        this.status = TaskStatus.Completed;
    }

    public String toString() {
        return "Name: " + name + ", Priority: " + priority + ", Status: " + status;
    }
}
