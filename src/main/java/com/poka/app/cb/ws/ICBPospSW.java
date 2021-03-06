package com.poka.app.cb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepList;
import com.poka.app.anno.enity.MonRule;
import com.poka.app.anno.enity.MoneyDataInfo;
import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepList;
import com.poka.app.anno.enity.SendFile;
import com.poka.app.vo.AppointmenResult;

@WebService
public interface ICBPospSW {

	/*预约取款*/
	@WebMethod(operationName = "handleAppointmen")
	@WebResult(name = "result")
	public boolean handleAppointmen(@WebParam(name = "appointment") AppointmenResult appointment);
	
	/*黑名单规则*/
	@WebMethod(operationName = "sendMonRuleData")
	@WebResult(name = "result")
	public boolean sendMonRuleData(@WebParam(name = "monRuleList") List<MonRule> monRuleList);
	
	/*黑名单规则(CZYH)*/
	@WebMethod(operationName = "sendMonRuleDataForCZYH")
	@WebResult(name = "result")
	public boolean sendMonRuleDataForCZYH(@WebParam(name = "monRuleList") List<MonRule> monRuleList);

	/*机具信息*/
	@WebMethod(operationName = "getPerInfoData")
	@WebResult(name = "result")
	public boolean getPerInfoData(@WebParam(name = "listData") String listData);

	/*网点信息*/
	@WebMethod(operationName = "getBanchInfoData")
	@WebResult(name = "result")
	public boolean getBanchInfoData(@WebParam(name = "listData") String listData);

	/*网点日结*/
	@WebMethod(operationName = "sendNetCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepBak(
			@WebParam(name = "netCheckDailyRepList") List<NetCheckDailyRep> netCheckDailyRepList);

	/*银行日结*/
	@WebMethod(operationName = "sendBankCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepBak(
			@WebParam(name = "bankCheckDailyRepList") List<BankCheckDailyRep> bankCheckDailyRepList);
	
	/*网点流水*/
	@WebMethod(operationName = "sendNetCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepList(
			@WebParam(name = "netCheckDailyRepListFlow") List<NetCheckDailyRepList> netCheckDailyRepListFlow);
	
	/*银行流水*/
	@WebMethod(operationName = "sendBankCheckDailyRepList")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepList(
			@WebParam(name = "bankCheckDailyRepListFlow") List<BankCheckDailyRepList> bankCheckDailyRepListFlow);

	/*下发FSN文件记录*/
	@WebMethod(operationName = "sendFileData")
	@WebResult(name = "result")
	public boolean sendFileData(@WebParam(name = "sendFileList") List<SendFile> sendFileList);
	
	
	@WebMethod(operationName = "selectGZHData")
	@WebResult(name = "result")
	public List<MoneyDataInfo> selectGZHData(@WebParam(name = "mon")String mon);
	
	/*取款信息下发商行*/
	@WebMethod(operationName = "sendBagInfoData")
	@WebResult(name = "result")
	public boolean sendBagInfoData(@WebParam(name = "bagInfoList") List<BagInfo> bagInfoList);
}
