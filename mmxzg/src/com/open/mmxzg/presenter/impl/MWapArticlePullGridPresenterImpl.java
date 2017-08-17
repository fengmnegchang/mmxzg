/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午9:41:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.presenter.impl;

import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.utils.NetWorkUtils;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.jsoup.m.MArticleJsoupService;
import com.open.mmxzg.view.MArticlePullGridView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午9:41:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapArticlePullGridPresenterImpl extends MArticlePullGridPresenterImpl {

	public MWapArticlePullGridPresenterImpl(Context context, MArticlePullGridView view, String url) {
		super(context, view, url);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#call()
	 */
	@Override
	public MArticleJson call() throws Exception {
		// TODO Auto-generated method stub
		MArticleJson mMArticleJson = new MArticleJson();
		String href = url;
		if(pageNo>1){
			//http://www.mmxzg.com/e/wap/list.php?classid=1&style=0&bclassid=
			//http://www.mmxzg.com/e/wap/list.php?page=1&classid=1&style=0&bclassid=0&totalnum=990
			//http://www.mmxzg.com/e/wap/
			href = url+"0&page="+pageNo;
		}
		String typename = "MArticleJsoupService-parseMmxzgWapList-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
			mMArticleJson.setList(MArticleJsoupService.parseMmxzgWapList(href, pageNo));
			try {
				//数据存储
				Gson gson = new Gson();
				OpenDBBean  openbean = new OpenDBBean();
	    	    openbean.setUrl(url);
	    	    openbean.setTypename(typename);
			    openbean.setTitle(gson.toJson(mMArticleJson));
			    OpenDBService.insert(mContext, openbean);
			    
			    //jdbc
//			    DBMySqlUtils.query();
//			    DBMySqlUtils.query(openbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
//				ImgContainerDBService.imgContainer(mMArticleJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			List<OpenDBBean> beanlist =  OpenDBService.queryListType(mContext, url,typename);
			String result = beanlist.get(0).getTitle();
			Gson gson = new Gson();
			mMArticleJson = gson.fromJson(result, MArticleJson.class);
		}
		return mMArticleJson;
	}

}
