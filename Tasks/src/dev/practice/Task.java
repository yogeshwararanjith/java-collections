package dev.practice;


enum Status {IN_QUEUE,ASSIGNED,IN_PROGRESS};
enum Priority {HIGH,MEDIUM,LOW};

public class Task implements Comparable<Task> {
    private String project;
    private String description;
    private String assignee;
    private Status status;
    private Priority priority;

    public Task(String assignee, String description, Priority priority, String project, Status status) {
        this.assignee = assignee;
        this.description = description;
        this.priority = priority;
        this.project = project;
        this.status = status;
    }

    public Task(String assignee, String description, Priority priority, String project) {
        this(assignee,description,priority,project,assignee==null?Status.IN_QUEUE:Status.ASSIGNED);
    }

    public Task(String description, Priority priority, String project) {
        this(null,description,priority,project);
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public int compareTo(Task o) {
        int result = this.project.compareTo(o.project);
        if(result == 0){
            result = this.description.compareTo(o.description);
        }
        return result;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %-10s %s".formatted(project,description,assignee,priority,status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return getProject().equals(task.getProject()) && getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getProject().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
