package a1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    @Test
    public void testEquals(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Project p2 = new Project("Tesla", new HashSet<Qualification>(), ProjectSize.LARGE);

        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(p2));
        assertFalse(p1.equals(null));
    }

    @Test
    public void testToString(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);
        Worker w3 = new Worker("Katie", new HashSet<Qualification>(), 60000);
        p1.addWorker(w1);
        p1.addWorker(w2);
        p1.addWorker(w3);

        p1.setStatus(ProjectStatus.PLANNED);

        assertEquals("Rocket:3:PLANNED", p1.toString());
    }

    @Test
    public void testGetName(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        assertEquals("Rocket", p1.getName());
    }

    @Test
    public void testSetName(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        p1.setName("Electric");
        assertEquals("Electric", p1.getName());
    }

    @Test
    public void testGetSize(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        assertEquals(ProjectSize.MEDIUM, p1.getSize());
    }

    @Test
    public void testSetSize(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        p1.setSize(ProjectSize.SMALL);
        assertEquals(ProjectSize.SMALL, p1.getSize());
    }

    @Test
    public void testGetSetStatus(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        p1.setStatus(ProjectStatus.ACTIVE);
        assertEquals(ProjectStatus.ACTIVE, p1.getStatus());
    }

    @Test
    public void testAddWorker(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        p1.addWorker(w1);
        assertEquals(1, p1.getWorkers().size());
    }
    @Test
    public void testRemoveWorker(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        p1.addWorker(w1);
        assertEquals(1, p1.getWorkers().size());
        p1.removeWorker(w1);
        assertEquals(0, p1.getWorkers().size());
    }

    @Test
    public void testGetWorkers(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);
        Worker w3 = new Worker("Katie", new HashSet<Qualification>(), 60000);
        p1.addWorker(w1);
        p1.addWorker(w2);
        p1.addWorker(w3);
        assertEquals(3, p1.getWorkers().size());
    }

    @Test
    public void testRemoveAllWorkers(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);
        Worker w3 = new Worker("Katie", new HashSet<Qualification>(), 60000);

        p1.addWorker(w1);
        assertEquals(1, p1.getWorkers().size());

        p1.addWorker(w2);
        assertEquals(2, p1.getWorkers().size());

        p1.addWorker(w3);
        assertEquals(3, p1.getWorkers().size());

        p1.removeAllWorkers();
        assertEquals(0, p1.getWorkers().size());
    }

    @Test
    public void testGetRequiredQualifications(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        Qualification q1 = new Qualification("tools");
        p1.addQualification(q1);
        assertEquals(1, p1.getRequiredQualifications().size());
    }
    @Test
    public void testAddQualification(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);
        assertEquals(0, p1.getRequiredQualifications().size());
        Qualification q1 = new Qualification("tools");
        p1.addQualification(q1);
        assertEquals(1, p1.getRequiredQualifications().size());
    }

    @Test
    public void testGetMissingQualifications(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);

        Qualification q1 = new Qualification("tools");
        Qualification q2 = new Qualification("bobcat");

        p1.addQualification(q1);
        p1.addQualification(q2);

        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        w1.addQualification(q1);
        Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);
        w2.addQualification(q1);
        Worker w3 = new Worker("Katie", new HashSet<Qualification>(), 60000);

        p1.addWorker(w1);
        p1.addWorker(w2);

        assertEquals(1, p1.getMissingQualifications().size());

        p1.addWorker(w3);
        w3.addQualification(q2);
        assertEquals(0, p1.getMissingQualifications().size());


        p1.removeAllWorkers();
        assertEquals(2, p1.getMissingQualifications().size());

    }


    @Test
    public void testIsHelpful(){
        Project p1 = new Project("Rocket", new HashSet<Qualification>(), ProjectSize.MEDIUM);

        Qualification q1 = new Qualification("tools");
        Qualification q2 = new Qualification("bobcat");

        p1.addQualification(q1);
        p1.addQualification(q2);

        Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
        w1.addQualification(q1);
        Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);
        w2.addQualification(q2);
        Worker w3 = new Worker("Katie", new HashSet<Qualification>(), 60000);


        p1.addWorker(w1);

       assertTrue(p1.isHelpful(w2));
       assertFalse(p1.isHelpful(w3));
       assertFalse(p1.isHelpful(w1));
    }

}
