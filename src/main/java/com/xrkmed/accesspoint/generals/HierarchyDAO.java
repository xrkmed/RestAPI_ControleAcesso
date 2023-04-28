package com.xrkmed.accesspoint.generals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xrkmed.accesspoint.exceptions.InvalidArray;
import com.xrkmed.accesspoint.exceptions.WorkerNotFound;
import com.xrkmed.accesspoint.models.AccessPointCad;
import com.xrkmed.accesspoint.models.WorkerAdvertency;
import com.xrkmed.accesspoint.models.WorkerEntity;
import com.xrkmed.accesspoint.models.WorkerHistory;

public class HierarchyDAO {

	private static HierarchyDAO _instance;
	
	private final ArrayList<WorkerEntity> workers = new ArrayList<>();
	private final HashMap<WorkerEntity, AccessPointCad> workersWorking = new HashMap<>();

	public WorkerEntity registerWorker(WorkerEntity e){
		if(!workers.contains(e)){
			workers.add(e);
			return e;
		}

		return null;
	}

	public WorkerEntity registerWorker(long workerId, String workerName, HierarchyClass workerType, double salary){
		WorkerEntity e = new WorkerEntity(workerId, workerName, workerType, salary);
		if(!workers.contains(e)){
			workers.add(e);
			return e;
		}

		return null;
	}

	public void addWorkerAdvertency(WorkerEntity e, WorkerAdvertency object){
		if(workers.contains(e)){
			e.addWorkerAdvertency(object);
		}
	}

	public void addWorkerHistory(WorkerEntity e, WorkerHistory object){
		if(workers.contains(e)){
			e.addWorkerHistory(object);
		}
	}

	public WorkerEntity getWorkerById(long WorkerId) throws WorkerNotFound{
		for(WorkerEntity worker : workers){
			if(worker.getWorkerId() == WorkerId)
				return worker;
		}

		throw new WorkerNotFound();
	}

	public WorkerEntity getWorker(Predicate<String> query) throws WorkerNotFound{
		for(WorkerEntity worker : workers){
			if(query.test(worker.getWorkerName()) || query.test(worker.getWorkerFilial()) || query.test(worker.getWorkerType().getName()))
				return worker;
		}

		throw new WorkerNotFound();
	}

	public boolean working(WorkerEntity e){
		return workersWorking.get(e) != null;
	}

	public AccessPointCad getCad(WorkerEntity e){
		return workersWorking.get(e);
	}

	public boolean startWorking(WorkerEntity worker, AccessPointCad cad){
		workersWorking.put(worker, cad);
		return true;
	}
	
	public HierarchyDAO getInstance() {
		if(_instance == null)
			_instance = new HierarchyDAO();
		
		return _instance;
	}

	public long getWorkersCount(){
		return workers.size();
	}

	public String[] parseWorkerIdentification(String workerIdentification) throws InvalidArray{
		if(workerIdentification == null || workerIdentification.isEmpty())
			throw new InvalidArray();

		if(workerIdentification.length() != 12)
			throw new InvalidArray();
		
		String[] workerIdentificationArray = workerIdentification.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		if(workerIdentificationArray.length == 3){
			return workerIdentificationArray;
		}

		throw new InvalidArray();
	}
	
	
}
