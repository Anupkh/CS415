package a1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {
    Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
    Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);

    @Test
    public void testEquals(){
        assertTrue(w1.equals(w1));
        assertFalse(w1.equals(w2));
    }

    @Test
    public void testToString(){
        Worker w3 = new Worker("Kate", new HashSet<Qualification>(), 100000.1);
        Qualification q1 = new Qualification("Tools");
        Qualification q2 = new Qualification("Computer");
        w3.addQualification(q1);
        w3.addQualification(q2);
        Project p1 = new Project("Build", w1.getQualifications(), ProjectSize.LARGE);
        Project p2 = new Project("Create", w1.getQualifications(), ProjectSize.MEDIUM);
        w3.addProject(p1);
        w3.addProject(p2);
        assertEquals("Kate:2:2:100000", w3.toString());
    }
    @Test
    public void testGetName(){
        assertEquals("Bob", w1.getName());
    }

    @Test
    public void testSetName(){
        Worker w3 = new Worker("Kate", new HashSet<Qualification>(), 100000);
        w3.setName("Katie");
        assertEquals("Katie", w3.getName());
        w3.setName("");
        assertEquals("", w3.getName());
    }

    @Test
    public void testGetSalary(){
        assertEquals(30000, w1.getSalary());
    }

    @Test
    public void testSetSalary(){
        w1.setSalary(20);
        assertEquals(20, w1.getSalary());
    }

    @Test
    public void testGetQualification(){
        assertEquals(0, w1.getQualifications().size());
    }

    @Test
    public void testAddQualification(){
        assertEquals(0, w1.getQualifications().size());
        Qualification q1 = new Qualification("Tools");
        Qualification q2 = new Qualification("Computer");
        w1.addQualification(q1);
        assertEquals(1, w1.getQualifications().size());
        w1.addQualification(q2);
        assertEquals(2, w1.getQualifications().size());
    }

    @Test
    public void testGetProjects(){
        assertEquals(0, w1.getProjects().size());
    }

    @Test
    public void testAddProjects(){
        Project p1 = new Project("Build", w1.getQualifications(), ProjectSize.MEDIUM);
        w1.addProject(p1);
        assertEquals(1, w1.getProjects().size());
    }

    @Test
    public void testRemoveProject(){
        Project p1 = new Project("Build", w1.getQualifications(), ProjectSize.MEDIUM);
        w1.addProject(p1);
        assertEquals(1, w1.getProjects().size());
        w1.removeProject(p1);
        assertEquals(0, w1.getProjects().size());
    }

    @Test
    public void testGetWorkload(){
        Project p1 = new Project("Build", w1.getQualifications(), ProjectSize.LARGE);
        Project p2 = new Project("Create", w1.getQualifications(), ProjectSize.MEDIUM);
        Project p3 = new Project("Sleep", w1.getQualifications(), ProjectSize.SMALL);

        assertEquals(0, w1.getWorkload());
        w1.addProject(p1);
        assertEquals(3, w1.getWorkload());
        w1.addProject(p2);
        assertEquals(5, w1.getWorkload());
        w1.addProject(p3);
        assertEquals(6, w1.getWorkload());
        w1.addProject(p3);
        assertEquals(6, w1.getWorkload());
        w1.removeProject(p1);
        w1.removeProject(p2);
        assertEquals(1, w1.getWorkload());
    }

    @Test
    public void testWillOverload(){
        Project p1 = new Project("Build", w1.getQualifications(), ProjectSize.LARGE);
        Project p2 = new Project("Destroy", w1.getQualifications(), ProjectSize.LARGE);
        Project p3 = new Project("Create", w1.getQualifications(), ProjectSize.LARGE);
        Project p4 = new Project("Sleep", w1.getQualifications(), ProjectSize.LARGE);
        Project p5 = new Project("Eat", w1.getQualifications(), ProjectSize.LARGE);

        w1.addProject(p1);
        assertFalse(w1.willOverload(p2));
        w1.addProject(p2);
        assertFalse(w1.willOverload(p3));
        w1.addProject(p3);
        assertFalse(w1.willOverload(p4));
        w1.addProject(p4);
        assertTrue(w1.willOverload(p5));
    }

    @Test
    public void testIsAvailable(){
        Project p1 = new Project("Build", w1.getQualifications(), ProjectSize.LARGE);
        Project p2 = new Project("Destroy", w1.getQualifications(), ProjectSize.LARGE);
        Project p3 = new Project("Create", w1.getQualifications(), ProjectSize.LARGE);
        Project p4 = new Project("Sleep", w1.getQualifications(), ProjectSize.LARGE);

        assertTrue(w1.isAvailable());
        w1.addProject(p1);
        assertTrue(w1.isAvailable());
        w1.addProject(p2);
        assertTrue(w1.isAvailable());
        w1.addProject(p3);
        assertTrue(w1.isAvailable());
        w1.addProject(p4);
        assertFalse(w1.isAvailable());
        w1.removeProject(p1);
        assertTrue(w1.isAvailable());
    }
}
