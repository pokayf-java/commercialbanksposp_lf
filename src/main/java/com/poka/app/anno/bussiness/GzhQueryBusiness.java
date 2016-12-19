package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.poka.app.anno.base.service.impl.MoneyDataService;
import com.poka.app.anno.enity.MoneyDataInfo;

@Component
public class GzhQueryBusiness {
	Logger logger = Logger.getLogger(GzhQueryBusiness.class);
	private MoneyDataService moneyDataService;

	@Autowired
	public void setMoneyDataService(MoneyDataService moneyDataService) {
		this.moneyDataService = moneyDataService;
	}

	public String queryGzh(String dealNo) throws Exception {
		List<MoneyDataInfo> moneyDataList;
		logger.info("查询冠字号:" + dealNo);
		if (dealNo.indexOf("*") > -1) {
			dealNo = dealNo.replace("*", "_");
			moneyDataList = moneyDataService.findMoneyDataListByLike(dealNo);
		} else {
			moneyDataList = moneyDataService.findMoneyDataList(dealNo);
		}

		logger.info("查询冠字号" + dealNo + "共" + moneyDataList.size() + "条结果！");
		if (moneyDataList != null && moneyDataList.size() > 0) {
			Gson gson = new Gson();
			return gson.toJson(moneyDataList);
		} else {
			return "";
		}
	}
	public List<MoneyDataInfo> queryGzhList(String dealNo){
		List<MoneyDataInfo> moneyDataList;
		try {
			logger.info("查询冠字号:" + dealNo);
			if (dealNo.indexOf("*") > -1) {
				dealNo = dealNo.replace("*", "_");
				moneyDataList = moneyDataService.findMoneyDataListByLike(dealNo);
			} else {
				moneyDataList = moneyDataService.findMoneyDataList(dealNo);
			}
			logger.info("查询冠字号" + dealNo + "共" + moneyDataList.size() + "条结果！");
//			try {
//				Thread.sleep(60000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			if (moneyDataList != null && moneyDataList.size() > 0) {
				return moneyDataList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
