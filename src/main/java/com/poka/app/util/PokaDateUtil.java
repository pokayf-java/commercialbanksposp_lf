package com.poka.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PokaDateUtil {

	/**
	 * 获取当前日期的前sum天
	 * 
	 * @param sum
	 * @return
	 */
	public static List<String> getMoreDate(int sum) {

		java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		List<String> dateList = new ArrayList<String>();
		Date date = rightNow.getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		for (int i = 0; i < sum; i++) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			Date monday = c.getTime();
			dateList.add(sim.format(monday));
		}
		return dateList;

	}

	public static String getNow() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String getLastDayStr(String date) {
		try {
			Date nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			nowDate = calendar.getTime();
			return new SimpleDateFormat("yyyy-MM-dd").format(nowDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "Exception";
		}

	}

	public static List<String> getImportDate(String beginDate, String stopDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(stopDate);
			List<String> dateList = new ArrayList<String>();
			double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			double day = between / (24 * 3600);
			for (int i = 1; i <= day; i++) {
				Calendar cd = Calendar.getInstance();
				cd.setTime(sdf.parse(beginDate));
				cd.add(Calendar.DATE, i);// 增加一天
				dateList.add(sdf.format(cd.getTime()));
			}
			return dateList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {
//		System.out.println(PokaDateUtil.getImportDate("2016-01-01", PokaDateUtil.getLastDayStr("2017-01-10")).size());
		System.out.println(getMoreDate(30));

	}
}
