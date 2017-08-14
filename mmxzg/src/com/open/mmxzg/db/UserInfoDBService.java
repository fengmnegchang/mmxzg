///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-10下午1:52:07
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.db;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import android.content.Context;
//import android.os.Build;
//
//import com.open.android.utils.NetWorkUtils;
//import com.open.mmxzg.utils.DBMySqlUtils;
//import com.open.mmxzg.utils.DeviceUtils;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-10下午1:52:07
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class UserInfoDBService {
//	
//	public static void userinfo(Context context){
//		try {
//			//插入设备信息
//			String imei = DeviceUtils.getDeviceId(context);
//			String nettype = NetWorkUtils.getAPNType(context);
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
//			Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
//			String time = formatter.format(curDate);  
//			
//			String query = "select * from userinfo where imei='"+imei+"'";
//			String insert = "insert into userinfo(imei,manufacturer,nettype,time,model) values('"+imei+"','"+Build.MANUFACTURER+"','"+nettype+"','"+time+"','"+Build.MODEL+"')";
//			String update = "update userinfo set manufacturer='"+Build.MANUFACTURER+"',nettype='"+nettype+"',time='"+time+"',model='"+Build.MODEL+"' where imei='"+imei+"'";  
//			if(DBMySqlUtils.query(query)){
//				DBMySqlUtils.update(update);
//			}else{
//				DBMySqlUtils.insert(new String[]{insert});
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//}
