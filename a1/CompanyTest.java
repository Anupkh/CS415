package a1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest{

    @Test
    public void testEquals(){
        Company c1 = new Company("Limitless");
        Company c2 = new Company("Freedom");

        assertTrue(c1.equals(c1));
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testToString(){
        Company c1 = new Company("Limitless");

        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);


        Worker w1 = c1.createWorker("Bob", workerQualification, 30000);
        Worker w2 = c1.createWorker("Sam", workerQualification, 50000);



        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        Project p1 =c1.createProject("Build car", requiredQualifications, ProjectSize.MEDIUM);
        Project p2 =c1.createProject("Build plane", requiredQualifications, ProjectSize.MEDIUM);


        assertEquals("Limitless:2:2", c1.toString());
    }

    @Test
    public void testGetName(){
        Company c1 = new Company("Limitless");
        assertEquals("Limitless", c1.getName());
    }

    @Test
    public void testSetName(){
        Company c1 = new Company("Limitless");
        c1.setName("Freedom");
        assertEquals("Freedom", c1.getName());
    }

    @Test
    public void testGetEmployedWorkers(){
        Company c1 = new Company("Limitless");
        assertEquals(0, c1.getEmployedWorkers().size());

        Qualification q1 = c1.createQualification("Hammer");

        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);


        Worker w1 = c1.createWorker("Bob", workerQualification, 30000);
        assertEquals(1, c1.getEmployedWorkers().size());
    }

    @Test
    public void testGetAvailableWorkers(){
        Company c1 = new Company("Limitless");

        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);
        workerQualification.add(q2);


        Worker w1 = c1.createWorker("Bob", workerQualification, 30000);
        assertEquals(1, c1.getAvailableWorkers().size());

        Worker w2 = c1.createWorker("Sam", workerQualification, 50000);
        assertEquals(2, c1.getAvailableWorkers().size());
    }


    @Test
    public void testGetAssignedWorkers(){
        Company c1 = new Company("Limitless");

        //company qualifications
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        //project required qualifications
        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        //create project
        Project p1 = c1.createProject("Build car", requiredQualifications, ProjectSize.MEDIUM);

        //worker qualifications
        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);

        //w1
        Worker w1 = c1.createWorker("Lisa", workerQualification, 90000);

        c1.assign(w1, p1);
        assertEquals(1, p1.getWorkers().size());
    }
    @Test
    public void testUnassignedWorkers(){
        Company c1 = new Company("Limitless");

        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);
        workerQualification.add(q2);


        Worker w1 = c1.createWorker("Bob", workerQualification, 30000);
        Worker w2 = c1.createWorker("Sam", workerQualification, 50000);

        assertEquals(2, c1.getUnassignedWorkers().size());

    }

    @Test
    public void testGetProjects(){
        Company c1 = new Company("Limitless");

        //company qualifications
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        //project required qualifications
        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        //create project
        Project p1 = c1.createProject("Build car", requiredQualifications, ProjectSize.MEDIUM);

        assertEquals(1, c1.getProjects().size());
    }

    @Test
    public void testGetQualification(){
        Company c1 = new Company("Limitless");
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Hammer");
        assertEquals(1, c1.getQualifications().size());
        Qualification q3 = c1.createQualification("Wrench");
        assertEquals(2, c1.getQualifications().size());

    }
    @Test
    public void testCreateWorker(){
        Company c1 = new Company("Limitless");
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");
        Set<Qualification> workerQual = new HashSet<Qualification>();

        workerQual.add(q1);
        Worker w1  = c1.createWorker("Bob", workerQual, 30000);
        assertEquals(1, c1.getEmployedWorkers().size());

        Worker duplicateW1 = c1.createWorker("Bob", workerQual, 30000);
        assertEquals(1, c1.getEmployedWorkers().size());


        workerQual.add(q2);
        Worker w2 = c1.createWorker("Amy", workerQual, 40000);
        assertEquals(2, c1.getEmployedWorkers().size());

        //Don't hire if worker doesn't meet qualifications
        Worker w3 = c1.createWorker("Kate", new HashSet<Qualification>(), 20000);
        assertEquals(2, c1.getEmployedWorkers().size());
    }


    @Test
    public void testCreateQualification(){
        Company c1 = new Company("Limitless");
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q1Duplicate = c1.createQualification("Hammer");
        assertEquals(1, c1.getQualifications().size());
        Qualification q2 = c1.createQualification("Wrench");
        assertEquals(2, c1.getQualifications().size());


    }

    @Test
    public void testCreateProject(){
        Company c1 = new Company("Limitless");
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");
        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        assertEquals(0, c1.getProjects().size());

        c1.createProject("Build car", requiredQualifications, ProjectSize.LARGE);
        assertEquals(1, c1.getProjects().size());

        //create duplicate project
        Project p2 = c1.createProject("Build car", requiredQualifications, ProjectSize.LARGE);
        assertEquals(1, c1.getProjects().size());
    }

    @Test
    public void testStart(){
        Company c1 = new Company("Limitless");
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");
        Qualification q3 = new Qualification("Engineering");

        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        Project p1 =c1.createProject("Build car", requiredQualifications, ProjectSize.MEDIUM);
        c1.start(p1);
        assertEquals(ProjectStatus.ACTIVE, p1.getStatus());

        requiredQualifications.add(q3);
        Project p2 = c1.createProject("Build Rocket", requiredQualifications, ProjectSize.LARGE);

    }

    @Test
    public void testFinish(){
        Company c1 = new Company("Limitless");
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        Project p1 =c1.createProject("Build car", requiredQualifications, ProjectSize.MEDIUM);
        c1.start(p1);
        assertEquals(ProjectStatus.ACTIVE, p1.getStatus());

        c1.finish(p1);

        assertEquals(ProjectStatus.FINISHED, p1.getStatus());

        for(Worker w : p1.getWorkers()){
            c1.unassign(w, p1);
        }

    }

    @Test
    public void testAssign(){
        Company c1 = new Company("Limitless");

        //company qualifications
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        //project required qualifications
        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        //create project
        Project p1 = c1.createProject("Build car", requiredQualifications, ProjectSize.MEDIUM);

        //worker qualifications
        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);

        //w1
        Worker w1 = c1.createWorker("Lisa", workerQualification, 90000);
        assertEquals(1, c1.getEmployedWorkers().size());


        c1.assign(w1, p1);
        assertEquals(1, p1.getWorkers().size());

        c1.assign(w1, p1);
        assertEquals(1, p1.getWorkers().size());

    }

    @Test
    public void testUnassignAll(){
        Company c1 = new Company("Limitless");

        //company qualifications
        Qualification q1 = c1.createQualification("Hammer");
        Qualification q2 = c1.createQualification("Wrench");

        //project required qualifications
        Set<Qualification> requiredQualifications = new HashSet<Qualification>();
        requiredQualifications.add(q1);
        requiredQualifications.add(q2);

        //create project
        Project p1 = c1.createProject("Build car", requiredQualifications, ProjectSize.LARGE);
        Project p2 = c1.createProject("Build seat", requiredQualifications, ProjectSize.LARGE);
        Project p3 = c1.createProject("Build pc", requiredQualifications, ProjectSize.LARGE);
        Project p4 = c1.createProject("Build scooter", requiredQualifications, ProjectSize.LARGE);

        //worker qualifications
        Set<Qualification> workerQualification = new HashSet<Qualification>();
        workerQualification.add(q1);

        //w1
        Worker w1 = c1.createWorker("Lisa", workerQualification, 90000);
        assertEquals(1, c1.getEmployedWorkers().size());

        c1.assign(w1, p1);
        assertTrue(w1.isAvailable());
        c1.assign(w1, p2);
        assertTrue(w1.isAvailable());
        c1.assign(w1, p3);
        assertTrue(w1.isAvailable());
        c1.assign(w1, p4);
        assertTrue(!w1.isAvailable());

        c1.unassignAll(w1);
        assertEquals(0, w1.getWorkload());
    }
}
