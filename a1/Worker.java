package a1;

import java.util.HashSet;
import java.util.Set;

public class Worker {
    private String name;
    private Set<Qualification> qs;
    private double salary;
    private Set<Project> projects = new HashSet<Project>();
	public Worker(String name, Set<Qualification> qs, double salary){
        this.name = name;
        this.qs = qs;
        this.salary = salary;
    }

	@Override
	public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        Worker w1 = (Worker)o;
        return this.name == w1.name;
    }

	@Override
	public String toString() {
        return this.name + ":" + this.projects.size() + ":" + this.qs.size() + ":" + (int)Math.floor(this.salary);
    }

	public String getName() {
        return this.name;
    }
	public void setName(String s) {
        this.name = s;
    }

	public double getSalary() {
        return this.salary;
    }
	public void setSalary(double salary) {
        this.salary = salary;
    }
	public Set<Qualification> getQualifications() {
        return this.qs;
    }

	public void addQualification(Qualification q) {
        this.qs.add(q);
    }

	public Set<Project> getProjects() {
        return projects;
    }

	public void addProject(Project p) {
        this.projects.add(p);
    }

	public void removeProject(Project p) {
        this.projects.remove(p);
    }

	public int getWorkload() {
        int small = 0;
        int medium = 0;
        int large = 0;
        for(Project p : projects){
            if(p.getSize() == ProjectSize.SMALL){
                small++;
            }
            if(p.getSize() == ProjectSize.MEDIUM){
                medium++;
            }
            if(p.getSize() == ProjectSize.LARGE){
                large++;
            }
        }
        return ((3*large) + (2*medium) + small);
    }

	public boolean willOverload(Project p) {
        projects.add(p);
        int workLoad = getWorkload();
        projects.remove(p);
        return workLoad > 12;
    }

	public boolean isAvailable() {
        return getWorkload() < 12;
    }
}
