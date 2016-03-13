package cz.vendasky;

import java.util.ArrayList;

public class Task {

    private int id;
    private ArrayList<Integer> dependencies;
    private int timeNeeded;

    public Task(int id, int timeNeeded) {
        this.id = id;
        this.timeNeeded = timeNeeded;
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(int taskId) {
        dependencies.add(taskId);
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Integer> getDependencies() {
        return this.dependencies;
    }

    public int getTimeNeeded() {
        return timeNeeded;
    }


    public void removeDependency(int id) {
        int indexOfId = this.dependencies.indexOf(id);
        this.dependencies.remove(indexOfId);
    }

    @Override
    public boolean equals(Object task) {
        return this.id == ((Task) task).getId();
    }

}
