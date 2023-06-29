package com.alert.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the revenuestream database table.
 * 
 */
@Entity
@Table(name="revenuestream", schema="tableref")
@NamedQuery(name="RevenueStream.findAll", query="SELECT r FROM RevenueStream r")
public class RevenueStream implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private Long id;

	
	@Column(length=100)
	private String revenuestream;

	public RevenueStream() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRevenuestream() {
		return this.revenuestream;
	}

	public void setRevenuestream(String revenuestream) {
		this.revenuestream = revenuestream;
	}

}