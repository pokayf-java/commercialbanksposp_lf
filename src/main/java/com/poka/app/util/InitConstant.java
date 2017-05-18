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
			ConstantUtil.lanBiaoFlag = argsConfigUtil.getLanBiaoFlag();
			ConstantUtil.datFilePath = argsConfigUtil.getDatFilePath();
			ConstantUtil.ztFilePath = argsConfigUtil.getZtFilePath();
			ConstantUtil.ztFileFlag = argsConfigUtil.getZtFileFlag();
			ConstantUtil.ztFileBakPath = argsConfigUtil.getZtFileBakPath();
			ConstantUtil.crProduceFlag = argsConfigUtil.getCrProduceFlag();
			ConstantUtil.datImportFlag = argsConfigUtil.getDatImportFlag();
			ConstantUtil.delDatFileFlag = argsConfigUtil.getDelDatFileFlag();
			ConstantUtil.cdiProduceFlag = argsConfigUtil.getCdiProduceFlag();
			ConstantUtil.tjProduceFlag = argsConfigUtil.getTjProduceFlag();
			ConstantUtil.netPeiChaoFlag = argsConfigUtil.getNetPeiChaoFlag();
			ConstantUtil.atmJiaChaoFlag = argsConfigUtil.getAtmJiaChaoFlag();
			ConstantUtil.chaoXJiaChaoFlag = argsConfigUtil.getChaoXJiaChaoFlag();
			
		}
	}
}
