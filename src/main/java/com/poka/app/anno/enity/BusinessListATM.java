package com.poka.app.anno.enity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 1.2	自助设备业务信息
 * @author lb
 *
 */
@Entity
@Table(name = "BUSINESSLIST_ATM")
public class BusinessListATM {
	private String id;					//内部唯一流水号
	private String bankNo;				//银行编号
	private String netNo;				//网点编号
	private String percode;				//机具号
	private Timestamp businessDate;		//记账时间
	private String businessId;			//atm系统业务流水号
	private BigDecimal moneyTotal;		//金额
	private Character inOrOut;			//存取款标志
	private String accountNo;			//账号
	private String fileName;			//FSN文件名
	private String coreId;				//与表BUSINESSLIST_CORE的ID字段对应。（外键）
	private String remark;				//备注
	private Timestamp inserDate;		//插入时间
	
	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "BANKNO",length = 20)
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	@Column(name = "NETNO",length = 20)
	public String getNetNo() {
		return netNo;
	}
	public void setNetNo(String netNo) {
		this.netNo = netNo;
	}
	
	@Column(name = "BUSINESSDATE")
	public Timestamp getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Timestamp businessDate) {
		this.businessDate = businessDate;
	}
	
	@Column(name = "BUSINESSID",length = 50)
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	@Column(name = "MONEYTOTAL")
	public BigDecimal getMoneyTotal() {
		return moneyTotal;
	}
	public void setMoneyTotal(BigDecimal moneyTotal) {
		this.moneyTotal = moneyTotal;
	}
	
	@Column(name = "INOROUT",length = 1)
	public Character getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(Character inOrOut) {
		this.inOrOut = inOrOut;
	}
	
	@Column(name = "ACCOUNTNO",length = 30)
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Column(name = "REMARK",length = 200)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "INSERDATE")
	public Timestamp getInserDate() {
		return inserDate;
	}
	public void setInserDate(Timestamp inserDate) {
		this.inserDate = inserDate;
	}
	
	@Column(name = "PERCODE",length = 30)
	public String getPercode() {
		return percode;
	}
	public void setPercode(String percode) {
		this.percode = percode;
	}
	
	@Column(name = "FILENAME",length = 50)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "COREID",length = 50)
	public String getCoreId() {
		return coreId;
	}
	public void setCoreId(String coreId) {
		this.coreId = coreId;
	}

}
