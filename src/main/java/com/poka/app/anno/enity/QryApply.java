package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MONQRYAPPLY")
public class QryApply implements Serializable {
	private String orderId;
	private String bankId;
	private String netId;
	private Date applyDate;
	private Character applyType;
	private String qryMon;
	private String qryManName;
	private String qryTel;
	private String qryCredType;
	private String qryCredNo;
	private String agentName;
	private String agentTel;
	private String agentCerdType;
	private String agentCredNo;
	private Character proveType;
	private String proveNo;
	private Date getMoneyDT;
	private Character getMoneyMode;
	private String netName;
	private String counterPos;
	private String getMoneyAddr;
	private Date qryDate_R;
	private String qryUnitId_R;
	private String no_R;
	private String qryMode_R;
	private Character qryResult_R;
	private String handler_R;
	private String checker_R;
	private Integer state;
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;

	
	
	@Override
	public String toString() {
		return "QryApply [orderID=" + orderId + ", bankId=" + bankId
				+ ", netId=" + netId + ", applyDate=" + applyDate
				+ ", applyType=" + applyType + ", qryMon=" + qryMon
				+ ", qryManName=" + qryManName + ", qryTel=" + qryTel
				+ ", qryCredType=" + qryCredType + ", qryCredNo=" + qryCredNo
				+ ", agentName=" + agentName + ", agentTel=" + agentTel
				+ ", agentCerdType=" + agentCerdType + ", agentCredNo="
				+ agentCredNo + ", proveType=" + proveType + ", proveNo="
				+ proveNo + ", getMoneyDT=" + getMoneyDT + ", getMoneyMode="
				+ getMoneyMode + ", netName=" + netName + ", counterPos="
				+ counterPos + ", getMoneyAddr=" + getMoneyAddr
				+ ", qryDate_R=" + qryDate_R + ", qryUnitId_R=" + qryUnitId_R
				+ ", no_R=" + no_R + ", qryMode_R=" + qryMode_R
				+ ", qryResult_R=" + qryResult_R + ", handler_R=" + handler_R
				+ ", checker_R=" + checker_R + ", state=" + state
				+ ", createUser=" + createUser + ", createDate=" + createDate
				+ ", modifyUser=" + modifyUser + ", modifyDate=" + modifyDate
				+ "]";
	}

	@Id
	@Column(name = "orderId",length=30)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "BankId",length=4)
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "NetId",length=4)
	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	@Column(name = "applyDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "applyType",length=1)
	public char getApplyType() {
		return applyType;
	}

	public void setApplyType(char applyType) {
		this.applyType = applyType;
	}

	@Column(name = "qryMon",length=20)
	public String getQryMon() {
		return qryMon;
	}

	public void setQryMon(String qryMon) {
		this.qryMon = qryMon;
	}

	@Column(name = "qryManName",length=20)
	public String getQryManName() {
		return qryManName;
	}

	public void setQryManName(String qryManName) {
		this.qryManName = qryManName;
	}

	@Column(name = "qryTel",length=20)
	public String getQryTel() {
		return qryTel;
	}

	public void setQryTel(String qryTel) {
		this.qryTel = qryTel;
	}

	@Column(name = "qryCredType",length=20)
	public String getQryCredType() {
		return qryCredType;
	}

	public void setQryCredType(String qryCredType) {
		this.qryCredType = qryCredType;
	}

	@Column(name = "qryCredNo",length=20)
	public String getQryCredNo() {
		return qryCredNo;
	}

	public void setQryCredNo(String qryCredNo) {
		this.qryCredNo = qryCredNo;
	}

	@Column(name = "agentName",length=20)
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Column(name = "agentTel",length=20)
	public String getAgentTel() {
		return agentTel;
	}

	public void setAgentTel(String agentTel) {
		this.agentTel = agentTel;
	}

	@Column(name = "agentCerdType",length=20)
	public String getAgentCerdType() {
		return agentCerdType;
	}

	public void setAgentCerdType(String agentCerdType) {
		this.agentCerdType = agentCerdType;
	}

	@Column(name = "agentCredNo",length=20)
	public String getAgentCredNo() {
		return agentCredNo;
	}

	public void setAgentCredNo(String agentCredNo) {
		this.agentCredNo = agentCredNo;
	}

	@Column(name = "proveType",length=1)
	public Character getProveType() {
		return proveType;
	}

	public void setProveType(Character proveType) {
		this.proveType = proveType;
	}

	@Column(name = "proveNo",length=20)
	public String getProveNo() {
		return proveNo;
	}

	public void setProveNo(String proveNo) {
		this.proveNo = proveNo;
	}

	@Column(name = "getMoneyDT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getGetMoneyDT() {
		return getMoneyDT;
	}

	public void setGetMoneyDT(Date getMoneyDT) {
		this.getMoneyDT = getMoneyDT;
	}

	@Column(name = "getMoneyMode",length=1)
	public Character getGetMoneyMode() {
		return getMoneyMode;
	}

	public void setGetMoneyMode(Character getMoneyMode) {
		this.getMoneyMode = getMoneyMode;
	}
	
	@Column(name = "netName",length=70)
	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	@Column(name = "counterPos",length=70)
	public String getCounterPos() {
		return counterPos;
	}

	public void setCounterPos(String counterPos) {
		this.counterPos = counterPos;
	}

	@Column(name = "getMoneyAddr",length=80)
	public String getGetMoneyAddr() {
		return getMoneyAddr;
	}

	public void setGetMoneyAddr(String getMoneyAddr) {
		this.getMoneyAddr = getMoneyAddr;
	}

	@Column(name = "qryDate_R")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getQryDate_R() {
		return qryDate_R;
	}

	public void setQryDate_R(Date qryDate_R) {
		this.qryDate_R = qryDate_R;
	}

	@Column(name = "qryUnitId_R",length=4)
	public String getQryUnitId_R() {
		return qryUnitId_R;
	}

	public void setQryUnitId_R(String qryUnitId_R) {
		this.qryUnitId_R = qryUnitId_R;
	}

	@Column(name = "No_R",length=20)
	public String getNo_R() {
		return no_R;
	}

	public void setNo_R(String no_R) {
		this.no_R = no_R;
	}

	@Column(name = "qryMode_R",length=255)
	public String getQryMode_R() {
		return qryMode_R;
	}

	public void setQryMode_R(String qryMode_R) {
		this.qryMode_R = qryMode_R;
	}

	@Column(name = "qryResult_R",length=1)
	public Character getQryResult_R() {
		return qryResult_R;
	}

	public void setQryResult_R(Character qryResult_R) {
		this.qryResult_R = qryResult_R;
	}

	@Column(name = "handler_R",length=20)
	public String getHandler_R() {
		return handler_R;
	}

	public void setHandler_R(String handler_R) {
		this.handler_R = handler_R;
	}

	@Column(name = "checker_R",length=20)
	public String getChecker_R() {
		return checker_R;
	}

	public void setChecker_R(String checker_R) {
		this.checker_R = checker_R;
	}

	@Column(name = "State",length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "CreateUser",length=20)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "ModifyUser",length=20)
	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Column(name = "ModifyDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
