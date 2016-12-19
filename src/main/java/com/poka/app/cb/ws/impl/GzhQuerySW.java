package com.poka.app.cb.ws.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.GzhQueryBusiness;
import com.poka.app.cb.ws.IgzhQuery;

public class GzhQuerySW implements IgzhQuery {

	private GzhQueryBusiness gzhQueryBussiness;
	
	@Autowired
	public void setGzhQueryBussiness(GzhQueryBusiness gzhQueryBussiness) {
		this.gzhQueryBussiness = gzhQueryBussiness;
	}

	@Override
	public String gzhQueryList(String mon) {	
		String result = "";
		try {
			result = gzhQueryBussiness.queryGzh(mon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
