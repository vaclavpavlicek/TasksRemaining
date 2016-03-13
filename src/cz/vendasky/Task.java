package cz.vendasky;

import java.util.ArrayList;

public class Task {

    private int id;
    private ArrayList<Integer> dependencies;

    public Task(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object task) {
        return this.id == ((Task) task).getId();
    }

}
