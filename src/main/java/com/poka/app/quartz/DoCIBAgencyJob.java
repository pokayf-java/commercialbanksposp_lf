package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.CIBAgencyBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 兴业银行代理业务
 * @author enma.ai
 * 2017年9月19日
 */
public class DoCIBAgencyJob {

	private CIBAgencyBusiness cibAgencyBusiness;
	
	@Autowired
	public void setCibAgencyBusiness(CIBAgencyBusiness cibAgencyBusiness) {
		this.cibAgencyBusiness = cibAgencyBusiness;
	}

	public void work() {
		if (ConstantUtil.cibAgencyFlag.trim().equals("Enabled")) {
			cibAgencyBusiness.agencyBankSync();
		}
	}
}
