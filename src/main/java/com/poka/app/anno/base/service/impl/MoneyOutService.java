package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.MoneyOut;
import com.poka.app.anno.enity.MoneyOutPK;

@Service
public class MoneyOutService extends BaseService<MoneyOut, MoneyOutPK> {

	/**
	 * * 获取网点配钞信息
	 * 
	 * @param operDate
	 * @param nowDate
	 * @return
	 */
	public List<MoneyOut> getMoneyOutList(String operDate) {
		String hql = " FROM MoneyOut WHERE operTime >'" + operDate + "' or toCounterDate > '" + operDate
				+ "' or netReceiveDate >'" + operDate + "'";
		Query query = createQuery(hql);
		return (List<MoneyOut>) query.list();
	}

	/**
	 * 查询上次同步的完成时间
	 * 
	 * @param type
	 *            1：核心系统存取款业务信息，2：业务信息券别明细,3:导入ODS传过来的dat文件,4:P_ImportCoreData,
	 *            5:执行存储过程P_LBTJ,6:网点配钞信息,7:钞箱加钞信息，8：ATM加钞信息
	 * @return
	 */
	public String getFinishDate(int type) {
		String sql = " SELECT FINISHDATE FROM LANBIAOLOGS WHERE TYPE = " + type;
		String finishdate = (String) this.getBaseDao().getSession().createSQLQuery(sql).uniqueResult();
		if (null != finishdate) {
			return finishdate;
		} else {
			return "";
		}
	}

	/**
	 * insert初始的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int insertFinishDate(int type) {
		
		String sql = " INSERT INTO LANBIAOLOGS (FINISHDATE,TYPE) VALUES (date_sub(now(),interval 2 day)," + type + ")";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * update同步的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int updateFinishDate(int type, String nowDate) {
		String sql = " UPDATE LANBIAOLOGS SET FINISHDATE ='" + nowDate + "' WHERE TYPE =" + type;
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 获取数据库日期
	 * 
	 * @param type
	 * @return
	 */
	public String getNowDate(Integer type) {
		String sql = "";
		if (type == 1) {
			sql = " SELECT now() as nowdatetime";
		} else if (type == 2) {
			sql = " SELECT curdate() as nowdate";
		}
		return this.getBaseDao().getSession().createSQLQuery(sql).uniqueResult().toString();
	}
}
