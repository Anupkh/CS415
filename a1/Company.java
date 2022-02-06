package a1;

import java.util.HashSet;
import java.util.Set;

public class Company {
    private String name;
    private Set<Worker> employedWorkers = new HashSet<Worker>();
    private Set<Qualification> companyQualifications = new HashSet<Qualification>();
    private Set<Project> companyProjects = new HashSet<Project>();
    private Set<Worker> assignedPool = new HashSet<Worker>();
    private Set<Worker> availablePool = new HashSet<Worker>();


	public Company(String name) {
        this.name = name;
    }

	@Override
	public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        Company comp = (Company)o;
        return this.name == comp.name;
    }

	@Override
	public String toString() {
        return this.name + ":" + availablePool.size() + ":" + this.companyProjects.size();
    }

	public String getName() {
        return this.name;
    }

	public void setName(String s) {
        this.name = s;
    }

	public Set<Worker> getEmployedWorkers() {
        return this.employedWorkers;
    }

    //NT
	public Set<Worker> getAvailableWorkers() {
        Set<Worker> availableWorkers = new HashSet<Worker>();
        for(Worker w : this.employedWorkers){
            if(w.isAvailable())
                availableWorkers.add(w);
        }
        return availableWorkers;
    }

	public Set<Worker> getUnavailableWorkers() {
        Set<Worker> unavailableWorkers = new HashSet<Worker>();
        for(Worker w : this.employedWorkers){
            if(!w.isAvailable())
                unavailableWorkers.add(w);
        }
        return unavailableWorkers;
    }

	public Set<Worker> getAssignedWorkers() {
        return this.assignedPool;
    }

	public Set<Worker> getUnassignedWorkers() {
        Set<Worker> unassignedWorkers = new HashSet<Worker>();
        for(Worker w : this.employedWorkers){
            if(!this.assignedPool.contains(w)){
                unassignedWorkers.add(w);
            }
        }
        return unassignedWorkers;
    }

	public Set<Project> getProjects() {
        return this.companyProjects;
    }

	public Set<Qualification> getQualifications() {
        return this.companyQualifications;
    }

	public Worker createWorker(String nn, Set<Qualification> qs, double salary) {
        //check if the worker has at least one qualification
        if(qs.size() < 1)   return null;

        for(Qualification workerQualification : qs){
            if(!this.companyQualifications.contains(workerQualification)){
                return null;
            }
        }

        Worker newWorker = new Worker(nn, qs, salary);
        //check if duplicate worker and return null if worker already hired
        for(Worker worker : this.getEmployedWorkers()){
            if(worker.equals(newWorker)){
                return null;
            }
        }

        this.employedWorkers.add(newWorker);
        if(!this.availablePool.contains(newWorker)) {
            this.availablePool.add(newWorker);
        }

        return newWorker;
    }

    //return null if qualification already exists
	public Qualification createQualification(String description) {
        if(description == null){
            return null;
        }
        Qualification q1 = new Qualification(description);
        for(Qualification q : this.companyQualifications){
            if(q1.equals(q)){
                return null;
            }
        }
        this.companyQualifications.add((q1));
        getAvailableWorkers();
        return q1;
    }


    //return null if project already exists
	public Project createProject(String n, Set<Qualification> qs, ProjectSize s) {

        for(Qualification q : qs){
            if(!this.companyQualifications.contains(q)){
                return null;
            }
        }

        Project newProject = new Project(n, qs, s);

        //check for duplicate
        for(Project p : this.getProjects()){
            if(p.equals(newProject)){
                return null;
            }
        }
        newProject.setStatus(ProjectStatus.PLANNED);
        this.companyProjects.add(newProject);
        return newProject;

    }

	public void start(Project p) {

        //check if project meets company qualification requirements
        for(Qualification q : p.getRequiredQualifications()){
            if(!this.companyQualifications.contains((q))){
                return;
            }
        }
        p.setStatus(ProjectStatus.ACTIVE);
        getAvailableWorkers();
        getUnavailableWorkers();
    }

	public void finish(Project p) {
        if(p.getStatus() == ProjectStatus.ACTIVE) {
            p.setStatus(ProjectStatus.FINISHED);
            p.removeAllWorkers();
            getAvailableWorkers();
        }
    }

	public void assign (Worker w, Project p) {

        if(!this.getEmployedWorkers().contains(w) || !w.isAvailable() || p.getWorkers().contains(w)) {
            return;
        }

        if(getUnavailableWorkers().contains(w)) {
            return;
        }

        if(p.getStatus() == ProjectStatus.ACTIVE || p.getStatus() == ProjectStatus.FINISHED) {
            return;
        }

        if(w.willOverload(p)) {
            return;
        }


        if (p.isHelpful(w)) {
            p.addWorker(w);
            w.addProject(p);
            if(!this.assignedPool.contains(w)) {
                this.assignedPool.add(w);
            }
            if(!w.isAvailable()){
                this.availablePool.remove(w);
            }
            return;
        }


    }

	public void unassign (Worker w, Project p) {
        if(p.getWorkers().contains(w)){
            p.removeWorker(w);
            w.removeProject(p);
        }
        if(p.getMissingQualifications().size() > 0){
            p.setStatus(ProjectStatus.SUSPENDED);
        }
        if(w.getProjects().size() == 0){
            this.assignedPool.remove(w);
        }
        if(w.isAvailable() && !this.availablePool.contains(w)){
            this.availablePool.add(w);
        }

    }

	public void unassignAll (Worker w) {
        for(Project p : this.companyProjects){
            if(p.getWorkers().contains(w)){
                w.removeProject(p);
                p.removeWorker(w);
            }
            if(p.getMissingQualifications().size() > 0){
                p.setStatus(ProjectStatus.SUSPENDED);
            }
        }
        this.assignedPool.remove(w);
        this.availablePool.add(w);
    }
}

