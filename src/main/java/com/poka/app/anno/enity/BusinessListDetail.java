package com.poka.app.anno.enity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 1.3	业务信息券别明细表
 * @author lb
 *
 */
@Entity
@Table(name = "BUSINESSLIST_DETAIL")
public class BusinessListDetail {
	private String id;					//内部唯一流水号
	private BigDecimal monval;			//面值
	private Integer records;			//张数
	private Date insertDate;		//插入时间
	
	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "INSERTDATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	@Column(name = "MONVAL")
	public BigDecimal getMonval() {
		return monval;
	}
	public void setMonval(BigDecimal monval) {
		this.monval = monval;
	}
	
	@Column(name = "RECORDS")
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}

}
