package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.NetPeiChaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 网点配钞
 * @author lb
 *
 */

public class DoNetPeiChaoJob {

	private NetPeiChaoBusiness netPeiChaoBusiness;
	
	@Autowired
	public void setNetPeiChaoBusiness(NetPeiChaoBusiness netPeiChaoBusiness) {
		this.netPeiChaoBusiness = netPeiChaoBusiness;
	}

	public void work() {
		
		if(ConstantUtil.netPeiChaoFlag.trim().equals("Enabled")){
			netPeiChaoBusiness.sendMoneyOutList();
		}
		
	}

}
