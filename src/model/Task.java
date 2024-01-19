package model;

public class Task {
    private int id;
    private String name;
    private TaskPriority priority;
    private TaskStatus status;

    public Task(String name, TaskPriority priority){
        this.name=name;
        this.priority=priority;
        this.status=TaskStatus.Pending;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public TaskPriority getPriority(){
        return priority;
    }

    public TaskStatus geStatus(){
        return status;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPriority(TaskPriority priority){
        this.priority=priority;
    }

    public void markAsCompleted(){
        this.status = TaskStatus.Completed;
    }

    public String toString() {
        return "Name: " + name + ", Priority: " + priority + ", Status: " + status;
    }
}
