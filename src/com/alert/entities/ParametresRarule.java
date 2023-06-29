package com.alert.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the parametres_rarules database table.
 * 
 */
@Entity
@Table(name = "parametres_rarules", schema = "tableref")
@NamedQuery(name = "ParametresRarule.findAll", query = "SELECT p FROM ParametresRarule p")
public class ParametresRarule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	private String fonction;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(name = "id_parametre")
	private Long idParametre;

	@Column(name = "id_rule")
	private Long idRule;

	@Column(precision = 30, scale = 3)
	private BigDecimal seuil;

	private String nameparametre;

	public ParametresRarule() {
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdParametre() {
		return this.idParametre;
	}

	public void setIdRule(Long idRule) {
		this.idRule = idRule;
	}

	public Long getIdRule() {
		return this.idRule;
	}

	public void setIdParametre(Long idParametre) {
		this.idParametre = idParametre;
	}

	public BigDecimal getSeuil() {
		return this.seuil;
	}

	public void setSeuil(BigDecimal seuil) {
		this.seuil = seuil;
	}

	public String getNameparametre() {
		return nameparametre;
	}

	public void setNameparametre(String nameparametre) {
		this.nameparametre = nameparametre;
	}

}