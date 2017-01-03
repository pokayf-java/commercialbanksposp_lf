package com.poka.app.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PokaDateUtil {
	
	
	/**
	 * 获取当前日期的前sum天
	 * @param sum
	 * @return
	 */
	public static List<String> getMoreDate(int sum){
		
		java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		List<String> dateList = new ArrayList<String>();
		Date date = rightNow.getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		for(int i=0;i<sum;i++){
			c.add(Calendar.DAY_OF_YEAR, -1);
			Date monday = c.getTime();
			dateList.add(sim.format(monday));
		}
		return dateList;
		
	}
	
	
	public static String getNow(){
		 return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

}
