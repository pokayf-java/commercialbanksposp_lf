package com.poka.app.anno.enity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.poka.app.util.TimestampAdapter;

/**
 * 刷卡记录
 * 
 * @author lb
 *
 */
@Entity
@Table(name = "SKJL")
public class ShuaKaJiLu {
	private String id; 				// 内部唯一流水号
	private String bankNo; 			// 银行编号
	private String netNo; 			// 网点编号
	private String userId; 			// 柜员号
	private String perCode;			// 机具号
	private Timestamp businessDate; // 记账时间
	private String accountNo; 		// 账号
	private Timestamp insertDate;   // 插入时间

	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "BANKNO", length = 20)
	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "NETNO", length = 20)
	public String getNetNo() {
		return netNo;
	}

	public void setNetNo(String netNo) {
		this.netNo = netNo;
	}

	@Column(name = "USERID", length = 8)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "BUSINESSDATE")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp businessDate) {
		this.businessDate = businessDate;
	}

	@Column(name = "ACCOUNTNO", length = 30)
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Column(name = "INSERTDATE")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	@Column(name = "PERCODE", length = 30)
	public String getPerCode() {
		return perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}


}
