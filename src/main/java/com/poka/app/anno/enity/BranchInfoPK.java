package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class BranchInfoPK implements Serializable{
	private String bankno;
	private String agencyno;
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof BranchInfoPK){
			BranchInfoPK pk = (BranchInfoPK)obj;
			if(pk.getBankno().equals(this.bankno)&&pk.getAgencyno().equals(this.agencyno)){
				return true;
			}
			
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return (this.getBankno()+this.getAgencyno()).hashCode();
	}
	
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
	
	
}
