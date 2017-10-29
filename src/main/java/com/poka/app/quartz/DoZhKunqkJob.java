package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.ZhengKunqkBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 整捆取款文件定时解析
 * @author lb
 *
 */
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
