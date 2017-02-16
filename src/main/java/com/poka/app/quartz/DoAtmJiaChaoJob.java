package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AtmJiaChaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * ATM加钞
 * @author lb
 *
 */

public class DoAtmJiaChaoJob {

	private AtmJiaChaoBusiness atmJiaChaoBusiness;
	
	@Autowired
	public void setAtmJiaChaoBusiness(AtmJiaChaoBusiness atmJiaChaoBusiness) {
		this.atmJiaChaoBusiness = atmJiaChaoBusiness;
	}

	public void work() {
		
		if(ConstantUtil.atmJiaChaoFlag.trim().equals("Enabled")){
			atmJiaChaoBusiness.sendBundleInfo();;
		}
		
	}

}
