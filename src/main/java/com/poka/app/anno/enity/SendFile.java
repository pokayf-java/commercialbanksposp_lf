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
 *
 *记录下发到商行的FSN文件记录
 */

@Entity
@IdClass(SendFilePK.class)
@Table(name = "SENDFILE")
public class SendFile implements Serializable {
	
	private String bankNo;			//银行号
	private String fileName;		//文件名
	private Date checkDate;			//清分时间
	private String sendState;		//发送状态
	private Date sendDate;			//发送日期
	
	@Id
	@Column(name = "BANKNO",length = 10)
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	@Id
	@Column(name = "FILENAME",length = 200)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "CHECKDATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Column(name = "SENDSTATE",length = 1)
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	
	@Column(name = "SENDDATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
}
