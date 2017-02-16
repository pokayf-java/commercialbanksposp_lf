package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.MonBoxAddMon;
import com.poka.app.anno.enity.MonBoxAddMonPK;

@Service
public class MonBoxAddMonService extends BaseService<MonBoxAddMon, MonBoxAddMonPK> {

	/**
	 * * 获取钞箱加钞信息
	 * 
	 * @param operDate
	 * @param nowDate
	 * @return
	 */
	public List<MonBoxAddMon> getMonBoxAddMonList(String operDate) {
		String hql = " FROM MonBoxAddMon WHERE insertDate >'" + operDate + "'";
		Query query = createQuery(hql);
		return (List<MonBoxAddMon>) query.list();
	}

}
