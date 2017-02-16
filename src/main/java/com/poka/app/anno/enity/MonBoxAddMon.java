package com.poka.app.anno.enity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.poka.app.util.TimestampAdapter;

/**
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "MONBOXADDMON")
@IdClass(value = MonBoxAddMonPK.class)
public class MonBoxAddMon implements Serializable {

	private static final long serialVersionUID = 1L;
	private String scanId;
	private String flag;
	private String monBoxId;
	private String remark;
	private Timestamp operTime;
	private String operatorId;
	private String checker;
	private Timestamp insertDate;

	@Id
	@Column(name = "SCANID", length = 24)
	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "OPERATORID", length = 20)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Id
	@Column(name = "OPERTIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getOperTime() {
		return operTime;
	}

	public void setOperTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp operTime) {
		this.operTime = operTime;
	}

	@Column(name = "REMARK", length = 512)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Id
	@Column(name = "MONBOXID", length = 11)
	public String getMonBoxId() {
		return monBoxId;
	}

	public void setMonBoxId(String monBoxId) {
		this.monBoxId = monBoxId;
	}

	@Column(name = "CHECKER", length = 20)
	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	@Column(name = "INSERTDATE")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp insertDate) {
		this.insertDate = insertDate;
	}

}
