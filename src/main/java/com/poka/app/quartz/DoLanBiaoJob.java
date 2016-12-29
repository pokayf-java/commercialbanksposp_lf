package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.LanBiaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 蓝标业务信息 定时任务类
 * @author lb
 * 
 */
public class DoLanBiaoJob {
	
	private LanBiaoBusiness lanBiaoBusiness;
	
	@Autowired
	public void setLanBiaoBusiness(LanBiaoBusiness lanBiaoBusiness) {
		this.lanBiaoBusiness = lanBiaoBusiness;
	}

	public void work() {
		if(ConstantUtil.lanBiaoFlag.equals("Enabled")){
			lanBiaoBusiness.sendBusinessListCoreInfo();
			lanBiaoBusiness.sendBusinessListDetailInfo();
			lanBiaoBusiness.importODSData();
		}
	}
	
}
