package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BranchInfoService;
import com.poka.app.anno.base.service.impl.MonRuleService;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.MonRule;
import com.poka.app.cb.ws.ICBPospSW;
import com.poka.app.util.CxfUtil;

@Component
public class MonRuleBusiness {

	Logger logger = Logger.getLogger(MonRuleBusiness.class);

	private MonRuleService monRuleService;
	
	private BranchInfoService branchInfoService;
	
	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}
	
	@Autowired
	public void setMonRuleService(MonRuleService monRuleService) {
		this.monRuleService = monRuleService;
	}
	
	@Autowired
	public void setBranchInfoService(BranchInfoService branchInfoService) {
		this.branchInfoService = branchInfoService;
	}
	
	public boolean getMonRuleData(List<MonRule> monRuleList){
		if(null!=monRuleList&&monRuleList.size()>0){
			for(MonRule monRule:monRuleList){
				//1：表示来至人行的同步可疑数据
				monRule.setSourceType("1");
				monRuleService.save(monRule);
			}
		}
		return true;
	}
	
	/**
	 *	将清分中心的可疑冠字号同步到村镇银行
	 * @param monRuleList
	 */
	
	public void sendMonRuleDataForCZYH(){
		
		List<BranchInfo> branchInfoList = branchInfoService.getBranchInfoList();
		if (branchInfoList.size() > 0) {
			for (int i = 0; i < branchInfoList.size(); i++) {

				BranchInfo branchInfo = branchInfoList.get(i);
				String branchIp = branchInfo.getAddress().trim();

				ICBPospSW service = cxfUtil.getCxfClient(ICBPospSW.class, cxfUtil.getUrl(branchIp, cxfUtil.getPort()));
				cxfUtil.recieveTimeOutWrapper(service);

				boolean result = Boolean.FALSE;
				List<MonRule> monRuleList = monRuleService.getMonRuleList();
				
				if (null != monRuleList && monRuleList.size() > 0) {
					try {
						result = service.sendMonRuleDataForCZYH(monRuleList);
					} catch (Exception ex) {
						logger.info("连接服务器失败...");
					}
					if (result) {
						logger.info("ip:" + branchIp + "可疑币数据同步成功...("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
						logger.info("共计" + monRuleList.size() + "条");
					} else {
						logger.info("ip:" + branchIp + "可疑币数据同步失败...("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}
		
	}

}
