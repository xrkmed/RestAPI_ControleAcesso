package com.xrkmed.accesspoint.exceptions;

public class WorkerNotFound extends Exception{
	private static final long serialVersionUID = 1L;
	
	public WorkerNotFound() {
		super("Worker not found");
	}
	
	public WorkerNotFound(String msg) {
		super(msg);
	}

}
