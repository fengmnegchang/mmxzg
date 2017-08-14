///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-10下午1:55:33
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
//import android.os.Build;
//
//import com.open.mmxzg.bean.m.MArticleBean;
//import com.open.mmxzg.bean.m.MSlideMenuBean;
//import com.open.mmxzg.json.m.MArticleJson;
//import com.open.mmxzg.json.m.MSlideMenuJson;
//import com.open.mmxzg.utils.DBMySqlUtils;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-10下午1:55:33
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class TagsContainerDBService {
//	
//	public static void tagsContainer(MSlideMenuJson mMSlideMenuJson){
//		try {
//			for(MSlideMenuBean bean:mMSlideMenuJson.getList()){
//				String query = "select * from tagscontainer where href='"+bean.getHref()+"'";
//				String insert = "insert into tagscontainer(href,alt,title) values('"+bean.getHref()+"','"+bean.getAlt()+"','"+bean.getTitle()+"')";
//				String update = "update tagscontainer set alt='"+bean.getTitle() +"',title='"+bean.getTitle()+"' where href='"+bean.getHref()+"'";  
//				if(DBMySqlUtils.query(query)){
//					DBMySqlUtils.update(update);
//				}else{
//					DBMySqlUtils.insert(new String[]{insert});
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
