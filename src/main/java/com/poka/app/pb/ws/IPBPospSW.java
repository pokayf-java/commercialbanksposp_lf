package com.poka.app.pb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.BundleInfo;
import com.poka.app.anno.enity.BusinessListCore;
import com.poka.app.anno.enity.BusinessListDetail;
import com.poka.app.anno.enity.MonBoxAddMon;
import com.poka.app.anno.enity.MoneyOut;
import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.anno.enity.ShuaKaJiLu;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@WebService
public interface IPBPospSW {

	/* 预约取款 */
	@WebMethod(operationName = "makeAppointmen")
	@WebResult(name = "result")
	public boolean makeAppointmen(@WebParam(name = "appointment") AppointmentVo appointment);

	/* 预约交款 */
	@WebMethod(operationName = "makePayment")
	@WebResult(name = "result")
	public boolean makePayment(@WebParam(name = "payment") PaymentVo payment);

	/* 查询申请 */
	@WebMethod(operationName = "makeQryApply")
	@WebResult(name = "result")
	public boolean makeQryApply(@WebParam(name = "qryApply") QryApply qryApply);

	/* 机具同步 */
	@WebMethod(operationName = "getPerInfoData")
	@WebResult(name = "result")
	public boolean getPerInfoData(@WebParam(name = "listData") String listData);

	/* 网点同步 */
	@WebMethod(operationName = "getBanchInfoData")
	@WebResult(name = "result")
	public boolean getBanchInfoData(@WebParam(name = "listData") String listData);

	/* 网点日结 */
	@WebMethod(operationName = "sendNetCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRep(
			@WebParam(name = "netCheckDailyRepList") List<NetCheckDailyRep> netCheckDailyRepList);

	/* 网点流水 */
	@WebMethod(operationName = "sendNetCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepList(
			@WebParam(name = "netCheckDailyRepListFlow") List<NetCheckDailyRepList> netCheckDailyRepListFlow);

	/* 银行日结 */
	@WebMethod(operationName = "sendBankCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRep(
			@WebParam(name = "bankCheckDailyRepList") List<BankCheckDailyRep> bankCheckDailyRepList);

	/* 银行流水 */
	@WebMethod(operationName = "sendBankCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepList(
			@WebParam(name = "bankCheckDailyRepListFlow") List<BankCheckDailyRepList> bankCheckDailyRepListFlow);

	/* 横向调拨 */
	@WebMethod(operationName = "sendBagInfo")
	@WebResult(name = "result")
	public boolean sendBagInfo(@WebParam(name = "bagInfoList") List<BagInfo> bagInfoList);

	/* 核心业务流水 */
	@WebMethod(operationName = "sendBusinessListCoreInfo")
	@WebResult(name = "result")
	public boolean sendBusinessListCoreInfo(
			@WebParam(name = "businessListCoreList") List<BusinessListCore> businessListCoreList);

	/* 核心业务信息券别明细 */
	@WebMethod(operationName = "sendBusinessListDetailInfo")
	@WebResult(name = "result")
	public boolean sendBusinessListDetailInfo(
			@WebParam(name = "businessListDetailList") List<BusinessListDetail> businessListDetailList);

	/* 刷卡记录 */
	@WebMethod(operationName = "sendShuaKaJiLuInfo")
	@WebResult(name = "result")
	public boolean sendShuaKaJiLuInfo(@WebParam(name = "sendShuaKaJiLuList") List<ShuaKaJiLu> sendShuaKaJiLuList);
	
	/* 网点配钞 */
	@WebMethod(operationName = "sendMoneyOutInfo")
	@WebResult(name = "result")
	public boolean sendMoneyOutInfo(@WebParam(name = "sendMoneyOutInfoList") List<MoneyOut> sendMoneyOutInfoList);
	
	/* 钞箱加钞 */
	@WebMethod(operationName = "sendMonBoxAddMonInfo")
	@WebResult(name = "result")
	public boolean sendMonBoxAddMonInfo(@WebParam(name = "sendMonBoxAddMonInfoList") List<MonBoxAddMon> sendMonBoxAddMonInfoList);
	
	/* ATM加钞  */
	@WebMethod(operationName = "sendBundleInfo")
	@WebResult(name = "result")
	public boolean sendBundleInfo(@WebParam(name = "sendBundleInfoList") List<BundleInfo> sendBundleInfoList);

}
