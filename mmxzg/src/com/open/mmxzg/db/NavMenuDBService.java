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

import com.open.mmxzg.bean.m.MSlideMenuBean;
import com.open.mmxzg.json.m.MSlideMenuJson;
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
public class NavMenuDBService {
	
	public static void navmenu(MSlideMenuJson mMSlideMenuJson){
		try {
			for(MSlideMenuBean bean:mMSlideMenuJson.getList()){
				String query = "select * from navmenu where href='"+bean.getHref()+"'";
				String insert = "insert into navmenu(href,alt) values('"+bean.getHref()+"','"+bean.getTitle()+"')";
				String update = "update navmenu set alt='"+bean.getTitle() +"' where href='"+bean.getHref()+"'";  
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
