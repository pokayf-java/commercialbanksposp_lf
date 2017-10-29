package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
/**
 * 
 * @ClassName:  CheckDailyRepLogs   
 * @Description:日结流水日志表
 * @author: pokalb
 * @date:   2017年10月19日 下午4:01:18   
 *     
 * @Copyright: 2017 www.poka.com Inc. All rights reserved. 
 *
 */
@SuppressWarnings("serial")
@Entity
@IdClass(CheckDailyRepLogsPK.class)
@Table(name = "CHECKDAILYREPLOGS")
public class CheckDailyRepLogs implements Serializable {

	private String operDate;
	private Integer type;
	
	@Id
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Id
	@Column(name = "OPERDATE", length = 10)
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
}
