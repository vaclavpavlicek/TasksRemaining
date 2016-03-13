package cz.vendasky;

import java.util.ArrayList;

public class Organizer {

    private ArrayList<Task> tasks;
    private ArrayList<Task> removedTasks;
    private int totalTime;

    public Organizer() {
        this.tasks = new ArrayList<>();
        this.removedTasks = new ArrayList<>();
        this.totalTime = 0;
    }

    public void removeTasksWithoutDependencies() {
        for (int i = 0; i < tasks.size(); i++) {
            if (this.tasks.get(i).getDependencies().size() == 0) {
                this.removedTasks.add(this.tasks.get(i));
                this.tasks.remove(i);
                i--;
            }
        }
        for (Task removedTask : this.removedTasks) {
            removeTaskDependencies(removedTask.getId());
        }
        increaseTotalTime();
    }

    public void removeTaskDependencies(int id) {
        this.tasks.stream().filter(task -> task.getDependencies().contains(id)).forEach(task -> {
            task.removeDependency(id);
        });
    }

    private void increaseTotalTime() {
        int maxTime = 0;
        for (Task removedTask : removedTasks) {
            if (removedTask.getTimeNeeded() > maxTime) {
                maxTime = removedTask.getTimeNeeded();
            }
        }
        this.totalTime += maxTime;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

}
