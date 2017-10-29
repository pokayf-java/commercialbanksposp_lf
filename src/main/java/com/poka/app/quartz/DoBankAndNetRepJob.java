package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.BankAndNetRepBusiness;
import com.poka.app.util.ConstantUtil;

/**
 * 银行、网点日报同步更新定时任务类
 * @author lb
 * 
 */
public class DoBankAndNetRepJob {
	
	private BankAndNetRepBusiness bankAndNetRepBussiness;
	
	@Autowired
	public void setBankAndNetRepBussiness(BankAndNetRepBusiness bankAndNetRepBussiness) {
		this.bankAndNetRepBussiness = bankAndNetRepBussiness;
	}

	public void work() {
		
		if(ConstantUtil.bankAndNetRepFlag.equals("Enabled")){
//			bankAndNetRepBussiness.sendBankCheckRepList();
//			bankAndNetRepBussiness.sendNetCheckRepList();
			bankAndNetRepBussiness.sendNetCheckRepListFlow();
			bankAndNetRepBussiness.sendBankCheckRepListFlow();
			
		}
	}
	
}
