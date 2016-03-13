package cz.vendasky;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskTest {

    Task task;

    @Before
    public void setUp() {
        task = new Task(0);
    }

    @Test
    public void shouldAddDependencyForTask() {
        ArrayList<Integer> dependencies = new ArrayList<>();
        dependencies.add(1);
        task.addDependency(1);
        assertEquals(dependencies, task.getDependencies());
    }

}