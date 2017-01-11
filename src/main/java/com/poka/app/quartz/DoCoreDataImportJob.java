package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.LanBiaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 执行生成核心数据存储过程
 * @author lb
 * 
 */
public class DoCoreDataImportJob {
	
	private LanBiaoBusiness lanBiaoBusiness;
	
	@Autowired
	public void setLanBiaoBusiness(LanBiaoBusiness lanBiaoBusiness) {
		this.lanBiaoBusiness = lanBiaoBusiness;
	}

	public void work() {
		if(ConstantUtil.cdiProduceFlag.trim().equals("Enabled")){
			lanBiaoBusiness.doImportCoreDatPro();;
		}
	}
	
}
