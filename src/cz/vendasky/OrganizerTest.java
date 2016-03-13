package cz.vendasky;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrganizerTest {

    Organizer organizer;

    @Before
    public void setUp() {
        organizer = new Organizer();
    }

    @Test
    public void shouldAddTaskIntoTasksList() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task expectedTask = new Task(0, 6);
        Task actualTask = new Task(0, 5);
        tasks.add(expectedTask);
        organizer.addTask(actualTask);
        assertEquals(tasks, organizer.getTasks());
    }

    @Test
    public void shouldRemoveTasksWithoutDependencies() {
        ArrayList<Task> expectedTasks = new ArrayList<>();
        Task firstTask = new Task(0, 6);
        Task secondTask = new Task(1, 5);
        secondTask.addDependency(0);
        expectedTasks.add(secondTask);
        organizer.addTask(firstTask);
        organizer.addTask(secondTask);
        organizer.removeTasksWithoutDependencies();
        assertEquals(expectedTasks, organizer.getTasks());
        assertEquals(6, organizer.getTotalTime());
    }

    @Test
    public void shouldRemoveDoneTasksFromDependencies() {
        ArrayList<Task> expectedTasks = new ArrayList<>();
        Task firstTask = new Task(0, 6);
        Task secondTask = new Task(1, 5);
        secondTask.addDependency(0);
        expectedTasks.add(firstTask);
        expectedTasks.add(secondTask);
        organizer.addTask(firstTask);
        organizer.addTask(secondTask);
        organizer.removeTaskDependencies(0);
        assertEquals(expectedTasks, organizer.getTasks());
    }

    @Test
    public void shouldCountTotalTime() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task firstTask = new Task(0, 5);
        Task secondTask = new Task(1, 6);
        Task thirdTask = new Task(2, 8);
        Task fourthTask = new Task(3, 5);
        secondTask.addDependency(0);
        thirdTask.addDependency(0);
        fourthTask.addDependency(1);
        fourthTask.addDependency(2);
        tasks.add(firstTask);
        tasks.add(secondTask);
        tasks.add(thirdTask);
        tasks.add(fourthTask);
        for (Task task : tasks) {
            organizer.addTask(task);
        }
        organizer.countTotalTime();
        assertEquals(18, organizer.getTotalTime());
    }

    @After
    public void run() {
        organizer.run("/home/vaclav/IdeaProjects/TasksRemaining/inputs/01.in", "/home/vaclav/IdeaProjects/TasksRemaining/outputs/01.in");
    }

}