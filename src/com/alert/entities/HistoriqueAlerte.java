package com.alert.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the historique_alerte database table.
 * 
 */
@Entity
@Table(name="historique_alerte" ,schema="stat")
@NamedQuery(name="HistoriqueAlerte.findAll", query="SELECT h FROM HistoriqueAlerte h")
public class HistoriqueAlerte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="date_statut")
	private Timestamp dateStatut;

	private String etat;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column(name="id_rule")
	private Long idRule;
	
	
	@Column(name="nom_utilisateur")
	private String nomUtilisateur;
	
	
	public HistoriqueAlerte() {
	}

	public Timestamp getDateStatut() {
		return this.dateStatut;
	}

	public void setDateStatut(Timestamp dateStatut) {
		this.dateStatut = dateStatut;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRule() {
		return this.idRule;
	}

	public void setIdRule(Long idRule) {
		this.idRule = idRule;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}