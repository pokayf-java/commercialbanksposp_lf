package com.poka.app.cb.ws.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AppointmentBussiness;
import com.poka.app.cb.ws.ICBPospSW;
import com.poka.app.vo.AppointmenResult;

@WebService(endpointInterface = "com.poka.app.cb.ws.ICBPospSW") 
public class CBPospSW implements ICBPospSW {

	private AppointmentBussiness appointmentBussiness;
	
	@Autowired
	public void setAppointmentBussiness(AppointmentBussiness appointmentBussiness) {
		this.appointmentBussiness = appointmentBussiness;
	}
	/*
	 * 预约请求结果处理
	 * @see com.poka.app.cb.ws.ICBPospSW#handleAppointmen(com.poka.app.vo.AppointmenResult)
	 */
	@Override
	public boolean handleAppointmen(AppointmenResult appointment) {
		return appointmentBussiness.handleAppointment(appointment);
	}

}
