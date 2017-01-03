package com.poka.app.anno.bussiness;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BusinessListCoreService;
import com.poka.app.anno.base.service.impl.BusinessListDetailService;
import com.poka.app.anno.enity.BusinessListCore;
import com.poka.app.anno.enity.BusinessListDetail;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.ConstantUtil;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.PokaDateUtil;

/**
 * 蓝标数据同步业务类
 * 
 * @author lb
 *
 */
@Component
public class LanBiaoBusiness {

	Logger logger = Logger.getLogger(LanBiaoBusiness.class);
	private BusinessListCoreService businessListCoreService;
	private BusinessListDetailService businessListDetailService;

	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("businessListDetailService")
	public void setBusinessListDetailService(BusinessListDetailService businessListDetailService) {
		this.businessListDetailService = businessListDetailService;
	}
	
	@Autowired
	@Qualifier("businessListCoreService")
	public void setBusinessListCoreService(BusinessListCoreService businessListCoreService) {
		this.businessListCoreService = businessListCoreService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 商行核心业务数据(BusinessListCore表)同步至人行
	 */
	public void sendBusinessListCoreInfo() {

		String finishdate = businessListCoreService.getFinishDate(1);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(1);
			finishdate = businessListCoreService.getFinishDate(1);
		}
		List<BusinessListCore> blcList = businessListCoreService.getBusinessListCore(finishdate);
		if (null != blcList && blcList.size() > 0) {
			for(BusinessListCore blc:blcList){
				System.out.println("bDate:"+blc.getBusinessDate());
				System.out.println("iDate："+blc.getInsertDate());
			}
			sendBusinessListCoreInfo(blcList);
		}else {
			logger.info("核心业务数据表没有要同步的数据...**"+PokaDateUtil.getNow()+"**");
		} 
	}

	public void sendBusinessListCoreInfo(List<BusinessListCore> dataList) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendBusinessListCoreInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**"+PokaDateUtil.getNow()+"**");
			}
			if (result) {
				logger.info("(核心业务数据)BusinessListCore 数据同步成功... **"+PokaDateUtil.getNow()+"**");
				logger.info("总计： " + dataList.size() + "条.");
				businessListCoreService.updateFinishDate(1);
			} else {
				logger.info("(核心业务数据)BusinessListCore 数据同步失败... **"+PokaDateUtil.getNow()+"**");
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 商行核心业务信息券别明细(BusinessListCore表)同步至人行
	 */
	public void sendBusinessListDetailInfo() {

		String finishdate = businessListCoreService.getFinishDate(2);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(2);
			finishdate = businessListCoreService.getFinishDate(2);
		}
		List<BusinessListDetail> bldList = businessListDetailService.getBusinessListDetail(finishdate);
		if (null != bldList && bldList.size() > 0) {
			sendBusinessListDetailInfo(bldList);
		} else {
			logger.info("业务信息券别明细表没有要同步的数据...**"+PokaDateUtil.getNow()+"**");
		} 
	}

	public void sendBusinessListDetailInfo(List<BusinessListDetail> dataList) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendBusinessListDetailInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**"+PokaDateUtil.getNow()+"**");
			}
			if (result) {
				logger.info("(业务信息券别明细)BusinessListDetail 数据同步成功... **"+PokaDateUtil.getNow()+"**");
				logger.info("总计： " + dataList.size() + "条.");
				businessListCoreService.updateFinishDate(2);
			} else {
				logger.info("(业务信息券别明细)BusinessListDetail 数据同步失败... **"+PokaDateUtil.getNow()+"**");
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 导入ODS传过来的dat文件
	 */
	public void importODSData()	{
		
		String today = new SimpleDateFormat("yyyyMMdd").format(PokaDateUtil.getNextDay(new Date()));
		File file = new File(ConstantUtil.filePath+File.separator+today);
		File[] tempList = file.listFiles();
		if(tempList ==null ){
			logger.info("没有相应的dat文件或文件路径有误...**"+PokaDateUtil.getNow()+"**");
			return;
		}
		String fileName = "";
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				if(tempList[i].getName().endsWith(".dat")) {
					fileName = tempList[i].getName();
				}
			}
		}
		Integer num = businessListCoreService.importODSData(fileName);
		if(num > 0){
			logger.info("导入dat数据文件成功...**"+PokaDateUtil.getNow()+"**");
			logger.info("dat文件记录数("+num+")条...");
		} else {
			logger.info("导入dat数据文件失败...**"+PokaDateUtil.getNow()+"**");
		}
		
	}
	
}
