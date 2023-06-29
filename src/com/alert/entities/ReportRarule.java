package com.alert.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the report_rarules database table.
 * 
 */
@Entity
@Table(name = "report_rarules", schema = "tableref")
@NamedQuery(name = "ReportRarule.findAll", query = "SELECT r FROM ReportRarule r")
public class ReportRarule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(name = "id_report")
	private Long idReport;

	private String namerep;

	@Column(name = "id_rule")
	private Long idRule;

	private String namerul;

	public ReportRarule() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdReport() {
		return this.idReport;
	}

	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}

	public Long getIdRule() {
		return this.idRule;
	}

	public void setIdRule(Long idRule) {
		this.idRule = idRule;
	}

	public String getNamerep() {
		return namerep;
	}

	public void setNamerep(String namerep) {
		this.namerep = namerep;
	}

	public String getNamerul() {
		return namerul;
	}

	public void setNamerul(String namerul) {
		this.namerul = namerul;
	}

}