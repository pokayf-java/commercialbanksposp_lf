package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BundleInfoService;
import com.poka.app.anno.base.service.impl.MoneyOutService;
import com.poka.app.anno.enity.BundleInfo;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;

/**
 * 网点配钞、钞箱加钞，ATM加钞业务。
 * 
 * @author Administrator
 *
 */
@Component
public class AtmJiaChaoBusiness {

	Logger logger = Logger.getLogger(AtmJiaChaoBusiness.class);
	private MoneyOutService moneyOutService;
	private BundleInfoService bundleInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("moneyOutService")
	public void setMoneyOutService(MoneyOutService moneyOutService) {
		this.moneyOutService = moneyOutService;
	}
	
	@Autowired
	@Qualifier("bundleInfoService")
	public void setBundleInfoService(BundleInfoService bundleInfoService) {
		this.bundleInfoService = bundleInfoService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}
	
	/**
	 *ATM加钞
	 */
	public void sendBundleInfo() {
		
		String nowDateTime = moneyOutService.getNowDate(1);
		String finishdate = getFinishDate(8);
		List<BundleInfo> bundleinfList = bundleInfoService.getBundleInfoList(finishdate);
		if (null != bundleinfList && bundleinfList.size() > 0) {
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
			boolean result = Boolean.FALSE;
			try {
				result = service.sendBundleInfo(bundleinfList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...");
			}
			if (result) {
				logger.info("BundleInfo 信息同步成功...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				logger.info("共计" + bundleinfList.size() + "条.");
				moneyOutService.updateFinishDate(8, nowDateTime);
			} else {
				logger.info("处理BundleInfo 失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
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
