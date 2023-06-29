package com.alert.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alerte_user", schema = "stat")
public class Alertuser implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "id_alerte")
	private Long idAlerte;
	
	@Column(name = "id_user")
	private Long idUser;
	
//	private String alertename;
	
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAlerte() {
		return idAlerte;
	}

	public void setIdAlerte(Long idAlerte) {
		this.idAlerte = idAlerte;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

//	public String getAlertename() {
//		return alertename;
//	}
//
//	public void setAlertename(String alertename) {
//		this.alertename = alertename;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	
	
	
	
	
	

}
