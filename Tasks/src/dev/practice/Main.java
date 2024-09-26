package dev.practice;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Set<Task> taskList = TaskData.getTasks("all");
        Comparator<Task> sorter = Comparator.comparing(Task::getPriority).thenComparing(Task::getStatus);
//        Comparator<Task> sortByStatus = Comparator.comparing(Task::getStatus);
        sortAndPrint("All tasks",taskList,sorter);

        Set<Task> annsTask = TaskData.getTasks("ann");
        sortAndPrint("Ann's Task",annsTask,sorter);

        Set<Task> bobsTask = TaskData.getTasks("bob");
        sortAndPrint("Bob's Task",bobsTask,sorter);

        Set<Task> carolsTask = TaskData.getTasks("carol");
        sortAndPrint("Carol's Task",carolsTask,sorter);

        List<Set<Task>> unionTasks = List.of(annsTask,bobsTask,carolsTask);
        Set<Task>  assignedTasks = getUnion(unionTasks);
        sortAndPrint("Assigned Tasks",assignedTasks);

        Set<Task> everyTask = getUnion(List.of(taskList,assignedTasks));
        sortAndPrint("Every Tasks",everyTask);

        Set<Task> missingTasks = getDifference(everyTask,taskList);
        sortAndPrint("Missing tasks",missingTasks);

        Set<Task> unassignedTask = getDifference(everyTask,assignedTasks);
        sortAndPrint("Unassigned",unassignedTask,sorter);
    }

    private static void sortAndPrint(String header, Collection<Task> collection){
        sortAndPrint(header,collection,null);
    }
    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {
        System.out.println("-".repeat(90));
        System.out.println(header);
        System.out.println("-".repeat(90));
        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    private static Set<Task> getUnion(List<Set<Task>> sets){
        Set<Task> union = new HashSet<>();
        for (var taskSet: sets){
            union.addAll(taskSet);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a,Set<Task> b){
        Set<Task> intersect = new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }
    private static Set<Task> getDifference(Set<Task> a,Set<Task> b) {
        Set<Task> difference = new HashSet<>(a);
        difference.removeAll(b);
        return difference;
    }
}