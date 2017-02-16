package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BundleInfo;
import com.poka.app.anno.enity.BundleInfoPK;

@Service
public class BundleInfoService extends BaseService<BundleInfo, BundleInfoPK> {

	/**
	 * * 获取ATM加钞信息
	 * 
	 * @param operDate
	 * @param nowDate
	 * @return
	 */
	public List<BundleInfo> getBundleInfoList(String operDate) {
		String hql = " FROM BundleInfo WHERE createTime >'" + operDate + "'";
		Query query = createQuery(hql);
		return (List<BundleInfo>) query.list();
	}
}
