package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BusinessListCore;

@Service
public class BusinessListCoreService extends BaseService<BusinessListCore, String> {

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

	/**
	 * * 查询核心业务数据
	 * 
	 * @param operDate
	 * @param nowDate
	 * @return
	 */
	public List<BusinessListCore> getBusinessListCore(String operDate, String nowDate) {
		String hql = " FROM BusinessListCore WHERE insertDate >='" + operDate + "' and insertDate < '" + nowDate + "'";
		Query query = createQuery(hql);
		return (List<BusinessListCore>) query.list();
	}

	/**
	 * 查询上次同步的完成时间
	 * 
	 * @param type
	 *            1：核心系统存取款业务信息，2：业务信息券别明细,3:导入ODS传过来的dat文件,4:P_ImportCoreData,
	 *            5:执行存储过程P_LBTJ,6:网点配钞信息,7:钞箱加钞信息，8：ATM加钞
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
		String sql = null;
		if (type >= 3) {
			sql = " INSERT INTO LANBIAOLOGS (FINISHDATE,TYPE) VALUES (date_sub(curdate(),interval 2 day)," + type + ")";
		} else {
			sql = " INSERT INTO LANBIAOLOGS (FINISHDATE,TYPE) VALUES (date_sub(now(),interval 2 day)," + type + ")";
		}

		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * update同步的完成时间
	 * 
	 * @param type
	 * @return
	 */
	public int updateFinishDate(int type, String nowDate) {
		String sql = null;
		if (type >= 3) {
			sql = " UPDATE LANBIAOLOGS SET FINISHDATE = '" + nowDate + "'WHERE TYPE =" + type;
		} else {
			sql = " UPDATE LANBIAOLOGS SET FINISHDATE ='" + nowDate + "' WHERE TYPE =" + type;
		}
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 导入ODS传过来的dat文件
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public int importODSData(String filePath) {
		String sql = "LOAD DATA INFILE '" + filePath
				+ "' REPLACE INTO TABLE ODS CHARACTER SET UTF8 FIELDS TERMINATED BY 0x03 LINES TERMINATED BY '\n'";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 删除旧的库存ods数据
	 * 
	 * @return
	 */
	public int deleteOldODSData(String date) {
		String sql = " DELETE FROM ODS WHERE ODS_SRC_DT <= '" + date + "'";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 执行将ods表转换为businesscore_list表数据
	 */
	public int doImportCoreDatPro(String date) {
		String sql = " {CALL P_ImportCoreData('" + date + "')}";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 执行生成businesscore_detail表数据
	 * 
	 * @param date
	 * @return
	 */
	public int doLBTJPro(String date) {
		String sql = " {CALL P_LBTJ('" + date + "')}";
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 删除临时表
	 * 
	 * @param tmpTable
	 * @return
	 */
	public int doDeleteTmpTable(String tmpTable) {
		String sql = "DROP TEMPORARY TABLE IF EXISTS " + tmpTable;
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 创建临时表
	 * 
	 * @param tmpTable
	 * @return
	 */
	public int doCreateTmpTable(String tmpTable) {
		String sql = "CREATE TEMPORARY TABLE IF EXISTS " + tmpTable;
		return this.getBaseDao().getSession().createSQLQuery(sql).executeUpdate();
	}

}
