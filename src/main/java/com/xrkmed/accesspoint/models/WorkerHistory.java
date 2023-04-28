package com.xrkmed.accesspoint.models;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = WorkerHistory.class)
public class WorkerHistory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private float rating;
	
	public WorkerHistory() {
		
	}
	
	public WorkerHistory(String title, String description, float rating) {
		this.title = title;
		this.description = description;
		this.rating = Math.max(Math.min(rating, 10), 0);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public float getRating() {
		return rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, rating, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkerHistory other = (WorkerHistory) obj;
		return Objects.equals(description, other.description)
				&& Float.floatToIntBits(rating) == Float.floatToIntBits(other.rating)
				&& Objects.equals(title, other.title);
	}
	
	
}
