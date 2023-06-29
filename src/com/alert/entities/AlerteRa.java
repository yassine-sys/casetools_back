package com.alert.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the alerte_ra database table.
 * 
 */
@Entity
@Table(name = "alerte_ra", schema = "stat")
@NamedQuery(name = "AlerteRa.findAll", query = "SELECT a FROM AlerteRa a")
public class AlerteRa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "date_detection")
	private Timestamp dateDetection;

	private String dateappel;

	private String etat;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "id_rule")
	private Long idRule;

	private String namerule;
	private double seuil;
	private double valeur;
	private String function;

	private Integer seuilmin;
	private Integer seuilmed;
	private Integer seilmax;

	private String description;

	private String status ;
	
	
	public Integer getSeuilmin() {
		return seuilmin;
	}

	public void setSeuilmin(Integer seuilmin) {
		this.seuilmin = seuilmin;
	}

	public Integer getSeuilmed() {
		return seuilmed;
	}

	public void setSeuilmed(Integer seuilmed) {
		this.seuilmed = seuilmed;
	}

	public Integer getSeilmax() {
		return seilmax;
	}

	public void setSeilmax(Integer seilmax) {
		this.seilmax = seilmax;
	}

	public String getNamerule() {
		return namerule;
	}

	public void setNamerule(String namerule) {
		this.namerule = namerule;
	}

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public AlerteRa() {
	}

	public Timestamp getDateDetection() {
		return this.dateDetection;
	}

	public void setDateDetection(Timestamp dateDetection) {
		this.dateDetection = dateDetection;
	}

	public String getDateappel() {
		return this.dateappel;
	}

	public void setDateappel(String dateappel) {
		this.dateappel = dateappel;
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

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}