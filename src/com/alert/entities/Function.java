package com.alert.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "etl_rep_functions_reporting", schema = "reporting")
public class Function extends AbstractEntity {

	private String functionName;

	
	

	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "function_reporting", joinColumns = @JoinColumn(name = "function_id") , inverseJoinColumns = @JoinColumn(name = "list_rep_id") , schema = "reporting")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<RepRapport> listreprapport = new ArrayList<>();

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	

	
	public List<RepRapport> getListreprapport() {
		return listreprapport;
	}

	public void setListreprapport(List<RepRapport> listreprapport) {
		this.listreprapport = listreprapport;
	}
}
