package com.poka.app.cb.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.vo.AppointmenResult;


@WebService
public interface ICBPospSW {
	@WebMethod(operationName = "handleAppointmen")
	@WebResult(name = "result")
	public boolean handleAppointmen(
			@WebParam(name = "appointment") AppointmenResult appointment);
	
//	@WebMethod(operationName = "handPayment")
//	@WebResult(name = "result")
//	public boolean handPayment(@WebParam(name = "payment")PaymentVo payment);
}
