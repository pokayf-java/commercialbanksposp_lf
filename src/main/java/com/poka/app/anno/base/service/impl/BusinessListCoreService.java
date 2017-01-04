package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BusinessListCore;

@Service
public class BusinessListCoreService extends BaseService<BusinessListCore, String> {

	/**
	 * 查询核心业务数据
	 * 
	 * @return
	 */
	public List<BusinessListCore> getBusinessListCore(String operDate) {
		String hql = " FROM BusinessListCore WHERE insertDate >='" + operDate + "' and insertDate <= now() ";
		Query query = createQuery(hql);
		return (List<BusinessListCore>) query.list();
	}

	/**
	 * 查询上次同步的完成时间
	 * 
	 * @param type
	 *            1：核心系统存取款业务信息，2：业务信息券别明细
	 * @return
	 */
	public String getFinishDate(int type) {
		String sql = " SELECT FINISHDATE FROM LANBIAOLOGS WHERE TYPE = " + type;
		String finishdate = (String) this.getBaseDao().getSession().createSQLQuery(sql).uniqueResult();
		if (null != finishdate) {
			return finishdate;
		} else {
			return null;
		}
	}

	/**
	 * insert初始的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int insertFinishDate(int type) {
		String sql = null;
		if (type == 3) {
			sql = " INSERT INTO LANBIAOLOGS (FINISHDATE,TYPE) VALUES (date_sub(curdate(),interval 1 day)," + type + ")";
		} else {
			sql = " INSERT INTO LANBIAOLOGS (FINISHDATE,TYPE) VALUES (date_sub(now(),interval 1 day)," + type + ")";
		}

		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * update同步的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int updateFinishDate(int type) {
		String sql = " UPDATE LANBIAOLOGS SET FINISHDATE = now() WHERE TYPE =" + type;
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 导入ODS传过来的dat文件
	 */
	public int importODSData(String filePath) {
		String sql = "LOAD DATA INFILE '" + filePath
				+ "' REPLACE INTO TABLE ODS CHARACTER SET UTF8 FIELDS TERMINATED BY 0x03 LINES TERMINATED BY '\n'";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

}
