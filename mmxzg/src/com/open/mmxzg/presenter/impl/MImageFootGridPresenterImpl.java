/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:20:42
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
import com.open.android.mvp.base.CommonAsyncTaskPresenter;
import com.open.android.utils.NetWorkUtils;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.jsoup.m.MArticleJsoupService;
import com.open.mmxzg.presenter.MImageFootGridPresenter;
import com.open.mmxzg.view.MImageFootGridView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:20:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MImageFootGridPresenterImpl extends CommonAsyncTaskPresenter<MArticleJson> implements MImageFootGridPresenter {
	private MImageFootGridView mMImageFootGridView;
	
	public MImageFootGridPresenterImpl(Context mContext,MImageFootGridView view,String url){
		mMImageFootGridView = view;
		mMImageFootGridView.setPresenter(this);
		this.mContext = mContext;
		this.url = url;
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MArticleJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMImageFootGridView.onCallback(result);
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
//		String href = url;
//		if(pageNo>1){
//			if(url.endsWith("/")){
//				href = url+"page/"+pageNo;
//			}else{
//				href = url+"/page/"+pageNo;
//			}
//		}
		String typename = "MArticleJsoupService-parseMMXZGMImageFootList-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
			mMArticleJson.setList(MArticleJsoupService.parseMMXZGMImageFootList(url, pageNo));
			try {
				//数据存储
				Gson gson = new Gson();
				OpenDBBean  openbean = new OpenDBBean();
	    	    openbean.setUrl(url);
	    	    openbean.setTypename(typename);
			    openbean.setTitle(gson.toJson(mMArticleJson));
			    OpenDBService.insert(mContext, openbean);
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
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.presenter.CommonPresenter#doAsync()
	 */
	@Override
	public void doAsync() {
		// TODO Auto-generated method stub
		try {
			doAsync(this, this, this);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.open.android.mvp.presenter.CommonPresenter#setPageNo(int)
	 */
	@Override
	public void setPageNo(int pageNo) {
		// TODO Auto-generated method stub
		this.pageNo = pageNo;

	}

	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.BasePresenter#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
