package a1;

import java.util.HashSet;
import java.util.Set;

public class Qualification {
	private String description;
	private Set<Worker> workerSet= new HashSet<Worker>();

	public Qualification(String s) {
		this.description = s;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o){
			return true;
		}
		if(o == null){
			return false;
		}
		Qualification qual = (Qualification)o;
		if(this.description == qual.description){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return this.description;
	}

	public Set<Worker> getWorkers() {
		return workerSet;
	}

	public void addWorker(Worker w) {
		workerSet.add(w);
	}

	public void removeWorker(Worker w) {
		workerSet.remove(w);
	}
}
