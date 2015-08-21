package commercialbanksposp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.poka.app.anno.base.dao.impl.BaseDao;
import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.anno.base.service.impl.ReserveDetailService;
import com.poka.app.anno.base.service.impl.ReserveMainService;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.anno.enity.ReserveDetail;
import com.poka.app.anno.enity.ReserveMain;
import com.poka.app.enumtype.OrderType;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.util.CxfUtil;
import com.poka.app.vo.AppointmentVo;

public class ServiceTest {
/*
	static ClassPathXmlApplicationContext ctx = null;

	//@BeforeClass
	public static void before() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testOrderInfo() {

		OrderInfoService orderInfoService = (OrderInfoService) ctx
				.getBean("orderInfoService");
//		System.out.println(orderInfoService.getClass());
//		System.out.println(orderInfoService.getBaseDao().getClass());
//		System.out.println(orderInfoService.getAll().size());
//		ReserveMainService reserveMainService = (ReserveMainService) ctx
//				.getBean("reserveMainService");
//		System.out.println(reserveMainService.getBaseDao().getClass());
//
//		ReserveDetailService reserveDetailService = (ReserveDetailService) ctx
//				.getBean("reserveDetailService");
//		System.out.println(reserveDetailService.getBaseDao().getClass());
//		List<OrderInfo> orders = orderInfoService.getUnsendOrder(OrderType.APPOINTMENT);
//		
//		System.out.println("orders:"+orders.size());
//		
//		AppointmentVo vo = orderInfoService.getAppointmentVo("1");
//		System.out.println(vo.getReserveMain()==null?null:vo.getReserveMain().toString());
//		
//		for(ReserveDetail v:vo.getReserveDetails()){
//			System.out.println(v == null?null:v.toString());
		AppointmentVo vo = orderInfoService.getAppointmentVo("1");
		
		
	}
	@Test
	public void testGetUrl(){
		System.out.println(new CxfUtil().getUrl());
	//	System.out.println(new CxfUtil().getResource("service.properties"));
		//System.out.println(new CxfUtil().getResource("/service.properties"));
	}
	
    @Test
    public void testCallservice(){
    	CxfUtil cxf = new CxfUtil();
    	String url = cxf.getUrl();
    	IPBPospSW service = cxf.getCxfClient(IPBPospSW.class, url);
    	cxf.recieveTimeOutWrapper(service);
    	AppointmentVo vo = new AppointmentVo();
    	ReserveMain main = new ReserveMain();
    	main.setBankId("0204");
    	main.setBankName("people bank");
    	main.setCreateDate(new Date());
    	main.setOrderId("1");
    	main.setRsvNo("1");
    	
    	List<ReserveDetail> reserveDetails = new ArrayList<>();
    	ReserveDetail de = new ReserveDetail();
    	de.setRsvNo("1");
    	reserveDetails.add(de);
    	
    	vo.setReserveDetails(reserveDetails);
    	vo.setReserveMain(main);
    	boolean result  = service.makeAppointmen(vo);
    	System.out.println("result:"+result);
    }
	//@AfterClass
	public static void after() {
		ctx.close();
		ctx.destroy();
	}*/
}
