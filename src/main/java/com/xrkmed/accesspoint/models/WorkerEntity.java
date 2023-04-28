package com.xrkmed.accesspoint.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xrkmed.accesspoint.generals.BuilderConfig;
import com.xrkmed.accesspoint.generals.HierarchyClass;
import com.xrkmed.accesspoint.generals.HierarchyDAO;

@JsonDeserialize(as = WorkerEntity.class)
public class WorkerEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String workerFilial;
	private long workerId;
	private String workerName;
	private HierarchyClass workerType;
	private double workerSalary;
	private final ArrayList<WorkerHistory> workerHistory = new ArrayList<>();
	private final ArrayList<WorkerAdvertency> workerAdvertency = new ArrayList<>();
	private final HierarchyDAO hierarchyDAO = new HierarchyDAO().getInstance();
	
	public WorkerEntity() {
	}
	
	public WorkerEntity(long workerId, String workerName, HierarchyClass workerType, double salary) {
		this.workerId = workerId;
		this.workerName = workerName;
		this.workerType = workerType;
		this.workerSalary = salary;
		this.workerFilial = BuilderConfig.PACKAGE_FILIAL;
	}

	public void addWorkerHistory(WorkerHistory object){
		workerHistory.add(object);
	}

	public void addWorkerAdvertency(WorkerAdvertency object){
		workerAdvertency.add(object);
	}

	public ArrayList<WorkerHistory> getWorkerHistory(){
		return workerHistory;
	}

	public ArrayList<WorkerAdvertency> getWorkerAdvertency(){
		return workerAdvertency;
	}

	public long getWorkerId() {
		return workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public HierarchyClass getWorkerType() {
		return workerType;
	}

	public double getWorkerSalary() {
		return workerSalary;
	}

	public String getWorkerFilial() {
		return workerFilial;
	}

	public String getWorkerIdentificationComplete(){
		return BuilderConfig.getWorkerIdentificationFormat(workerId);
	}

	public boolean isWorkerWorkingNow(){
		return hierarchyDAO.working(this);
	}

	public float getWorkerRating(){
		float rating = 0;
		for(WorkerHistory history : workerHistory){
			rating += (history.getRating()*2);
		}

		for(WorkerAdvertency advertency : workerAdvertency){
			rating -= advertency.getRating();
		}

		return rating / (workerHistory.size() + workerAdvertency.size());
	}

	@Override
	public int hashCode() {
		return Objects.hash(workerFilial, workerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkerEntity other = (WorkerEntity) obj;
		return Objects.equals(workerFilial, other.workerFilial) && workerId == other.workerId;
	}
	
	
	
}
