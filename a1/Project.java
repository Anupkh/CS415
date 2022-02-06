package a1;

import java.util.HashSet;
import java.util.Set;

public class Project {
    private String name;
    private Set<Qualification> qs;
    private ProjectSize size;
    private ProjectStatus ps;
    private Set<Worker> projectWorkers = new HashSet<Worker>();
	public Project(String name, Set<Qualification> qs, ProjectSize size) {
        this.name = name;
        this.qs = qs;
        this.size = size;
    }

	@Override
	public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        Project o1 = (Project)o;
        return this.name == o1.name;
    }

	@Override
	public String toString() {
        return this.getName() + ":" + this.getWorkers().size() + ":" + this.getStatus();
    }

	public String getName() {
        return this.name;
    }

	public void setName(String s) {
        this.name = s;
    }

	public ProjectSize getSize() {
        return this.size;
    }

	public void setSize(ProjectSize size) {
        this.size = size;
    }

	public ProjectStatus getStatus() {
        return this.ps;
    }

	public void setStatus(ProjectStatus status) {
        this.ps = status;
    }

	public void addWorker(Worker w) {
        if(!this.getWorkers().contains(w))
            this.projectWorkers.add(w);
    }

	public void removeWorker(Worker w) {
        this.projectWorkers.remove(w);
    }

	public Set<Worker> getWorkers() {
        return this.projectWorkers;
    }

	public void removeAllWorkers() {
        this.projectWorkers.clear();
    }

	public Set<Qualification> getRequiredQualifications() {
        return this.qs;
    }

	public void addQualification(Qualification q) {
        if(this.qs.contains(q))
            return;
        this.qs.add(q);
    }

    private void helperMissingQualifications(Qualification q, Set<Qualification> missingQual){
        for(Worker w : this.getWorkers()) {
            if (w.getQualifications().contains(q))
                return;
        }
        missingQual.add(q);
    }

	public Set<Qualification> getMissingQualifications() {
        Set<Qualification> missingQual = new HashSet<Qualification>();

        for(Qualification q : this.qs){
            helperMissingQualifications(q, missingQual);
        }
        return missingQual;
    }

	public boolean isHelpful(Worker w) {
        for(Qualification q : w.getQualifications()){
            if(getMissingQualifications().contains(q)) {
                return true;
            }
        }
        return false;
    }

}
