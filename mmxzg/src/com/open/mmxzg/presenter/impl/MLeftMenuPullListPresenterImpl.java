/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-15下午5:59:47
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.presenter.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.mvp.base.CommonAsyncTaskPresenter;
import com.open.android.utils.NetWorkUtils;
import com.open.mmxzg.json.m.MSlideMenuJson;
import com.open.mmxzg.jsoup.m.MLeftMenuJsoupService;
import com.open.mmxzg.presenter.MLeftMenuPullListPresenter;
import com.open.mmxzg.view.MLeftMenuPullListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-15下午5:59:47
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MLeftMenuPullListPresenterImpl extends   CommonAsyncTaskPresenter<MSlideMenuJson> implements MLeftMenuPullListPresenter{
	private MLeftMenuPullListView mMLeftMenuPullListView;
	private Context mContext;

	public MLeftMenuPullListPresenterImpl(Context context, @NonNull MLeftMenuPullListView view, String url) {
		mMLeftMenuPullListView = checkNotNull(view, "mMLeftMenuPullListView cannot be null!");
		mMLeftMenuPullListView.setPresenter(this);
		this.mContext = context;
		this.url = url;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#call()
	 */
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
			try {
//				NavMenuDBService.navmenu(mMSlideMenuJson);
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#onCallback
	 * (com.open.android.json.CommonJson)
	 */
	@Override
	public void onCallback(MSlideMenuJson result) {
		mMLeftMenuPullListView.onCallback(result);
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
			// TODO Auto-generated catch block
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
