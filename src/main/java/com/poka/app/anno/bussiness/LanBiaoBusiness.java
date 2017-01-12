package com.poka.app.anno.bussiness;

import java.io.File;
import java.io.IOException;
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

		String nowDateTime = businessListCoreService.getNowDate(1);
		String finishdate = getFinishDate(1);
		List<BusinessListCore> blcList = businessListCoreService.getBusinessListCore(finishdate, nowDateTime);
		if (null != blcList && blcList.size() > 0) {
			sendBusinessListCoreInfo(blcList, nowDateTime);
		} else {
			logger.info("核心业务数据表没有要同步的数据...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
	}

	public void sendBusinessListCoreInfo(List<BusinessListCore> dataList, String nowDateTime) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendBusinessListCoreInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
			}
			if (result) {
				logger.info("(核心业务数据)BusinessListCore 数据同步成功... **[执行时间：" + PokaDateUtil.getNow() + "]**");
				logger.info("总计：[ " + dataList.size() + "]条.");
				businessListCoreService.updateFinishDate(1, nowDateTime);
			} else {
				logger.info("(核心业务数据)BusinessListCore 数据同步失败... **[执行时间：" + PokaDateUtil.getNow() + "]**");
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

		String nowDateTime = businessListCoreService.getNowDate(1);
		String finishdate = getFinishDate(2);
		List<BusinessListDetail> bldList = businessListDetailService.getBusinessListDetail(finishdate, nowDateTime);
		if (null != bldList && bldList.size() > 0) {
			sendBusinessListDetailInfo(bldList, nowDateTime);
		} else {
			logger.info("业务信息券别明细表没有要同步的数据...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
	}

	public void sendBusinessListDetailInfo(List<BusinessListDetail> dataList, String nowDateTime) {

		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendBusinessListDetailInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
			}
			if (result) {
				logger.info("(业务信息券别明细)BusinessListDetail 数据同步成功... **[执行时间：" + PokaDateUtil.getNow() + "]**");
				logger.info("总计： [" + dataList.size() + "]条.");
				businessListCoreService.updateFinishDate(2, nowDateTime);
			} else {
				logger.info("(业务信息券别明细)BusinessListDetail 数据同步失败... **[执行时间：" + PokaDateUtil.getNow() + "]**");
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
		String nowDate = businessListCoreService.getNowDate(2);
		String finishdate = getFinishDate(3);
		List<String> dateList = PokaDateUtil.getImportDate(finishdate, PokaDateUtil.getLastDayStr(nowDate));
		if (null != dateList && dateList.size() > 0) {
			Integer deleteSize = businessListCoreService.deleteOldODSData(PokaDateUtil.getLastDayStr(nowDate));
			logger.info("删除ODS表历史记录...**共计：[" + deleteSize + "]条.**");
			for (int i = 0; i < dateList.size(); i++) {
				String tmpPath = ConstantUtil.filePath + File.separator + dateList.get(i).replace("-", "");
				File file = new File(tmpPath);
				File[] tempList = file.listFiles();
				if (tempList == null) {
					logger.info("[" + dateList.get(i) + "]没有相应的dat文件或文件路径有误...**[执行时间：" + PokaDateUtil.getNow() + "]**");
					continue;
				}
				String fileName = "";
				String successFileName = "";
				for (int j = 0; j < tempList.length; j++) {
					if (tempList[j].isFile()) {
						if (tempList[j].getName().endsWith(".dat")) {
							fileName = tempList[j].getName();
						}
						if (tempList[j].getName().endsWith(".log")) {
							successFileName = tempList[j].getName();
						}
					}
				}
				if (!successFileName.equals("")) {
					logger.info("[请勿重复导入...][文件名]---->" + fileName + "<----");
					continue;
				}
				Integer num = businessListCoreService.importODSData(tmpPath + File.separator + fileName);
				if (num > 0) {
					logger.info("导入dat数据文件成功...**" + PokaDateUtil.getNow() + "**");
					logger.info("dat文件记录数[" + num + "]条...[" + dateList.get(i) + "][文件名]---->" + fileName + "<----");
					businessListCoreService.updateFinishDate(3, dateList.get(i));
					createSucFile(tmpPath);
				} else {
					logger.info("导入dat数据文件失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				}
			}
		} else {
			logger.info("无dat文件需要导入...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
	}

	/**
	 * 执行存储过程P_LBTJ
	 */
	public void doLBTJPro() {
		doProduce(5);
	}
	
	/**
	 * 执行存储过程P_ImportCoreData
	 */
	public void doImportCoreDatPro() {
		doProduce(4);
	}
	
	/**
	 * 执行存储过程通用方法
	 *@param type: 4、P_ImportCoreData,5、P_LBTJ.
	 */
	public void doProduce(int type) {
		String nowDate = businessListCoreService.getNowDate(2);
		String finishdate = getFinishDate(type);
		List<String> dateList = PokaDateUtil.getImportDate(finishdate, PokaDateUtil.getLastDayStr(nowDate));
		if (null != dateList && dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				if (type == 4) {
					businessListCoreService.doImportCoreDatPro(dateList.get(i));
					businessListCoreService.updateFinishDate(4, dateList.get(i));
					logger.info("[日期:"+dateList.get(i)+"]-->P_ImportCoreData<--存储过程执行完成...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				} else if (type == 5) {
					businessListCoreService.doLBTJPro(dateList.get(i));
					businessListCoreService.updateFinishDate(5, dateList.get(i));
					logger.info("[日期:"+dateList.get(i)+"]-->P_LBTJ<--存储过程执行完成...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				} else {
					
				}
			}
			
		} else {
			logger.info("P_ImportCoreData存储过程无执行日期参数...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
	}
	
	/**
	 * 获取同步完成日期
	 * @param type
	 * @return
	 */
	public String getFinishDate(int type) {
		String finishdate = businessListCoreService.getFinishDate(type);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(type);
			finishdate = businessListCoreService.getFinishDate(type);
		}
		return finishdate;
	}
	/**
	 * 生成success.log文件
	 * @param path
	 */
	public void createSucFile(String path) {
		File f = new File(path);
		if (!f.exists()) {
			// f.mkdirs();
			return;
		}
		String fileName = "success.log";
		File file = new File(f, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
