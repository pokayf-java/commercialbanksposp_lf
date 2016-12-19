package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BusinessListCoreService;
import com.poka.app.anno.enity.BusinessListCore;
import com.poka.app.util.CxfUtil;

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

	private CxfUtil cxfUtil;

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
	 * 商行核心业务数据同步至人行
	 */
	public void sendBusinessListCoreInfo() {

		String finishdate = businessListCoreService.getFinishDate(1);
		if (null == finishdate || "".equals(finishdate)) {
			businessListCoreService.insertFinishDate(1);
			finishdate = businessListCoreService.getFinishDate(1);
		} 
		List<BusinessListCore> blcList = businessListCoreService.getBusinessListCore(finishdate);
		if(null !=blcList && blcList.size()>0){
			
		}
	}
	
}
