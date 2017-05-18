package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.LanBiaoBusiness;
import com.poka.app.anno.bussiness.ZhengKunqkBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 执行定时删除dat和zt文件任务类
 * 
 * @author lb
 * 
 */
public class DoDatFileDeleteJob {
	private LanBiaoBusiness lanBiaoBusiness;
	private ZhengKunqkBusiness zhengKunqkBusiness;

	@Autowired
	public void setZhengKunqkBusiness(ZhengKunqkBusiness zhengKunqkBusiness) {
		this.zhengKunqkBusiness = zhengKunqkBusiness;
	}

	@Autowired
	public void setLanBiaoBusiness(LanBiaoBusiness lanBiaoBusiness) {
		this.lanBiaoBusiness = lanBiaoBusiness;
	}

	public void work() {
		if(ConstantUtil.delDatFileFlag.trim().equals("Enabled")){
			lanBiaoBusiness.deleteDatFile();
			zhengKunqkBusiness.deleteZtFile();
		}
	}
}
