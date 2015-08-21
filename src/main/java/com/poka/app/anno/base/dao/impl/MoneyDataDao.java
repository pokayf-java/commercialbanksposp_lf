package com.poka.app.anno.base.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.poka.app.anno.base.dao.IMoneyDataDao;
import com.poka.app.anno.enity.MoneyDataInfo;

@SuppressWarnings("unchecked")
@Repository
public class MoneyDataDao implements IMoneyDataDao {

	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 用来获取数据库类型 update liangb
	 * 
	 * @return
	 */
	public String getFormatType() {

		String formatType = "DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as operdate,";
		// if(JdbcUtil.DriverType.contains("mysql")){
		// formatType =
		// "DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,";
		// }else if(JdbcUtil.DriverType.contains("oracle")){
		// formatType =
		// "TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,";
		// }else{
		//
		// }
		return formatType;
	}

	@Override
	public List<MoneyDataInfo> findMoneyDataList(String mon) throws Exception {
		if (mon == null) {
			throw new Exception();
		}
		String sql = "select *" + " from" + " (" + " select A.percode percode,"
				// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype ,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype ,"
				// +" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.mon mon,"
				+ " A.monval monval,"
				+ " A.monver monver,"
				+ " A.bundleid bundleid,"
				+ " A.imagepath imagepath,"
				+ " C.ipaddr ipaddr,"
				// +" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.bankno bankno,"
				+ " C.bankname bankname,"
				+ " A.agencyno agencyno,"
				+ " D.branchname branchname"
				+ " from MONEYDATA A"
				// +" left JOIN PERINFO B"
				// +" on A.percode= B.percode"
				+ " left join BANKINFO C"
				+ " on A.bankno= C.bankno"
				+ " left join BRANCHINFO D"
				+ " on A.agencyno= D.agencyno"
				+ " where A.mon=?"

				+ " UNION ALL"

				+ " select A.percode percode,"
				// +" case C.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case C.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// +" TO_CHAR(B.scantime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(B.scantime,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.mon mon,"
				+ " A.monval monval,"
				+ " A.monver monver,"
				+ " A.bundleid bundleid,"
				+ " A.imagepath imagepath,"
				+ " D.ipaddr ipaddr,"
				// +" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.bankno bankno,"
				+ " D.bankname bankname,"
				+ " A.agencyno agencyno,"
				+ " E.branchname branchname"
				+ " from MONEYDATA A"
				+ " inner JOIN BUNDLEINFO B"
				+ " on A.bundleid = B.bundleid"
				// +" inner JOIN PERINFO C"
				// +" on B.percode= C.percode"
				+ " inner join BANKINFO D"
				+ " on A.bankno= D.bankno"
				+ " left join BRANCHINFO E"
				+ " on A.agencyno= E.agencyno"
				+ " where A.mon=?"
				+ " AND (B.FLAG ='0'"
				+ " OR B.FLAG ='1')"

				+ " UNION ALL"

				+ " select A.PERCODE percode,"
				// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// +" TO_CHAR(E.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OPerdate,"
				// +" DATE_FORMAT(E.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.MON mon,"
				+ " A.MONVAL monval,"
				+ " A.monver monver,"
				+ " A.BUNDLEID bundleid,"
				+ " A.imagepath imagepath,"
				+ " C.ipaddr ipaddr,"
				// +" case E.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case E.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.BANKNO bankno,"
				+ " C.BANKNAME bankname,"
				+ " A.AGENCYNO agencyno,"
				+ " D.BRANCHNAME branchname"
				+ " from MONEYDATA A"
				+ " inner join WITHDRAWINFO E"
				+ " on E.SCANID = A.BUNDLEID"
				// +" inner join PERINFO B"
				// +" on A.PERCODE = B.PERCODE"
				+ " inner join BANKINFO C"
				+ " on A.bankno = C.bankno"
				+ " inner join BRANCHINFO D"
				+ " on A.agencyno = D.agencyno"
				+ " where A.mon=?"

				+ " UNION ALL"

				+ " select A.PERCODE percode,"
				// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as ,"
				// +" TO_CHAR(F.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(F.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.MON mon,"
				+ " A.MONVAL monval,"
				+ " A.monver monver,"
				+ " A.BUNDLEID bundleid,"
				+ " A.imagepath imagepath,"
				+ " C.ipaddr ipaddr,"
				// +" case F.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case F.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.BANKNO bankno," + " C.BANKNAME bankname," + " A.AGENCYNO agencyno,"
				+ " D.BRANCHNAME branchname"
				+ " from MONEYDATA A"
				+ " inner join PACKAGEINFO E"
				+ " on A.BUNDLEID =E.BUNDLEID"
				+ " inner join WITHDRAWINFO F"
				+ " on E.PACKAGEID = F.SCANID"
				// +" inner join PERINFO B"
				// +" on A.PERCODE = B.PERCODE"
				+ " inner join BANKINFO C" + " on A.Bankno = C.bankno"
				+ " inner join BRANCHINFO D" + " on A.agencyno = D.agencyno"
				+ " where A.mon=?"

				// +" UNION ALL"
				//
				// +" select A.PERCODE,"
				// //+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// //+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				// +" A.MON,"
				// +" A.MONVAL,"
				// +" A.MONVER,"
				// +" A.BUNDLEID,"
				// +" A.imagepath,"
				// +" C.ipaddr,"
				// //+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				// +" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				// +" B.BANKNO,"
				// +" C.BANKNAME,"
				// +" B.AGENCYNO,"
				// +" D.BRANCHNAME"
				// +" from V_MONBOXOFPACKAGEPUSHATM A"
				// +" inner join PERINFO B"
				// +" on A.PERCODE=B.percode"
				// +" inner join BANKINFO C"
				// +" on B.BANKNO=C.BANKNO"
				// +" inner join BRANCHINFO D"
				// +" on B.AGENCYNO=D.AGENCYNO"
				// +" where A.mon=?"
				//
				// +" UNION ALL"

				// +" select A.PERCODE,"
				// //+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// //+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				// +" A.MON,"
				// +" A.MONVAL,"
				// +" A.MONVER,"
				// +" A.BUNDLEID,"
				// +" A.imagepath,"
				// +" C.ipaddr,"
				// //+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				// +" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				// +" B.BANKNO,"
				// +" C.BANKNAME,"
				// +" B.AGENCYNO,"
				// +" D.BRANCHNAME"
				// +" from V_MONBOXOFBUNDLEPUSHATM A"
				// +" inner join PERINFO B"
				// +" on A.PERCODE=B.percode"
				// +" inner join BANKINFO C"
				// +" on B.BANKNO=C.BANKNO"
				// +" inner join BRANCHINFO D"
				// +" on B.AGENCYNO=D.AGENCYNO"
				// +" where A.mon=?"
				+ " ) T" + " order by OperDate asc";
		// +" limit 0,500 ";
		
//		select A.percode percode,"
//		// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype ,"
//		// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype ,"
//		// +" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
//		// +" DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,"
//		+ getFormatType()
//		+ " A.mon mon,"
//		+ " A.monval monval,"
//		+ " A.monver monver,"
//		+ " A.bundleid bundleid,"
//		+ " A.imagepath imagepath,"
//		+ " C.ipaddr ipaddr,"
//		// +" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
//		+ " case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
//		+ " A.bankno bankno,"
//		+ " C.bankname bankname,"
//		+ " A.agencyno agencyno,"
//		+ " D.branchname branchname"
		

		
		return query(sql,mon);

	}
    public List<MoneyDataInfo> query(String sql,String mon){
    	SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, mon);
		query.setString(1, mon);
		query.setString(2, mon);
		query.setString(3, mon);
		query.addScalar("percode", StandardBasicTypes.STRING);
		query.addScalar("operdate", StandardBasicTypes.STRING);
		query.addScalar("mon", StandardBasicTypes.STRING);
		query.addScalar("monval", StandardBasicTypes.STRING);
		query.addScalar("monver", StandardBasicTypes.STRING);
		query.addScalar("bundleid", StandardBasicTypes.STRING);
		query.addScalar("imagepath", StandardBasicTypes.STRING);
		query.addScalar("ipaddr", StandardBasicTypes.STRING);
		query.addScalar("businesstype", StandardBasicTypes.STRING);
		query.addScalar("bankno", StandardBasicTypes.STRING);
		query.addScalar("bankname", StandardBasicTypes.STRING);
		query.addScalar("agencyno", StandardBasicTypes.STRING);
		query.addScalar("branchname", StandardBasicTypes.STRING);
		List<Object[]> list =  query.list();
		List<MoneyDataInfo> monDataInfos = new ArrayList<MoneyDataInfo>();
		for (Object[] ob : list) {
			MoneyDataInfo mo = new MoneyDataInfo();
			mo.setPercode((String) ob[0]);
			mo.setOperdate((String)ob[1]);
			mo.setMon((String)ob[2]);
			mo.setMonval((String)ob[3]);
			mo.setMonver((String)ob[4]);
			mo.setBundleid((String)ob[5]);
			mo.setImagepath(GetImageStr((String)ob[6]));
			mo.setIpaddr((String)ob[7]);
			mo.setBusinesstype((String)ob[8]);
			mo.setBankno((String)ob[9]);
			mo.setBankname((String)ob[10]);
			mo.setAgencyno((String)ob[11]);
			mo.setBranchname((String)ob[12]);
			monDataInfos.add(mo);
		}
		return monDataInfos;
    }
    
    public  String GetImageStr(String imgFile)  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理    
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
            return "";
        }  
        //对字节数组Base64编码  
        return new String(Base64.encodeBase64(data));//返回Base64编码过的字节数组字符串  
    }  
	@Override
	public List<MoneyDataInfo> findMoneyDataListByLike(String mon)
			throws Exception {
		if (mon == null) {
			throw new Exception();
		}

		String sql = "select *" + " from" + " (" + " select A.percode percode,"
				// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype ,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype ,"
				// +" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.mon mon,"
				+ " A.monval monval,"
				+ " A.monver monver,"
				+ " A.bundleid bundleid,"
				+ " A.imagepath imagepath,"
				+ " C.ipaddr ipaddr,"
				// +" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.bankno bankno,"
				+ " C.bankname bankname,"
				+ " A.agencyno agencyno,"
				+ " D.branchname branchname"
				+ " from MONEYDATA A"
				// +" left JOIN PERINFO B"
				// +" on A.percode= B.percode"
				+ " left join BANKINFO C"
				+ " on A.bankno= C.bankno"
				+ " left join BRANCHINFO D"
				+ " on A.agencyno=D.agencyno"
				+ " where A.mon like ?"

				+ " UNION ALL"

				+ " select A.percode percode,"
				// +" case C.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case C.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// +" TO_CHAR(B.scantime,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(B.scantime,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.mon mon,"
				+ " A.monval monval,"
				+ " A.monver monver,"
				+ " A.bundleid bundleid,"
				+ " A.imagepath imagepath,"
				+ " D.ipaddr ipaddr,"
				// +" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.bankno bankno,"
				+ " D.bankname bankname,"
				+ " A.agencyno agencyno,"
				+ " E.branchname branchname"
				+ " from MONEYDATA A"
				+ " inner JOIN BUNDLEINFO B"
				+ " on A.bundleid= B.bundleid"
				// +" inner JOIN PERINFO C"
				// +" on B.percode= C.percode"
				+ " inner join BANKINFO D"
				+ " on A.bankno= D.bankno"
				+ " left join BRANCHINFO E"
				+ " on A.agencyno=E.agencyno"
				+ " where A.mon like ?"
				+ " AND (B.FLAG ='0'"
				+ " OR B.FLAG ='1')"

				+ " UNION ALL"

				+ " select A.PERCODE percode,"
				// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// +" TO_CHAR(E.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OPerdate,"
				// +" DATE_FORMAT(E.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.MON mon,"
				+ " A.MONVAL monval,"
				+ " A.monver monver,"
				+ " A.BUNDLEID bundleid,"
				+ " A.imagepath imagepath,"
				+ " C.ipaddr ipaddr,"
				// +" case E.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case E.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.BANKNO bankno,"
				+ " C.BANKNAME bankname,"
				+ " A.AGENCYNO agencyno,"
				+ " D.BRANCHNAME branchname"
				+ " from MONEYDATA A"
				+ " inner join WITHDRAWINFO E"
				+ " on E.SCANID = A.BUNDLEID"
				// +" inner join PERINFO B"
				// +" on A.PERCODE = B.PERCODE"
				+ " inner join BANKINFO C"
				+ " on A.bankno = C.bankno"
				+ " inner join BRANCHINFO D"
				+ " on A.agencyno = D.agencyno"
				+ " where A.mon like ?"

				+ " UNION ALL"

				+ " select A.PERCODE percode,"
				// +" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// +" TO_CHAR(F.OPERTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(F.OPERTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				+ getFormatType()
				+ " A.MON mon,"
				+ " A.MONVAL monval,"
				+ " A.monver monver,"
				+ " A.BUNDLEID bundleid,"
				+ " A.imagepath imagepath,"
				+ " C.ipaddr ipaddr,"
				// +" case F.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				+ " case F.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				+ " A.BANKNO bankno," + " C.BANKNAME bankname," + " A.AGENCYNO agecyno,"
				+ " D.BRANCHNAME"
				+ " from MONEYDATA A"
				+ " inner join PACKAGEINFO E"
				+ " on A.BUNDLEID = E.BUNDLEID"
				+ " inner join WITHDRAWINFO F"
				+ " on E.PACKAGEID = F.SCANID"
				// +" inner join PERINFO B"
				// +" on A.PERCODE = B.PERCODE"
				+ " inner join BANKINFO C" + " on A.Bankno = C.bankno"
				+ " inner join BRANCHINFO D" + " on A.agencyno = D.agencyno"
				+ " where A.mon like ?"

				// +" UNION ALL"
				//
				// +" select A.PERCODE,"
				// //+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// //+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				// +" A.MON,"
				// +" A.MONVAL,"
				// +" A.MONVER,"
				// +" A.BUNDLEID,"
				// +" A.imagepath,"
				// +" C.ipaddr,"
				// //+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				// +" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				// +" B.BANKNO,"
				// +" C.BANKNAME,"
				// +" B.AGENCYNO,"
				// +" D.BRANCHNAME"
				// +" from V_MONBOXOFPACKAGEPUSHATM A"
				// +" inner join PERINFO B"
				// +" on A.PERCODE=B.percode"
				// +" inner join BANKINFO C"
				// +" on B.BANKNO=C.BANKNO"
				// +" inner join BRANCHINFO D"
				// +" on B.AGENCYNO=D.AGENCYNO"
				// +" where A.mon like ?"
				//
				// +" UNION ALL"
				//
				// +" select A.PERCODE,"
				// //+" case B.pertype when '00' then '点钞机'  when '01' then 'ATM' when '02' then '清分机' end as pertype,"
				// +" case B.pertype when '00' then '00'  when '01' then '01' when '02' then '02' end as pertype,"
				// //+" TO_CHAR(A.COLTIME,'YYYY-MM-DD HH24:MI:SS') as OperDate,"
				// +" DATE_FORMAT(A.COLTIME,'%Y-%m-%d %H:%i:%S') as OperDate,"
				// +" A.MON,"
				// +" A.MONVAL,"
				// +" A.MONVER,"
				// +" A.BUNDLEID,"
				// +" A.imagepath,"
				// +" C.ipaddr,"
				// //+" case A.businesstype when '0' then '清分'  when '1' then '存款' when '2' then '取款' when '3' then '加钞' when '4' then '回收' end as businesstype,"
				// +" case A.businesstype when '0' then '0'  when '1' then '1' when '2' then '2' when '3' then '3' when '4' then '4' end as businesstype,"
				// +" B.BANKNO,"
				// +" C.BANKNAME,"
				// +" B.AGENCYNO,"
				// +" D.BRANCHNAME"
				// +" from V_MONBOXOFBUNDLEPUSHATM A"
				// +" inner join PERINFO B"
				// +" on A.PERCODE=B.percode"
				// +" inner join BANKINFO C"
				// +" on B.BANKNO=C.BANKNO"
				// +" inner join BRANCHINFO D"
				// +" on B.AGENCYNO=D.AGENCYNO"
				// +" where A.mon like ?"
				+ " ) T" + " order by OperDate asc";
		// +" limit 0,500 ";

		return query(sql,mon);
	}

	@Override
	public List<MoneyDataInfo> findMoneyDataListPage(String page, String rows,
			String dealNo) throws Exception {
		int _page = Integer.parseInt(page);
		int _rows = Integer.parseInt(rows);
		int begin = (_page - 1) * _rows;
		int end = begin + _rows;

		String sql = "select * from("
				// +" select ROWNUM as N,r.* from("
				+ " select r.* from("
				+ " select * from("
				// +" select A.percode,case B.pertype when '00' then '点钞机' when '01' then 'ATM' when '02' then '清分机' end as PERTYPE,"
				+ " select A.percode,case B.pertype when '00' then '00' when '01' then '01' when '02' then '02' end as PERTYPE,"
				// +" TO_CHAR(A.coltime,'YYYY-MM-DD HH24:MI:SS') as OperDate,A.mon,A.monval,A.monver,A.bundleid, b.bankno, c.bankname, b.agencyno, d.branchname"
				+ " DATE_FORMAT(A.coltime,'%Y-%m-%d %H:%i:%S') as OperDate,A.mon,A.monval,A.monver,A.bundleid, B.bankno, C.bankname, B.agencyno, D.branchname"
				+ " from moneydata A inner JOIN perinfo B on A.percode= B.percode"
				+ " inner join bankinfo C on B.bankno= C.bankno inner join BranchInfo D on B.agencyno=D.agencyno"
				+ " union all"
				// +" select B.percode,case C.pertype when '00' then '点钞机' when '01' then 'ATM' when '02' then '清分机' end as PERTYPE,"
				+ " select B.percode,case C.pertype when '00' then '00' when '01' then '01' when '02' then '02' end as PERTYPE,"
				// +" TO_CHAR(B.scantime,'YYYY-MM-DD HH24:MI:SS') as OperDate,A.mon,A.monval,A.monver,A.bundleid, c.bankno, d.bankname, c.agencyno, e.branchname"
				+ " DATE_FORMAT(B.scantime,'%Y-%m-%d %H:%i:%S') as OperDate,A.mon,A.monval,A.monver,A.bundleid, C.bankno, D.bankname, C.agencyno, E.branchname"
				+ " from moneydata A inner JOIN bundleinfo B on A.bundleid= B.bundleid inner JOIN perinfo C on B.percode= C.percode"
				+ " inner join bankinfo D on C.bankno= D.bankno inner join BranchInfo E on C.agencyno=E.agencyno"
				+ " ) T order by OperDate asc " + " ) r )  limit ?,?";

		SQLQuery query = getSession().createSQLQuery(sql);
		query.setInteger(0, begin);
		query.setInteger(1, end);

		query.addEntity(MoneyDataInfo.class);
		return query.list();
	}

	@Override
	public int countResult(String dealNo) throws Exception {
		int total = 0;
		String sql = "select count(*) CO from MONEYDATA where mon=?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("CO", new org.hibernate.type.IntegerType());
		query.setString(0, dealNo);
		total = (Integer) query.uniqueResult();
		return total;
	}

	@Override
	public int getCount() throws Exception {
		String sql = "select count(*) CO" + " from"
				+ " (select A.percode as percode" + " from MONEYDATA A"
				+ " inner JOIN perinfo B" + " on A.percode= B.percode"
				+ " inner join bankinfo C" + " on B.bankno= C.bankno"
				+ " inner join BranchInfo D" + " on B.agencyno=D.agencyno"
				+ " union all" + " select A.bundleid as bundleid"
				+ " from MONEYDATA A" + " inner JOIN bundleinfo B"
				+ " on A.bundleid= B.bundleid" + " inner JOIN perinfo C"
				+ " on B.percode= C.percode" + " inner join bankinfo D"
				+ " on C.bankno= D.bankno" + " inner join BranchInfo E"
				+ " on C.agencyno=E.agencyno" + " )";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("CO", new org.hibernate.type.IntegerType());
		return (Integer) query.uniqueResult();
	}
}
