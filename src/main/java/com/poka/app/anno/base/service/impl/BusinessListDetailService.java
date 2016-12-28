package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BusinessListDetail;

@Service
public class BusinessListDetailService extends BaseService<BusinessListDetail, String> {

	/**
	 * 查询业务信息券别明细
	 * 
	 * @return
	 */
	public List<BusinessListDetail> getBusinessListDetail(String operDate) {
		String sql = " SELECT * FROM BusinessListDetail WHERE inserDate >='" + operDate + "' and inserDate <= now() ";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.addEntity(BusinessListDetail.class);
		return (List<BusinessListDetail>) query.list();
	}

}
