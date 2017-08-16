/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:42:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.mvp;


import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.mmxzg.R;
import com.open.mmxzg.activity.app.MCollectionGridFragmentActivity;
import com.open.mmxzg.activity.app.MHistoryListGridFragmentActivity;
import com.open.mmxzg.adapter.m.MSlideMenuAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:42:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MAppLeftMenuPullListMVPFragmnet extends MLeftMenuPullListMVPFragment {
	public View headview;
	public static MAppLeftMenuPullListMVPFragmnet newInstance(boolean isVisibleToUser) {
		MAppLeftMenuPullListMVPFragmnet fragment = new MAppLeftMenuPullListMVPFragmnet();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_pulllistview, container, false);
		mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_m_app_menu_head, null);
		return view;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mPullToRefreshListView.getRefreshableView().addHeaderView(headview);
		mMSlideMenuAdapter = new MSlideMenuAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mMSlideMenuAdapter);
		mPullToRefreshListView.setMode(Mode.PULL_FROM_START);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.mm.fragment.m.MLeftMenuPullListFragmnet#onItemClick(android.
	 * widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// super.onItemClick(parent, view, position, id);
		if (id != -1 && list.get((int) id) != null) {
			String title = list.get((int) id).getTitle();
			if (title.equals("我的收藏")) {
				MCollectionGridFragmentActivity.startMCollectionGridFragmentActivity(getActivity(), title);
			} else if (title.equals("浏览历史")) {
				MHistoryListGridFragmentActivity.startMHistoryListGridFragmentActivity(getActivity(), title);
			} else if (title.equals("关于我们")) {

			}
		}
	}
}
