/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-10下午1:55:33
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Build;

import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.utils.DBMySqlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-10下午1:55:33
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImgContainerDBService {
	
	public static void imgContainer(MArticleJson mMArticleJson){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
			Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
			String time = formatter.format(curDate);  
			for(MArticleBean bean:mMArticleJson.getList()){
				String query = "select * from imgcontainer where href='"+bean.getHref()+"' and src='"+bean.getSrc()+"'";
				String insert = "insert into imgcontainer(href,src,alt,imgcount,tag,taghref,time) values('"+bean.getHref()+"','"+bean.getSrc()+"','"+bean.getAlt()+"','"+bean.getImgcount()+"','"+bean.getTag()+"','"+bean.getTaghref()+"','"+time+"')";
				String update = "update imgcontainer set alt='"+bean.getAlt()+"',imgcount='"+bean.getImgcount()+"',time='"+time+"',tag='"+bean.getTag()+"',taghref='"+bean.getTaghref()+"' where href='"+bean.getHref()+"' and src='"+bean.getSrc()+"'";  
				if(DBMySqlUtils.query(query)){
					DBMySqlUtils.update(update);
				}else{
					DBMySqlUtils.insert(new String[]{insert});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
