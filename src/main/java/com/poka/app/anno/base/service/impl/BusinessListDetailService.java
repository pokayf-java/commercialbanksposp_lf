package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
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
		String hql = " FROM BusinessListDetail WHERE insertDate >='" + operDate + "' and insertDate <= now() ";
		Query query = createQuery(hql);
		return (List<BusinessListDetail>) query.list();
	}

}
