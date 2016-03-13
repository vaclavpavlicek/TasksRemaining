package cz.vendasky;

import java.util.ArrayList;

public class Organizer {

    private ArrayList<Task> tasks;

    public Organizer() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
