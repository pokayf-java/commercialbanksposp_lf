package com.poka.app.anno.enity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.poka.app.util.TimestampAdapter;

/**
 * 钞箱加钞
 * 
 * @author Administrator
 *
 */
public class MonBoxAddMonPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String scanId;
	private String monBoxId;
	private Timestamp operTime;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MonBoxAddMonPK) {
			MonBoxAddMonPK pk = (MonBoxAddMonPK) obj;
			if (pk.getMonBoxId().equals(this.monBoxId) && pk.getScanId().equals(this.scanId)
					&& pk.getOperTime().equals(this.operTime)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getMonBoxId() + this.getScanId() + this.getOperTime()).hashCode();
	}

	@Column(name = "SCANID", length = 24)
	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	@Column(name = "MONBOXID", length = 11)
	public String getMonBoxId() {
		return monBoxId;
	}

	public void setMonBoxId(String monBoxId) {
		this.monBoxId = monBoxId;
	}

	@Column(name = "OPERTIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getOperTime() {
		return operTime;
	}

	public void setOperTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp operTime) {
		this.operTime = operTime;
	}
}
