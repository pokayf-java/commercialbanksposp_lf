package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.ZhengKunqkBusiness;
import com.poka.app.util.ConstantUtil;

public class DoZhKunqkJob {

	private ZhengKunqkBusiness zhengKunqkBusiness;

	@Autowired
	public void setZhengKunqkBusiness(ZhengKunqkBusiness zhengKunqkBusiness) {
		this.zhengKunqkBusiness = zhengKunqkBusiness;
	}

	public void work() {
		if (ConstantUtil.ztFileFlag.trim().equals("Enabled")) {
			zhengKunqkBusiness.analyzeZtFile();
		}
	}
}
