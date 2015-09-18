package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERINFO")
public class PerInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5657632870579719970L;
	
	private String percode;
	private String pertype;
	private String bankno;
	private String agencyno;
	private String acctaddr;
	private Character status;
	private String brand;
	private String model;
	private String quality;

	@Id
	@Column(name = "percode", length = 24)
	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	@Column(name = "pertype", length = 2)
	public String getPertype() {
		return pertype;
	}

	public void setPertype(String pertype) {
		this.pertype = pertype;
	}

	@Column(name = "bankno", length = 20)
	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	@Column(name = "agencyno", length = 20)
	public String getAgencyno() {
		return agencyno;
	}

	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}

	@Column(name = "acctaddr", length = 200)
	public String getAcctaddr() {
		return acctaddr;
	}

	public void setAcctaddr(String acctaddr) {
		this.acctaddr = acctaddr;
	}

	@Column(name = "status", length = 1)
	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	@Column(name = "brand", length = 50)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "model", length = 100)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "quality", length = 100)
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

}
