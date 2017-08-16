/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:33:09
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
import com.open.mmxzg.presenter.MImagePullListPresenter;
import com.open.mmxzg.view.MImagePullListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:33:09
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MImagePullListPresenterImpl extends CommonAsyncTaskPresenter<MArticleJson> implements MImagePullListPresenter {
	private MImagePullListView mMImagePullListView;
	
	public MImagePullListPresenterImpl(Context mContext,MImagePullListView view,String url){
		this.mContext = mContext;
		this.url = url;
		this.mMImagePullListView = view;
		this.mMImagePullListView.setPresenter(this);
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MArticleJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMImagePullListView.onCallback(result);
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#call()
	 */
	@Override
	public MArticleJson call() throws Exception {
		// TODO Auto-generated method stub
		MArticleJson mMArticleJson;
		String typename = "MArticleJsoupService-parseMMXZGImagePagerList-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
			 mMArticleJson = MArticleJsoupService.parseMMXZGImagePagerList(url,pageNo);
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
