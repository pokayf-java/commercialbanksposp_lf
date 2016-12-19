package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

public class SendFilePK implements Serializable {

	private String bankNo;
	private String fileName;
	private Date checkDate;
	private String sendState;
	private Date sendDate;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SendFilePK) {
			SendFilePK pk = (SendFilePK) obj;
			if (pk.getBankNo().equals(this.bankNo) && pk.getFileName().equals(this.fileName)
					&& pk.getCheckDate().equals(this.checkDate) && pk.getSendState().equals(this.sendState)
					&& pk.getSendDate().equals(this.sendDate)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getBankNo() + this.getFileName() + this.getCheckDate() + this.getSendState() + this.getSendDate())
				.hashCode();
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

}
