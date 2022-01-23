package a1;


import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class QualificationTest {

    Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
    Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);

    @Test
    public void testToEquals() {
        Qualification q1 = new Qualification("tools");
        Qualification q2 = new Qualification("tools");
        Qualification q3 = new Qualification(null);

        assertTrue(q1.equals(q2));
        assertFalse(q2.equals(q3));
    }

    @Test
    public void testToString(){
        Qualification q1 = new Qualification("tools");
        assertEquals("tools", q1.toString());
    }


    @Test
    public void testAddWorker(){
        Qualification q1 = new Qualification("tools");
        q1.addWorker(w1);
        assertFalse(q1.getWorkers().size() > 1);
        q1.addWorker(w2);
        assertTrue(q1.getWorkers().size() == 2);

    }

    @Test
    public void testRemoveWorker(){
        Qualification q1 = new Qualification("tools");

        q1.addWorker(w1);
        assertEquals(1,q1.getWorkers().size());
        q1.removeWorker(w1);
        assertEquals(0, q1.getWorkers().size());
        q1.addWorker(w2);
        q1.removeWorker(w2);
        assertEquals(0, q1.getWorkers().size());
    }
}
