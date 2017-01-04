package com.poka.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		// java.text.SimpleDateFormat sim = new
		// java.text.SimpleDateFormat("yyyy-MM-dd");
		// java.util.Calendar rightNow = java.util.Calendar.getInstance();
		// List<String> dateList = new ArrayList<String>();
		// Date date = rightNow.getTime();
		// Calendar c = Calendar.getInstance();
		// c.setTime(date);
		//
		// for(int i=0;i<15;i++){
		// c.add(Calendar.DAY_OF_YEAR, -1);
		// Date monday = c.getTime();
		// System.out.println(sim.format(monday));
		// dateList.add(sim.format(monday));
		// }
		// System.out.println(dateList.size());
		String s1 = "2016-02-01";
		String s2 = "2016-04-04";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = sdf.parse(s1);
		Date end = sdf.parse(s2);
		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		double day = between / (24 * 3600);
		for (int i = 1; i <= day; i++) {
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s1));
			cd.add(Calendar.DATE, i);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			System.out.println(sdf.format(cd.getTime()));
		}

		// c.add(Calendar.DAY_OF_YEAR, -1);
		// Date monday = c.getTime();
		// System.out.println(sim.format(monday));
		// c.add(Calendar.DAY_OF_YEAR, -1);
		// Date tuesday = c.getTime();
		// System.out.println(sim.format(c.getTime()));
		// System.out.println(dateList.size());
		// c.add(Calendar.DAY_OF_YEAR, -1);
		// System.out.println(sim.format(c.getTime()));
		// c.add(Calendar.DAY_OF_YEAR, -2);
		// System.out.println(sim.format(c.getTime()));
		// c.add(Calendar.DAY_OF_YEAR, -3);
		// System.out.println(sim.format(c.getTime()));
		// c.add(Calendar.DAY_OF_YEAR, -4);
		// System.out.println(sim.format(c.getTime()));
		// c.add(Calendar.DAY_OF_YEAR, -5);
		// System.out.println(sim.format(c.getTime()));
		// c.add(Calendar.DAY_OF_YEAR, -6);
		// System.out.println(sim.format(c.getTime()));
		// c.add(Calendar.DAY_OF_YEAR, -7);
		// System.out.println(sim.format(c.getTime()));

		// Date today_plus1 = c.getTime();
		// System.out.println("Tomorrow:/t" + sim.format(today_plus1));
		// c.add(Calendar.DAY_OF_YEAR, -1);
		// Date today_plus2 = c.getTime();
		// System.out.println("Today+2:/t" + today_plus2.toLocaleString());
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// Date today_plus3 = c.getTime();
		// System.out.println("Today+3:/t" + today_plus3.toLocaleString());
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// Date today_plus4 = c.getTime();
		// System.out.println("Today+4:/t" + today_plus4.toLocaleString());
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// Date today_plus5 = c.getTime();
		// System.out.println("Today+5:/t" + today_plus5.toLocaleString());
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// Date today_plus6 = c.getTime();
		// System.out.println("Today+6:/t" + today_plus6.toLocaleString());
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// Date today_plus7 = c.getTime();
		// System.out.println("Today+7:/t" + today_plus7.toLocaleString());

		// java.util.Calendar rightNow = java.util.Calendar.getInstance();
		//
		// //得到当前时间，+3天
		// rightNow.add(java.util.Calendar.DAY_OF_MONTH, 3);
		// //如果是后退几天，就写 -天数 例如：
		// rightNow.add(java.util.Calendar.DAY_OF_MONTH, -3);
		// //进行时间转换
		// String date = sim.format(rightNow.getTime());
		// System.out.println(rightNow.add(java.util.Calendar.DAY_OF_MONTH, 3));
	}
}
