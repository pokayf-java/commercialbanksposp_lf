package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.enumtype.OrderType;
import com.poka.app.enumtype.StateType;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;

@Component
public class QryApplyBusiness {
	Logger logger = Logger.getLogger(QryApplyBusiness.class);
	private OrderInfoService orderInfoService;
	
	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	@Autowired
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	/**
	 * 查询申请
	 */
	public void makeQryApply() {
		List<OrderInfo> orders = this.orderInfoService
				.getUnsendOrder(OrderType.APPLICATION);
		if (orders.size() <= 0) {
			return;
		}
		logger.info("未处理查询申请订单数量为:" + orders.size());
		for (OrderInfo order : orders) {
			String orderId = order.getOrderId().trim();
			logger.info("处理查询申请订单号:" + orderId);
			QryApply vo = this.orderInfoService.getQryApply(orderId);
			if (vo==null) {
				logger.info("continue:" + orderId);
				continue;
			}
			IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
			cxfUtil.recieveTimeOutWrapper(service);
		
			boolean result = Boolean.FALSE;
			try{
				result = service.makeQryApply(vo);
			}catch(Exception ex){
				logger.info("连接服务器失败...**"+PokaDateUtil.getNow()+"**");
			}
			if (result) {
				
				logger.info("处理查询申请订单:" + orderId+"  成功...**"+PokaDateUtil.getNow()+"**");
				this.orderInfoService.updateOrderInfoState(order,
						StateType.SENDED);
			}else{
				logger.info("处理查询申请订单:" + orderId+"  失败...**"+PokaDateUtil.getNow()+"**");
			}
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	
}
