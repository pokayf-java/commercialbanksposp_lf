package com.poka.app.anno.enity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.poka.app.util.TimestampAdapter;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class BundleInfoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bundleId;
	private String perCode;
	private Timestamp scanTime;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BundleInfoPK) {
			BundleInfoPK pk = (BundleInfoPK) obj;
			if (pk.getBundleId().equals(this.bundleId)&&
					pk.getPerCode().equals(this.perCode)&&
					pk.getScanTime().equals(this.scanTime)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getBundleId() + this.getPerCode() + this.getScanTime()).hashCode();
	}

	@Column(name = "BUNDLEID", length = 24)
	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	@Column(name = "PERCODE", length = 24)
	public String getPerCode() {
		return perCode;
	}
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	
	@Column(name = "SCANTIME")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getScanTime() {
		return scanTime;
	}
	public void setScanTime(@XmlJavaTypeAdapter(TimestampAdapter.class) Timestamp scanTime) {
		this.scanTime = scanTime;
	}

}
