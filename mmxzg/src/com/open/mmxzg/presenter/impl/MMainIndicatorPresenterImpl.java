/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:23:03
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
import com.open.mmxzg.json.m.MSlideMenuJson;
import com.open.mmxzg.jsoup.m.MLeftMenuJsoupService;
import com.open.mmxzg.presenter.MMainIndicatorPresenter;
import com.open.mmxzg.view.MMainIndicatorView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:23:03
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MMainIndicatorPresenterImpl extends CommonAsyncTaskPresenter<MSlideMenuJson> implements MMainIndicatorPresenter {
	private MMainIndicatorView<MSlideMenuJson, MMainIndicatorPresenter> mMMainIndicatorView;
	public MMainIndicatorPresenterImpl(Context mContext,MMainIndicatorView view,String url){
		this.mContext = mContext;
		this.url = url;
		this.mMMainIndicatorView = view;
		this.mMMainIndicatorView.setPresenter(this);
	}


	@Override
	public MSlideMenuJson call() throws Exception {
		// TODO Auto-generated method stub
		MSlideMenuJson mMSlideMenuJson = new MSlideMenuJson();
		String typename = "MLeftMenuJsoupService-parseNavMenuList-"+pageNo;
		if(NetWorkUtils.isNetworkAvailable(mContext)){
			mMSlideMenuJson.setList(MLeftMenuJsoupService.parseNavMenuList(url, pageNo));
			try {
				//数据存储
				Gson gson = new Gson();
				OpenDBBean  openbean = new OpenDBBean();
	    	    openbean.setUrl(url);
	    	    openbean.setTypename(typename);
			    openbean.setTitle(gson.toJson(mMSlideMenuJson));
			    OpenDBService.insert(mContext, openbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			List<OpenDBBean> beanlist =  OpenDBService.queryListType(mContext, url,typename);
			String result = beanlist.get(0).getTitle();
			Gson gson = new Gson();
			mMSlideMenuJson = gson.fromJson(result, MSlideMenuJson.class);
		}
		return mMSlideMenuJson;
	}

	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MSlideMenuJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMMainIndicatorView.onCallback(result);
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

}
