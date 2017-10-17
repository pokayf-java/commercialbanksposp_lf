package com.poka.app.anno.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poka.app.anno.base.dao.impl.TAgencyCheckDao;
import com.poka.app.anno.enity.TAgencyCheck;
import com.poka.app.anno.enity.TAgencyCheckPK;

/**
 * 处理兴业银行代理的业务类
 * @author enma.ai
 * 2017年9月19日
 */

@Service
public class TagencyCheckService extends BaseService<TAgencyCheck, TAgencyCheckPK>{
	
	private TAgencyCheckDao tAgencyCheckDao;
	
	@Autowired
	public void settAgencyCheckDao(TAgencyCheckDao tAgencyCheckDao) {
		this.tAgencyCheckDao = tAgencyCheckDao;
	}

	/**
	 * 查找未同步的代理取款信息
	 * 2017年9月19日
	 * @author Enma.ai
	 * @return List<TAgencyCheck>
	 * @return
	 */
	public List<TAgencyCheck> getUnsendCheck() {
		String hql = "from TAgencyCheck t where t.syncStates = 0";
		return this.tAgencyCheckDao.find(hql);
	}

	public void updateCheckState(List<TAgencyCheck> checks) {
		List<TAgencyCheck> list = checks;
		for (TAgencyCheck tAgencyCheck : list) {
			tAgencyCheck.setSyncStates(1);
			tAgencyCheck.setSyncDate(new Date());
		}
		this.tAgencyCheckDao.saveLosts(list);
	}

	
}
