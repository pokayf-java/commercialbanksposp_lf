package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCHINFO")
@IdClass(value = BranchInfoPK.class)
public class BranchInfo implements Serializable {
	private String bankno;
	private String agencyno;
	private String Branchname;
	private String address;
	private String telphone;
	private String remark;
	
	@Id
	@Column(name = "bankno",length = 20)
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	
	@Id
	@Column(name = "agencyno",length = 20)
	public String getAgencyno() {
		return agencyno;
	}
	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}
	
	@Column(name = "branchname",length = 120)
	public String getBranchname() {
		return Branchname;
	}
	public void setBranchname(String branchname) {
		Branchname = branchname;
	}
	
	@Column(name = "address",length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "telphone",length = 20)
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Column(name = "remark",length = 512)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
