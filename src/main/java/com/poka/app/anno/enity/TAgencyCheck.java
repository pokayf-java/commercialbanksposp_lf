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
 * 2017年9月19日09:57:02
 * @author enma.ai
 * 廊坊兴业银行代理需求新增表T_AgencyCheck
 */
@Entity
@IdClass(TAgencyCheckPK.class)
@Table(name = "T_AgencyCheck")
public class TAgencyCheck implements Serializable {
	
	private String bagCode;
	private String bankId;
	private String netId;
	/*标签类型（0袋标签，1捆标签）*/
	private Integer codeType;
	private Date opDate;
	private String opId;
	private Integer sType;
	/*同步状态（0未同步，1已同步）*/
	private Integer syncStates;
	/*同步时间*/
	private Date syncDate;
	
	@Id
	@Column(name = "BagCode",length=24)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	@Id
	@Column(name = "bank_Id",length=4)
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	@Column(name = "net_id",length=4)
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	@Column(name = "CodeType")
	public Integer getCodeType() {
		return codeType;
	}
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	@Column(name = "opDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	@Column(name = "op_id",length=20)
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	@Column(name = "sType")
	public Integer getsType() {
		return sType;
	}
	public void setsType(Integer sType) {
		this.sType = sType;
	}
	@Column(name = "SyncStates")
	public Integer getSyncStates() {
		return syncStates;
	}
	public void setSyncStates(Integer syncStates) {
		this.syncStates = syncStates;
	}
	@Column(name = "SyncDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSyncDate() {
		return syncDate;
	}
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
	
}
