package com.xrkmed.accesspoint.models;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = WorkerAdvertency.class)
public class WorkerAdvertency implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String reason;
	private String appliedBy;
	private String dateStr;
	private float rating; 
	
	public WorkerAdvertency() {
		
	}
	
	public WorkerAdvertency(String reason, String appliedBy, String dateStr, float rating/*Utilize um rating de 0 a 10 para o quao ruim foi a advertencia, 0 = pouco ruim e 10 = muito ruim*/) {
		this.reason = reason;
		this.appliedBy = appliedBy;
		this.dateStr = dateStr;
		this.rating = rating;
	}

	public String getReason() {
		return reason;
	}

	public String getAppliedBy() {
		return appliedBy;
	}

	public String getDateStr() {
		return dateStr;
	}

	public float getRating(){
		return rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appliedBy, dateStr, reason);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkerAdvertency other = (WorkerAdvertency) obj;
		return Objects.equals(appliedBy, other.appliedBy) && Objects.equals(dateStr, other.dateStr)
				&& Objects.equals(reason, other.reason);
	}
	
	

	
}
