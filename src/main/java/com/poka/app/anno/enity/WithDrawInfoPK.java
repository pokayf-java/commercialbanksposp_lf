package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

public class WithDrawInfoPK implements Serializable {

	private String scanId;
	private Date operTime;
	private String accountNo;

	@Override
	public int hashCode() {
		return (this.getScanId() + this.getOperTime() + this.getAccountNo()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof WithDrawInfoPK) {
			WithDrawInfoPK pk = (WithDrawInfoPK) obj;
			if (pk.getScanId().equals(this.scanId) && pk.getOperTime().equals(this.operTime)
					&& pk.getAccountNo().equals(this.accountNo)) {
				return true;
			}

		}
		return false;
	}

	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
