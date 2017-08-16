/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:02:06
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
import com.open.mmxzg.presenter.MImagePagerAdapterPresenter;
import com.open.mmxzg.view.MImagePagerAdapterView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:02:06
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MImagePagerAdapterPresenterImpl extends CommonAsyncTaskPresenter<MArticleJson> implements MImagePagerAdapterPresenter {
	private MImagePagerAdapterView mMImagePagerAdapterView;
	private int position;
	private String href;
	
	public MImagePagerAdapterPresenterImpl(Context mContext,MImagePagerAdapterView view,String url){
		this.mContext = mContext;
		this.url = url;
		this.mMImagePagerAdapterView = view;
		this.mMImagePagerAdapterView.setPresenter(this);
	}
	
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MArticleJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMImagePagerAdapterView.onCallback(result);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.umei.activity.BaseFragmentActivity#call()
	 */
	@Override
	public MArticleJson call() throws Exception {
		// TODO Auto-generated method stub
		MArticleJson mMArticleJson =null;
		String typename = "MArticleJsoupService-parseMMXZGMImagePager-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
//			mMArticleJson = MArticleJsoupService.parsePXImagePagerList(url,position);
			if(pageNo==1){
				mMArticleJson = MArticleJsoupService.parseImagePagerList(url,position);
			}else{
				mMArticleJson = new MArticleJson();
//				mMArticleJson.setList(MArticleJsoupService.parseImageList(list.get(position).getHref(),position));
				mMArticleJson.setList(MArticleJsoupService.parseImageList(getUrl(),position));
				mMArticleJson.setCurrentPosition(position);
			}
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
		doAsync(this, this, this);
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

	/* (non-Javadoc)
	 * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#setPosition(int)
	 */
	@Override
	public void setPosition(int position) {
		// TODO Auto-generated method stub
		this.position = position;
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		this.href = url;
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.presenter.MImagePagerAdapterPresenter#getUrl()
	 */
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return href;
	}

}
