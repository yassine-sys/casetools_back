package com.alert.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userlogin", schema = "casemanagement")
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -706130025067732026L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "u_id")
	private Integer id;

	private String name;
	private String login;
	private String mail;
	private String password;
	private String matricule;
	private String etat;
	private Timestamp createdDate;
	private String createdBy;
	private boolean domaineControl;
	private boolean revunueStream;
	private boolean HistoriqueAlert;
	private boolean adminAdd;
	private boolean raRule;
	private boolean stat;
	private boolean assignedTo;
	private boolean reAssignedTo;
	private boolean closed;
 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDomaineControl() {
		return domaineControl;
	}

	public void setDomaineControl(boolean domaineControl) {
		this.domaineControl = domaineControl;
	}

	public boolean isRevunueStream() {
		return revunueStream;
	}

	public void setRevunueStream(boolean revunueStream) {
		this.revunueStream = revunueStream;
	}

	public boolean isHistoriqueAlert() {
		return HistoriqueAlert;
	}

	public void setHistoriqueAlert(boolean historiqueAlert) {
		HistoriqueAlert = historiqueAlert;
	}

	public boolean isAdminAdd() {
		return adminAdd;
	}

	public void setAdminAdd(boolean adminAdd) {
		this.adminAdd = adminAdd;
	}

	public boolean isRaRule() {
		return raRule;
	}

	public void setRaRule(boolean raRule) {
		this.raRule = raRule;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public boolean isAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(boolean assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isReAssignedTo() {
		return reAssignedTo;
	}

	public void setReAssignedTo(boolean reAssignedTo) {
		this.reAssignedTo = reAssignedTo;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
