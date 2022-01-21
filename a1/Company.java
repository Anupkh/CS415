package a1;

public class Company {

	public Company(String name) {}

	@Override
	public boolean equals(Object o) {}

	@Override
	public String toString() {}

	public String getName() {}

	public void setName(String s) {}

	public Set<Worker> getEmployedWorkers() {}

	public Set<Worker> getAvailableWorkers() {}

	public Set<Worker> getUnavailableWorkers() {}

	public Set<Worker> getAssignedWorkers() {}

	public Set<Worker> getUnassignedWorkers() {}

	public Set<Project> getProjects() {}

	public Set<Qualification> getQualifications() {}

	public Worker createWorker(String nn, Set<Qualification> qs, double salary) {}

	public Qualification createQualification(String description) {}

	public Project createProject(String n, Set<Qualification> qs, ProjectSize s) {}

	public void start(Project p) {}

	public void finish(Project p) {}

	public void assign (Worker w, Project p) {}

	public void unassign (Worker w, Project p) {}

	public void unassignAll (Worker w) {}
}
