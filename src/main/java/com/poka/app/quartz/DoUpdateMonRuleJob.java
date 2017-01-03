package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.MonRuleBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 向村镇银行下发可疑冠字号定时任务类
 * @author Administrator
 *
 */

public class DoUpdateMonRuleJob {

	MonRuleBusiness monRuleBussiness;
	
	@Autowired
	public void setMonRuleBussiness(MonRuleBusiness monRuleBussiness) {
		this.monRuleBussiness = monRuleBussiness;
	}

	public void work() {
		
		if(ConstantUtil.monRuleCZYHFlag.trim().equals("Enabled")){
			monRuleBussiness.sendMonRuleDataForCZYH();
		}
		
	}

}
