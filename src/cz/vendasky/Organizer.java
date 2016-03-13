package cz.vendasky;

import java.io.*;
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

    public void countTotalTime() {
        int i = 0;
        while (this.tasks.size() != 0) {
            removeTasksWithoutDependencies();
            removedTasks = new ArrayList<>();
            System.out.println(i);
            i++;
        }
    }

    public void run(String pathToInputFile, String pathToOutputFile) {
        readFromInputFile(pathToInputFile);
        countTotalTime();
        try {
            PrintWriter writer = new PrintWriter(pathToOutputFile, "UTF-8");
            writer.println(this.totalTime);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void readFromInputFile(String pathToInputFile) {
        int countOfTasks;
        int countOfDependencies;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToInputFile))) {
            line = reader.readLine();
            countOfTasks = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            countOfDependencies = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            line = reader.readLine() + " ";
            for (int i = 0; i < countOfTasks; i++) {
                this.addTask(new Task(i, Integer.parseInt(line.substring(0, line.indexOf(" ")))));
                line = line.substring(line.indexOf(" ") + 1);
            }
            for (int i = 0; i < countOfDependencies; i++) {
                line = reader.readLine();
                int firstTask = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                int secondTask = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                addDependencyToTask(firstTask, secondTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDependencyToTask(int firstTask, int secondTask) {
        for (Task task : this.tasks) {
            if (task.getId() == secondTask) {
                task.addDependency(firstTask);
                return;
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public static void main(String[] args) {
        Organizer organizer = new Organizer();
        organizer.run("/home/vaclav/IdeaProjects/TasksRemaining/inputs/02 (1).in", "/home/vaclav/IdeaProjects/TasksRemaining/outputs/02.txt");
    }

}
