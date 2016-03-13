package cz.vendasky;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrganizerTest {

    Organizer organizer;

    @Before
    public void setUp() {
        organizer = new Organizer();
    }

    @Test
    public void shouldAddTaskIntoTasksList() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task expectedTask = new Task(0);
        Task actualTask = new Task(0);
        tasks.add(expectedTask);
        organizer.addTask(actualTask);
        assertEquals(tasks, organizer.getTasks());
    }

    @Test
    public void shouldRemoveTasksWithoutDependencies() {
        ArrayList<Task> expectedTasks = new ArrayList<>();
        Task firstTask = new Task(0);
        Task secondTask = new Task(1);
        secondTask.addDependency(0);
        expectedTasks.add(secondTask);
        organizer.addTask(firstTask);
        organizer.addTask(secondTask);
        organizer.removeTasksWithoutDependencies();
        assertEquals(expectedTasks, organizer.getTasks());
    }

}