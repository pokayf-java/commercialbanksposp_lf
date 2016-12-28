package com.poka.app.anno.enity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 1.1	核心系统存取款业务信息
 * @author lb
 *
 */
@Entity
@Table(name = "BUSINESSLIST_CORE")
public class BusinessListCore {
	private String id;					//内部唯一流水号
	private String bankNo;				//银行编号
	private String netNo;				//网点编号
	private String userId;				//柜员号
	private Timestamp businessDate;		//记账时间
	private String businessId;			//核心系统业务流水号
	private BigDecimal moneyTotal;		//金额
	private Character inOrOut;			//存取款标志
	private String zhaiYoms;			//摘要描述
	private String accountNo;			//账号
	private String accountName;			//账号名称
	private String accounType;			//账号类型
	private Character pubOrPri;			//对公对私类型(1:对公 2:对私)
	private Character channel;			//渠道类型
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
	
	@Column(name = "USERID",length = 8)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	@Column(name = "ACCOUNTNAME",length = 30)
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Column(name = "ACCOUNTYPE",length = 2)
	public String getAccounType() {
		return accounType;
	}
	public void setAccounType(String accounType) {
		this.accounType = accounType;
	}
	
	@Column(name = "PUBORPRI",length = 1)
	public Character getPubOrPri() {
		return pubOrPri;
	}
	public void setPubOrPri(Character pubOrPri) {
		this.pubOrPri = pubOrPri;
	}
	
	@Column(name = "CHANNEL",length = 1)
	public Character getChannel() {
		return channel;
	}
	public void setChannel(Character channel) {
		this.channel = channel;
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
	
	@Column(name = "ZHAIYOMS",length = 50)
	public String getZhaiYoms() {
		return zhaiYoms;
	}
	public void setZhaiYoms(String zhaiYoms) {
		this.zhaiYoms = zhaiYoms;
	}

}
