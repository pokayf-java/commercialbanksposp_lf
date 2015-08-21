package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AppointmentBussiness;
import com.poka.app.anno.bussiness.PaymentBussiness;
import com.poka.app.anno.bussiness.QryApplyBussiness;
import com.poka.app.anno.enity.QryApply;


public class JobQuartz  {
	
	AppointmentBussiness appointmentBussiness;
	PaymentBussiness paymentBussiness;
	QryApplyBussiness qryApplyBussiness;
	
	@Autowired
	public void setPaymentBussiness(PaymentBussiness paymentBussiness) {
		this.paymentBussiness = paymentBussiness;
	}
	
	@Autowired
	public void setQryApplyBussiness(QryApplyBussiness qryApplyBussiness) {
		this.qryApplyBussiness = qryApplyBussiness;
	}

	@Autowired
	public void setAppointmentBussiness(AppointmentBussiness appointmentBussiness) {
		this.appointmentBussiness = appointmentBussiness;
	}

	public void work() {
		appointmentBussiness.makeAppointment();
		paymentBussiness.makePayment();
		qryApplyBussiness.makeQryApply();
	}

}
