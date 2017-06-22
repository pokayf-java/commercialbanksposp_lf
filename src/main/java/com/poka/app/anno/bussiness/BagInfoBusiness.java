package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 横向调拨业务处理
 */
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BagInfoService;
import com.poka.app.anno.enity.BagInfo;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;

@Component
public class BagInfoBusiness {

	Logger logger = Logger.getLogger(BagInfoBusiness.class);

	private BagInfoService bagInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("bagInfoService")
	public void setBagInfoService(BagInfoService bagInfoService) {
		this.bagInfoService = bagInfoService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 查询横向调拨信息
	 */
	public void sendBagInfo() {

		// 查询是否有订单信息
		List orderInfoList = bagInfoService.doSelectTRS_Order();

		int orderListSize = orderInfoList.size();
		if (null != orderInfoList && orderListSize > 0) {
			for (int i = 0; i < orderListSize; i++) {
				Map map = (Map) orderInfoList.get(i);
				List<BagInfo> bagInfoList = bagInfoService.doSelectBagInfo(map.get("ORDERID").toString());
				if (null != bagInfoList && bagInfoList.size() > 0) {
					handleSendBagInfoData(bagInfoList, map.get("ORDERID").toString());
				}
			}
		} else {
			logger.info("无横向调拨订单信息...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}

	}

	public void handleSendBagInfoData(List<BagInfo> list, String orderId) {

		logger.info("横向调拨订单号：" + orderId + "，共计" + list.size() + "条");
		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		try {
			result = service.sendBagInfo(list);
		} catch (Exception ex) {
			logger.info("连接服务器失败...**" + PokaDateUtil.getNow() + "**");
		}
		if (result) {
			// 同步更新状态
			bagInfoService.doUpdateOrderStatus(orderId);
			logger.info("处理横向调拨订单：" + orderId + "成功...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		} else {
			logger.info("处理横向调拨订单：" + orderId + "失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收人行下发的取款信息
	 * 
	 * @param bagInfoList
	 * @return
	 */
	public boolean insertBagInfo(List<BagInfo> bagInfoList) {
		int listSize = bagInfoList.size();
		if (listSize <= 0 || null == bagInfoList) {
			return false;
		} else {
			for (BagInfo bagInfo : bagInfoList) {
				bagInfoService.save(bagInfo);
			}
		}
		logger.info("取款数据同步成功(" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")");
		return true;
	}
}
