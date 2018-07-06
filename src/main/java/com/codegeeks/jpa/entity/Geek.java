package com.codegeeks.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "T_GEEK")
public class Geek extends Person {

	private String faboriteProgrammingLanguage;
	private List<Project> projects = new ArrayList<Project>();

	@Column(name = "FAV_PROG_LANG")
	public String getFaboriteProgrammingLanguage() {
		return faboriteProgrammingLanguage;
	}
	public void setFaboriteProgrammingLanguage(String faboriteProgrammingLanguage) {
		this.faboriteProgrammingLanguage = faboriteProgrammingLanguage;
	}
	
	@ManyToMany
	@JoinTable( name = "T_GEEK_PROJECT", 
	            joinColumns= {@JoinColumn(name="GEEK_ID", referencedColumnName="ID")},
	            inverseJoinColumns= {@JoinColumn(name="PROJECT_ID", referencedColumnName="ID")})
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	

	
}
