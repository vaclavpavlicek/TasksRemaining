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

}