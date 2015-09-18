package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.poka.app.anno.base.service.impl.MoneyDataService;
import com.poka.app.anno.enity.MoneyDataInfo;

@Component
public class GzhQueryBussiness {
	Logger logger = Logger.getLogger(GzhQueryBussiness.class);
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

}
