package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 整捆取款
 * 
 * @author Administrator
 *
 */
@Entity
@IdClass(WithDrawInfoPK.class)
@Table(name = "WITHDRAWINFO")
public class WithDrawInfo implements Serializable {

	private String scanId;
	private String flage;
	private Date operTime;
	private String businessType;
	private String operatorId;
	private String checker;
	private String bankNo;
	private String agencyNo;
	private String deskNo;
	private String bankSeq;
	private String perCode;
	private String boxId;
	private String accountNo;
	private String fileName;
	private String remark;

	@Id
	@Column(name = "SCANID",length = 24)
	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	@Column(name = "FLAGE",length = 1 )
	public String getFlage() {
		return flage;
	}

	public void setFlage(String flage) {
		this.flage = flage;
	}

	@Id
	@Column(name = "OPERTIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "BUSINESSTYPE",length = 4 )
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "OPERATORID",length = 20 )
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	@Column(name = "CHECKER",length = 20 )
	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	@Column(name = "BANKNO",length = 20 )
	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "AGENCYNO",length = 20 )
	public String getAgencyNo() {
		return agencyNo;
	}

	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}

	@Column(name = "DESKNO",length = 12 )
	public String getDeskNo() {
		return deskNo;
	}

	public void setDeskNo(String deskNo) {
		this.deskNo = deskNo;
	}

	@Column(name = "BANKSEQ",length = 24 )
	public String getBankSeq() {
		return bankSeq;
	}

	public void setBankSeq(String bankSeq) {
		this.bankSeq = bankSeq;
	}

	@Column(name = "PERCODE",length = 24 )
	public String getPerCode() {
		return perCode;
	}
	
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

	@Column(name = "BOXID",length = 24 )
	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	@Id
	@Column(name = "ACCOUNTNO",length = 25 )
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "FILENAME",length = 60 )
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name= "REMARK", length = 512)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
