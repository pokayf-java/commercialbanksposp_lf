package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.ChaoXJiaChaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 钞箱加钞
 * @author lb
 *
 */

public class DoChaoXJiaChaoJob {

	private ChaoXJiaChaoBusiness chaoXJiaChaoBusiness;
	
	@Autowired
	public void setChaoXJiaChaoBusiness(ChaoXJiaChaoBusiness chaoXJiaChaoBusiness) {
		this.chaoXJiaChaoBusiness = chaoXJiaChaoBusiness;
	}

	public void work() {
		
		if(ConstantUtil.chaoXJiaChaoFlag.trim().equals("Enabled")){
			chaoXJiaChaoBusiness.sendMonBoxAddMonInfo();
		}
		
	}

}
