package com.poka.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author lb 功能启用工具类
 *
 */
public class ArgsConfigUtil {

	private Properties p;

	public ArgsConfigUtil() {
		this("argsConfig.properties");
	}

	public ArgsConfigUtil(String path) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
		this.p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取预约取款启用标识
	 * @return
	 */
	public String getAppointmentFlag() {
		return p.getProperty("appointmentFlag");
	}

	/**
	 * 获取预约交款启用标识
	 * @return
	 */
	public String getPaymentFlag() {
		return p.getProperty("paymentFlag");
	}

	/**
	 * 获取查询申请启用标识
	 * @return
	 */
	public String getQryApplyFlag() {
		return p.getProperty("qryApplyFlag");
	}

	/**
	 * 获取机具、网点信息同步更新功能启用标识
	 * @return
	 */
	public String getPerInfoAndBranchInfoFlag() {
		return p.getProperty("perInfoAndBranchInfoFlag");
	}

	/**
	 * 获取网点、银行日报同步更新功能启用标识
	 * @return
	 */
	public String getBankAndNetRepFlag() {
		return p.getProperty("bankAndNetRepFlag");
	}
	
	/**
	 * 可以冠字号村镇银行同步启用标识
	 * @return
	 */
	public String getMonRuleCZYHFlag() {
		return p.getProperty("monRuleCZYHFlag");
	}
	
	/**
	 * 横向调拨信息同步启用标识
	 */
	public String getBagInfoFlag(){
		return p.getProperty("bagInfoFlag");
	}
	
	/**
	 * 蓝标信息同步启用标识
	 */
	public String getLanBiaoFlag(){
		return p.getProperty("lanBiaoFlag");
	}
	
	/**
	 * dat文件存放路径
	 */
	public String getFilePath(){
		return p.getProperty("filePath");
	}
}
