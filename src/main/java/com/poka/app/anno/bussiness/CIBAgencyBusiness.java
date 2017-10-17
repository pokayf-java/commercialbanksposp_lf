package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.TagencyCheckService;
import com.poka.app.anno.enity.TAgencyCheck;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;


/**
 * 兴业银行代理取款下发业务
 * @author enma.ai
 * 2017年9月19日
 */
@Component
public class CIBAgencyBusiness {
	Logger logger = Logger.getLogger(CIBAgencyBusiness.class);
	private TagencyCheckService tagencyCheckService;

	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}
	
	@Autowired
	@Qualifier("tagencyCheckService")
	public void setTagencyCheckService(TagencyCheckService tagencyCheckService) {
		this.tagencyCheckService = tagencyCheckService;
	}

	
	/**
	 * 兴业银行代理取款下发业务
	 * 2017年9月19日
	 * @author Enma.ai
	 * @return void
	 */
	public void agencyBankSync() {
		List<TAgencyCheck> checks = this.tagencyCheckService.getUnsendCheck();
		if(checks.size()<=0){
			return;
		}
		logger.info("未处理代理订单数量为:"+checks.size());
		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		try{
		 result = service.agencyCheck(checks);
		}catch(Exception ex){
			logger.info("连接服务器失败...**"+PokaDateUtil.getNow()+"**");
		}
		if (result) {
			this.tagencyCheckService.updateCheckState(checks);
			logger.info("处理代理取款订单成功...**"+PokaDateUtil.getNow()+"**");
		}else{
			logger.info("处理代理取款订单失败...**"+PokaDateUtil.getNow()+"**");
		}
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
