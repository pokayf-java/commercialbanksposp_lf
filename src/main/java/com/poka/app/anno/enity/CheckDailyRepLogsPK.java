package com.poka.app.anno.enity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CheckDailyRepLogsPK implements Serializable{
	
	private String operDate;
	private Integer type;
	
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.getOperDate()+this.getType()).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof CheckDailyRepLogsPK){
			CheckDailyRepLogsPK pk = (CheckDailyRepLogsPK)obj;
			if(pk.getOperDate().equals(this.operDate)&&pk.getType().equals(this.type)){
				return true;
			}
			
		}
		return false;
	}
	
}
