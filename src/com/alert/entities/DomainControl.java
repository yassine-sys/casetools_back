package com.alert.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the domaincontrol database table.
 * 
 */
@Entity
@Table(name="domaincontrol",schema="tableref")
@NamedQuery(name="DomainControl.findAll", query="SELECT d FROM DomainControl d")
public class DomainControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "domain")
	private String domain;

	@Column(name = "domaine")
	private String domaine;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private Long id;

	public DomainControl() {
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomaine() {
		return this.domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}