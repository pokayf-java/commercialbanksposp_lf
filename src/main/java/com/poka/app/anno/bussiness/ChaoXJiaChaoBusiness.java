package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.MonBoxAddMonService;
import com.poka.app.anno.base.service.impl.MoneyOutService;
import com.poka.app.anno.enity.MonBoxAddMon;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;

/**
 * 钞箱加钞。
 * 
 * @author Administrator
 *
 */
@Component
public class ChaoXJiaChaoBusiness {

	Logger logger = Logger.getLogger(ChaoXJiaChaoBusiness.class);
	private MoneyOutService moneyOutService;
	private MonBoxAddMonService monBoxAddMonService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("moneyOutService")
	public void setMoneyOutService(MoneyOutService moneyOutService) {
		this.moneyOutService = moneyOutService;
	}
	
	@Autowired
	@Qualifier("monBoxAddMonService")
	public void setMonBoxAddMonService(MonBoxAddMonService monBoxAddMonService) {
		this.monBoxAddMonService = monBoxAddMonService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 钞箱加钞
	 */
	public void sendMonBoxAddMonInfo() {

		String nowDateTime = moneyOutService.getNowDate(1);
		String finishdate = getFinishDate(7);
		List<MonBoxAddMon> moList = monBoxAddMonService.getMonBoxAddMonList(finishdate);
		if (null != moList && moList.size() > 0) {
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			try {
				result = service.sendMonBoxAddMonInfo(moList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...");
			}
			if (result) {
				logger.info("MonBoxAddMon 信息同步成功...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				logger.info("共计" + moList.size() + "条.");
				moneyOutService.updateFinishDate(7, nowDateTime);
			} else {
				logger.info("处理MonBoxAddMon 失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("无符合条件的数据**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
	}

	/**
	 * 获取同步完成日期
	 * 
	 * @param type
	 * @return
	 */
	public String getFinishDate(int type) {
		String finishdate = moneyOutService.getFinishDate(type);
		if (null == finishdate || "".equals(finishdate)) {
			moneyOutService.insertFinishDate(type);
			finishdate = moneyOutService.getFinishDate(type);
		}
		return finishdate;
	}
}
