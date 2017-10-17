package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * 2017年9月19日09:57:02
 * @author enma.ai
 * 廊坊兴业银行代理需求新增表T_AgencyCheck
 */

public class TAgencyCheckPK implements Serializable{
	
	private String bagCode;
	private String bankId;
	
	@Column(name = "BagCode",length=24)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	@Column(name = "bank_Id",length=4)
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.getBankId()+this.getBagCode()).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof TAgencyCheckPK){
			TAgencyCheckPK pk = (TAgencyCheckPK)obj;
			if(pk.getBankId().equals(this.bankId)&&pk.getBagCode().equals(this.bagCode)){
				return true;
			}
			
		}
		return false;
	}
	
}
