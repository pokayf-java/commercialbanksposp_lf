package com.poka.app.anno.enity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.poka.app.util.TimestampAdapter;

/**
 * 网点配钞
 * 
 * @author Administrator
 *
 */
public class MoneyOutPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String netId;
	private String scanId;
	private Timestamp operTime;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MoneyOutPK) {
			MoneyOutPK pk = (MoneyOutPK) obj;
			if (pk.getNetId().equals(this.netId) && pk.getScanId().equals(this.scanId) && 
					pk.getOperTime().equals(this.operTime)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getNetId() + this.getOperTime()+ this.getScanId()).hashCode();
	}

	@Column(name = "OPERTIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getOperTime() {
		return operTime;
	}
	public void setOperTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp operTime) {
		this.operTime = operTime;
	}
	@Column(name = "NETID", length = 20)
	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	@Column(name = "SCANID", length = 24)
	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

}
