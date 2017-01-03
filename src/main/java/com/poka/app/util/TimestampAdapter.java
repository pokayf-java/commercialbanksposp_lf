package com.poka.app.util;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {

	public String marshal(Timestamp time) throws Exception {
		return TimestampDateUtil.timestamp2Str(time);
	}


	public Timestamp unmarshal(String str) throws Exception {
		return TimestampDateUtil.str2Timestamp(str);
	}

}
