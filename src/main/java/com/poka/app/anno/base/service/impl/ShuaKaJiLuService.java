package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.ShuaKaJiLu;

@Service
public class ShuaKaJiLuService extends BaseService<ShuaKaJiLu, String> {

	/**
	 * 刷卡记录
	 * 
	 * @return
	 */
	public List<ShuaKaJiLu> getShuaKaJiLu(String operDate, String nowDate) {
		String hql = " FROM ShuaKaJiLu WHERE insertDate >='" + operDate + "' and insertDate <'" + nowDate + "'";
		Query query = createQuery(hql);
		return (List<ShuaKaJiLu>) query.list();
	}

}
