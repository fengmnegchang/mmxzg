/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:38:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.open.mmxzg.bean.m.MSlideMenuBean;
import com.open.mmxzg.json.m.MSlideMenuJson;
import com.open.mmxzg.view.MLeftMenuPullListView;


/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:38:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MAppLeftMenuPullListPresenterImpl extends MLeftMenuPullListPresenterImpl {

	public MAppLeftMenuPullListPresenterImpl(Context context, MLeftMenuPullListView view, String url) {
		super(context, view, url);
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
		// mMSlideMenuJson.setList(MLeftMenuJsoupService.parseList(url,
		// pageNo));
		List<MSlideMenuBean> list = new ArrayList<MSlideMenuBean>();
		MSlideMenuBean bean = new MSlideMenuBean();
		bean.setTitle("我的收藏");
		bean.setResid(mContext.getResources().getIdentifier("icon_my_favorite", "drawable", mContext.getPackageName()));
		list.add(bean);

		bean = new MSlideMenuBean();
		bean.setTitle("浏览历史");
		bean.setResid(mContext.getResources().getIdentifier("icon_browsing_history", "drawable", mContext.getPackageName()));
		list.add(bean);

		bean = new MSlideMenuBean();
		bean.setTitle("清空缓存");
		bean.setResid(mContext.getResources().getIdentifier("icon_clear_cache", "drawable", mContext.getPackageName()));
		list.add(bean);

		bean = new MSlideMenuBean();
		bean.setTitle("关于我们");
		bean.setResid(mContext.getResources().getIdentifier("icon_about_us", "drawable", mContext.getPackageName()));
		list.add(bean);
		mMSlideMenuJson.setList(list);
		return mMSlideMenuJson;
	}

	

}
