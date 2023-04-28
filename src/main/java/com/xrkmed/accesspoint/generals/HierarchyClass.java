package com.xrkmed.accesspoint.generals;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = HierarchyClass.class)
public class HierarchyClass implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String className;
	private int userAccess;
	
	public HierarchyClass() {
		
	}
	
	public HierarchyClass(String className, int userAccess) {
		this.className = className;
		this.userAccess = userAccess;
	}

	public String getName(){
		return className;
	}

	public int getAccess(){
		return userAccess;
	}

	public boolean hasAccess(int needed){
		return needed < userAccess;
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, userAccess);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HierarchyClass other = (HierarchyClass) obj;
		return Objects.equals(className, other.className) && userAccess == other.userAccess;
	}
	
	
}
