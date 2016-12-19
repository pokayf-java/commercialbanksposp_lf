package com.poka.app.util;

import com.poka.app.util.ArgsConfigUtil;
import com.poka.app.util.ConstantUtil;
/**
 * 初始化常量
 * @author Administrator
 *
 */
public class InitConstant {
	private static ArgsConfigUtil argsConfigUtil = null;
	static {
		if (argsConfigUtil == null) {
			argsConfigUtil = new ArgsConfigUtil();
			ConstantUtil.appointmentFlag = argsConfigUtil.getAppointmentFlag();
			ConstantUtil.paymentFlag = argsConfigUtil.getPaymentFlag();
			ConstantUtil.qryApplyFlag = argsConfigUtil.getQryApplyFlag();
			ConstantUtil.bankAndNetRepFlag = argsConfigUtil.getBankAndNetRepFlag();
			ConstantUtil.perInfoAndBranchInfoFlag = argsConfigUtil.getPerInfoAndBranchInfoFlag();
			ConstantUtil.monRuleCZYHFlag = argsConfigUtil.getMonRuleCZYHFlag();
			ConstantUtil.bagInfoFlag = argsConfigUtil.getBagInfoFlag();
			ConstantUtil.sendFileFlag = argsConfigUtil.getSendFileFlag();
		}
	}
}
