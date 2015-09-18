package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.poka.app.anno.base.service.impl.BranchInfoService;
import com.poka.app.anno.base.service.impl.PerInfoService;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.PerInfo;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;

@Component
public class PerInfoAndBranchInfoBussiness {

	Logger logger = Logger.getLogger(PerInfoAndBranchInfoBussiness.class);

	private PerInfoService perInfoService;

	private BranchInfoService branchInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("perInfoService")
	public void setPerInfoService(PerInfoService perInfoService) {
		this.perInfoService = perInfoService;
	}

	@Autowired
	@Qualifier("branchInfoService")
	public void setBranchInfoService(BranchInfoService branchInfoService) {
		this.branchInfoService = branchInfoService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	public void SendPerinfoAndBranchInfo() {
		SendPerInfo();
		SendBranchInfo();
	}

	public void SendPerInfo() {
		int maxCount = 15;
		int first = 0;
		int count = this.perInfoService.getPerinfoCount();
		if (count <= 0) {
			logger.info("perinfo size is zero");
			return;
		} else {
			while (true) {
				List<PerInfo> list = perInfoService.getPerinfoList(first, maxCount);
				handlePerInfoData(list);
				first += maxCount;
				if (list.size() < maxCount || first >= count) {
					break;
				}
			}
		}
		logger.info("finished handle perinfo");
	}

	public void handlePerInfoData(List<PerInfo> list) {
		logger.info("perinfo list size is " + list.size());
		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
	
		boolean result = Boolean.FALSE;
		try {
			result = service.getPerInfoData(new Gson().toJson(list));
		} catch (Exception ex) {
			logger.info("连接服务器失败!");
		}
		if (result) {
			logger.info("处理perinfodata  成功");
		} else {
			logger.info("处理perinfodata 失败");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public void SendBranchInfo() {
		int maxCount = 15;
		int first = 0;
		int count = this.branchInfoService.getBranchInfoCount();
		if (count <= 0) {
			logger.info("branchInfo size is zero");
			return;
		} else {
			while (true) {                               
				List<BranchInfo> list = branchInfoService.getBranchInfoList(first, maxCount);
				handleBranchInfoData(list);
				first += maxCount;
				if (list.size() < maxCount || first >= count) {
					break;
				}
			}
		}
		logger.info("finished handle perinfo");
	}
	
	public void handleBranchInfoData(List<BranchInfo> list) {
		logger.info("BranchInfo list size is " + list.size());
		
		logger.info("lits"+new Gson().toJson(list));
		
		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		try {
			result = service.getBanchInfoData(new Gson().toJson(list));
		} catch (Exception ex) {
			logger.info("连接服务器失败!");
		}
		if (result) {
			logger.info("处理BranchInfo  成功");
		} else {
			logger.info("处理BranchInfo 失败");
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
