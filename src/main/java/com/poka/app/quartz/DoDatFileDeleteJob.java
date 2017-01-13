package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.LanBiaoBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 执行定时删除dat文件任务类
 * 
 * @author lb
 * 
 */
public class DoDatFileDeleteJob {
	private LanBiaoBusiness lanBiaoBusiness;

	@Autowired
	public void setLanBiaoBusiness(LanBiaoBusiness lanBiaoBusiness) {
		this.lanBiaoBusiness = lanBiaoBusiness;
	}

	public void work() {
		if(ConstantUtil.delDatFileFlag.trim().equals("Enabled")){
			lanBiaoBusiness.deleteDatFile();
		}
	}
}
