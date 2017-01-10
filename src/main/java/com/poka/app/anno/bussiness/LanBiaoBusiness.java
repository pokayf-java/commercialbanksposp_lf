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

		String nowDate = businessListCoreService.getNowDate();
		String finishdate = businessListCoreService.getFinishDate(1);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(1);
			finishdate = businessListCoreService.getFinishDate(1);
		}
		List<BusinessListCore> blcList = businessListCoreService.getBusinessListCore(finishdate, nowDate);
		if (null != blcList && blcList.size() > 0) {
			sendBusinessListCoreInfo(blcList, nowDate);
		} else {
			logger.info("核心业务数据表没有要同步的数据...**" + PokaDateUtil.getNow() + "**");
		}
	}

	public void sendBusinessListCoreInfo(List<BusinessListCore> dataList, String nowDate) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendBusinessListCoreInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**" + PokaDateUtil.getNow() + "**");
			}
			if (result) {
				logger.info("(核心业务数据)BusinessListCore 数据同步成功... **" + PokaDateUtil.getNow() + "**");
				logger.info("总计： " + dataList.size() + "条.");
				businessListCoreService.updateFinishDate(1, nowDate);
			} else {
				logger.info("(核心业务数据)BusinessListCore 数据同步失败... **" + PokaDateUtil.getNow() + "**");
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 商行核心业务信息券别明细(BusinessListDetail表)同步至人行
	 */
	public void sendBusinessListDetailInfo() {

		String nowDate = businessListCoreService.getNowDate();
		String finishdate = businessListCoreService.getFinishDate(2);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(2);
			finishdate = businessListCoreService.getFinishDate(2);
		}
		List<BusinessListDetail> bldList = businessListDetailService.getBusinessListDetail(finishdate, nowDate);
		if (null != bldList && bldList.size() > 0) {
			sendBusinessListDetailInfo(bldList, nowDate);
		} else {
			logger.info("业务信息券别明细表没有要同步的数据...**" + PokaDateUtil.getNow() + "**");
		}
	}

	public void sendBusinessListDetailInfo(List<BusinessListDetail> dataList, String nowDate) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendBusinessListDetailInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**" + PokaDateUtil.getNow() + "**");
			}
			if (result) {
				logger.info("(业务信息券别明细)BusinessListDetail 数据同步成功... **" + PokaDateUtil.getNow() + "**");
				logger.info("总计： " + dataList.size() + "条.");
				businessListCoreService.updateFinishDate(2, nowDate);
			} else {
				logger.info("(业务信息券别明细)BusinessListDetail 数据同步失败... **" + PokaDateUtil.getNow() + "**");
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
	public void importODSData() {

		String finishdate = businessListCoreService.getFinishDate(3);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(3);
			finishdate = businessListCoreService.getFinishDate(3);
		}
		String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(PokaDateUtil.getNextDay(new Date()));
		List<String> dateList = PokaDateUtil.getImportDate(finishdate, nextDate);
		int numTmp = 0;
		if (null != dateList && dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				String tmpPath = ConstantUtil.filePath + File.separator + dateList.get(i).replace("-", "");
				File file = new File(tmpPath);
				File[] tempList = file.listFiles();
				if (tempList == null) {
					logger.info("没有相应的dat文件或文件路径有误...**" + PokaDateUtil.getNow() + "**");
					return;
				}
				String fileName = "";
				for (int j = 0; j < tempList.length; j++) {
					if (tempList[j].isFile()) {
						if (tempList[j].getName().endsWith(".dat")) {
							fileName = tempList[j].getName();
						}
					}
				}
				Integer num = businessListCoreService.importODSData(tmpPath + File.separator + fileName);
				if (num > 0) {
					logger.info("导入dat数据文件成功...**" + PokaDateUtil.getNow() + "**");
					logger.info("dat文件记录数(" + num + ")条...[" + dateList.get(i) + "][文件名]---->" + fileName + "<----");
					if (numTmp == 0) {
						businessListCoreService.updateFinishDate(3, null);
						numTmp++;
					}
				} else {
					logger.info("导入dat数据文件失败...**" + PokaDateUtil.getNow() + "**");
				}
			}
		}

	}

}
