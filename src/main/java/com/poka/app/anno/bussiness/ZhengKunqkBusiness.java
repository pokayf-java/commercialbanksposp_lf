package com.poka.app.anno.bussiness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.WithDrawInfoService;
import com.poka.app.anno.enity.WithDrawInfo;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.ConstantUtil;
import com.poka.app.util.CxfUtil;
import com.poka.app.util.FileUtil;
import com.poka.app.util.PokaDateUtil;

/**
 * 整捆取款文件解析
 * 
 * @author Administrator
 *
 */
@Component
public class ZhengKunqkBusiness {

	Logger logger = Logger.getLogger(ZhengKunqkBusiness.class);
	private WithDrawInfoService withDrawInfoService;
	private CxfUtil cxfUtil;

	@Autowired
	@Qualifier("withDrawInfoService")
	public void setWithDrawInfoService(WithDrawInfoService withDrawInfoService) {
		this.withDrawInfoService = withDrawInfoService;
	}

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	/**
	 * 解析整捆取款ZT文件
	 */
	public void analyzeZtFile() {
		File f = new File(ConstantUtil.ztFilePath.trim());
		List<String> list = new ArrayList<String>();
		list = FileUtil.getFileList(f);
		if (null != list && list.size() > 0) {
			for (String file : list) {
				List<String> accountNoList = new ArrayList<String>();
				String fileName = new File(file).getName();
				String strName[] = fileName.split("_");
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(new File(file)));
					String tempString = null;
					String tmpDate = null;
					int recordsNum = 0;
					int repeatNum = 0;
					List<WithDrawInfo> withDrawInfoList = new ArrayList<WithDrawInfo>();
					while ((tempString = br.readLine()) != null) {
						String bundleId = tempString.substring(0, 24).trim();
						String accountNo = tempString.substring(51, 76).trim();
						if (!accountNoList.contains(bundleId)) {
							WithDrawInfo withDrawInfo = new WithDrawInfo();
							withDrawInfo.setScanId(tempString.substring(0, 24).trim());
							withDrawInfo.setOperTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(tempString.substring(24, 43).trim()));
							withDrawInfo.setOperatorId(tempString.substring(43, 51).trim());
							withDrawInfo.setAccountNo(accountNo);
							withDrawInfo.setFileName(fileName);
							withDrawInfo.setBankNo(strName[0]);
							withDrawInfo.setAgencyNo(strName[1]);
							withDrawInfo.setBoxId(strName[2]);
							withDrawInfo.setFlage("0");
							withDrawInfoService.save(withDrawInfo);
							tmpDate = tempString.substring(24, 34).trim().replace("-", "");
							recordsNum++;
							accountNoList.add(bundleId);
							withDrawInfoList.add(withDrawInfo);
						} else {
							repeatNum++;
						}
					}
					br.close();
					
					//同步整捆取款信息至人行
					sendWithDrawInfoInfo(withDrawInfoList);
					
					logger.info("ZT文件[" + fileName + "]处理成功...合计[" + recordsNum + "]条...**[执行时间："
							+ PokaDateUtil.getNow() + "]**");
					logger.info("ZT文件[" + fileName + "]去重捆条码...合计[" + repeatNum + "]条...**[执行时间："
							+ PokaDateUtil.getNow() + "]**");

					String tmpBasePath = ConstantUtil.ztFileBakPath + File.separator + tmpDate;
					File ztFileBak = new File(tmpBasePath);
					if (!ztFileBak.exists() && !ztFileBak.isDirectory()) {
						ztFileBak.mkdirs();
					}
					File afile = new File(file);
					if (afile.renameTo(new File(tmpBasePath + File.separator + afile.getName()))) {
						logger.info("ZT文件[" + fileName + "]备份成功...");
					} else {
						logger.info("ZT文件[" + fileName + "]备份失败...");
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.info("ZT文件[" + fileName + "]处理失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				} catch (ParseException pe) {
					pe.printStackTrace();
					logger.info("ZT文件[" + fileName + "]处理失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				}
			}
		}
	}
	
	/**
	 * 整捆取款信息
	 * @param dataList
	 */
	public void sendWithDrawInfoInfo(List<WithDrawInfo> dataList) {
		IPBPospSW service = cxfUtil.getCxfClient(IPBPospSW.class, cxfUtil.getUrl());
		cxfUtil.recieveTimeOutWrapper(service);
		boolean result = Boolean.FALSE;
		if (null != dataList && dataList.size() > 0) {
			try {
				result = service.sendWithDrawInfoInfo(dataList);
			} catch (Exception ex) {
				logger.info("连接服务器失败...**[执行时间：" + PokaDateUtil.getNow() + "]**");
			}
			if (result) {
				logger.info("(整捆取款数据)WithDrawInfo 数据同步成功... **[执行时间：" + PokaDateUtil.getNow() + "]**");
				logger.info("总计：[" + dataList.size() + "]条.");
			} else {
				logger.info("(整捆取款数据)WithDrawInfo 数据同步失败... **[执行时间：" + PokaDateUtil.getNow() + "]**");
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param 删除zt文件
	 * 
	 */
	public void deleteZtFile() {
		List<String> dateList = PokaDateUtil.getMoreDate(31);
		if (null != dateList && dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				String tmpPath = ConstantUtil.ztFileBakPath + File.separator + dateList.get(i).replace("-", "");
				if (FileUtil.delFolder(tmpPath)) {
					logger.info("日期：[" + dateList.get(i) + "] zt文件成功删除...**[执行时间：" + PokaDateUtil.getNow() + "]**");
				}
			}
		} else {
			logger.info("无符合条件的zt文件可以删除...**[执行时间：" + PokaDateUtil.getNow() + "]**");
		}
	}
	
	
}
