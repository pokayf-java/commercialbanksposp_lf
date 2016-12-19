package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BusinessListCore;

@Service
public class BusinessListCoreService extends BaseService<BusinessListCore, String> {

	/**
	 * 查询核心业务数据前一天或指定日期
	 * 
	 * @return
	 */
	public List<BusinessListCore> getBusinessListCore(String operDate) {
		String sql = " SELECT * FROM BusinessListCore WHERE inserDate >='" + operDate + "' and inserDate <= now() ";
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		query.addEntity(BusinessListCore.class);
		return (List<BusinessListCore>) query.list();
	}

	/**
	 * 查询上次同步的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public String getFinishDate(int type) {
		String sql = " SELECT finishdate FROM LANBIAOLOGS WHERE type = " + type;
		SQLQuery query = this.getBaseDao().getSession().createSQLQuery(sql);
		return query.toString();
	}

	/**
	 * insert初始的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int insertFinishDate(int type) {
		String sql = " INSERT INTO LANBIAOLOGS (finishdate,type) VALUES (date_sub(now(),interval 1 day)," + type + ")";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * update同步的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int updateFinishDate(int type) {
		String sql = " UPDATE LANBIAOLOGS SET finishdate = now() WHERE type =" + type;
		return this.getBaseDao().getSession().createQuery(sql).executeUpdate();
	}

}
