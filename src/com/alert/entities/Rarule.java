package com.alert.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

/**
 * The persistent class for the rarules database table.
 * 
 */
@Entity
@Table(name = "rarules", schema = "tableref")
@NamedQuery(name = "Rarule.findAll", query = "SELECT r FROM Rarule r")
public class Rarule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column
	private String nom;

	@Column
	private String description;

	@Column(name = "id_domain")
	private Long idDomain;

	private Long idrevenue;

	@Column(name = "nom_utilisateur", length = 100)
	private String nomUtilisateur;

	@Column
	private String type;

	public Rarule() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdDomain() {
		return this.idDomain;
	}

	public void setIdDomain(Long idDomain) {
		this.idDomain = idDomain;
	}

	public Long getIdrevenue() {
		return this.idrevenue;
	}

	public void setIdrevenue(Long idrevenue) {
		this.idrevenue = idrevenue;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}