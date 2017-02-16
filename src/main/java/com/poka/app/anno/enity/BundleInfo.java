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
 * 钞箱进ATM
 * 捆进ATM
 * @author lb
 * 
 */
@Entity
@Table(name = "BUNDLEINFO")
@IdClass(value = BundleInfoPK.class)
public class BundleInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String bundleId;
	private String perCode;
	private Timestamp scanTime;
	private Timestamp createTime;
	private String operatorId;
	private String checker;
	private String addMonOperator;
	private String addMonChecker;
	private Timestamp addMonTime;
	private String flag;
	private String bankNo;
	private String agencyNo;
	private String fileName;
	private String id;
	
	@Id
	@Column(name = "BUNDLEID", length = 24)
	public String getBundleId() {
		return bundleId;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	
	@Id
	@Column(name = "PERCODE", length = 24)
	public String getPerCode() {
		return perCode;
	}
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	
	@Id
	@Column(name = "SCANTIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getScanTime() {
		return scanTime;
	}
	public void setScanTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp scanTime) {
		this.scanTime = scanTime;
	}
	
	@Column(name = "CREATETIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "OPERATORID", length = 20)
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	@Column(name = "CHECKER", length = 20)
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	
	@Column(name = "ADDMONOPERATOR", length = 20)
	public String getAddMonOperator() {
		return addMonOperator;
	}
	public void setAddMonOperator(String addMonOperator) {
		this.addMonOperator = addMonOperator;
	}
	
	@Column(name = "ADDMONCHECKER", length = 20)
	public String getAddMonChecker() {
		return addMonChecker;
	}
	public void setAddMonChecker(String addMonChecker) {
		this.addMonChecker = addMonChecker;
	}
	
	@Column(name = "ADDMONTIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getAddMonTime() {
		return addMonTime;
	}
	public void setAddMonTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp addMonTime) {
		this.addMonTime = addMonTime;
	}
	
	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "BANKNO", length = 20)
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	@Column(name = "AGENCYNO", length = 20)
	public String getAgencyNo() {
		return agencyNo;
	}
	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}
	
	@Column(name = "FILENAME", length = 60)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "ID", length = 30)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
