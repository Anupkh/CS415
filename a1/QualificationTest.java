package a1;


import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class QualificationTest {

    Worker w1 = new Worker("Bob", new HashSet<Qualification>(), 30000);
    Worker w2 = new Worker("Sam", new HashSet<Qualification>(), 50000);

    @Test
    public void testToEquals() {
        Qualification a1 = new Qualification("tools");
        Qualification a2 = new Qualification("tools");
        Qualification a3 = new Qualification(null);

        assertTrue(a1.equals(a2));
        assertFalse(a2.equals(a3));
    }

    @Test
    public void testToString(){
        Qualification a1 = new Qualification("tools");
        assertEquals("tools", a1.toString());
    }


    @Test
    public void testAddWorker(){
        Qualification a1 = new Qualification("tools");
        a1.addWorker(w1);
        assertFalse(a1.getWorkers().size() > 1);
        a1.addWorker(w2);
        assertTrue(a1.getWorkers().size() == 2);

    }

    @Test
    public void testRemoveWorker(){
        Qualification a1 = new Qualification("tools");

        a1.addWorker(w1);
        assertEquals(1,a1.getWorkers().size());
        a1.removeWorker(w1);
        assertEquals(0, a1.getWorkers().size());
        a1.addWorker(w2);
        a1.removeWorker(w2);
        assertEquals(0, a1.getWorkers().size());
    }

}
