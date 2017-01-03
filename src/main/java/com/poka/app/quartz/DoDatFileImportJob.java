package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.LanBiaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * dat文件导入定时任务类
 * @author lb
 * 
 */
public class DoDatFileImportJob {
	
	private LanBiaoBusiness lanBiaoBusiness;
	
	@Autowired
	public void setLanBiaoBusiness(LanBiaoBusiness lanBiaoBusiness) {
		this.lanBiaoBusiness = lanBiaoBusiness;
	}

	public void work() {
		if(ConstantUtil.datImportFlag.trim().equals("Enabled")){
			lanBiaoBusiness.importODSData();
		}
	}
	
}
