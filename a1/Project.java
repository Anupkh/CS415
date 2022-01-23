package a1;

import java.util.Set;

public class Project {
    private String name;
    private Set<Qualification> qs;
    private ProjectSize size;
	public Project(String name, Set<Qualification> qs, ProjectSize size) {
        this.name = name;
        this.qs = qs;
        this.size = size;
    }

//	@Override
//	public boolean equals(Object o) {}
//
//	@Override
//	public String toString() {}
//
//	public String getName() {}
//
//	public void setName(String s) {}
//
	public ProjectSize getSize() {
        return this.size;
    }
//
//	public void setSize(ProjectSize size) {}
//
//	public ProjectStatus getStatus() {}
//
//	public void setStatus(ProjectStatus status) {}
//
//	public void addWorker(Worker w) {}
//
//	public void removeWorker(Worker w) {}
//
//	public Set<Worker> getWorkers() {}
//
//	public void removeAllWorkers() {}
//
//	public Set<Qualification> getRequiredQualifications() {}
//
//	public void addQualification(Qualification q) {}
//
//	public Set<Qualification> getMissingQualifications() {}
//
//	public boolean isHelpful(Worker w) {}
}
