/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:37:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.open.android.fragment.common.CommonPullToRefreashListMVPFragment;
import com.open.mmxzg.R;
import com.open.mmxzg.activity.m.MImagePagerAdapterFragmentActivity;
import com.open.mmxzg.adapter.m.MImageListAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.MImagePullListPresenter;
import com.open.mmxzg.presenter.impl.MImageFootGridPresenterImpl;
import com.open.mmxzg.view.MImagePullListView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:37:13
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MImagePullListMVPFragment extends CommonPullToRefreashListMVPFragment<MArticleBean, MArticleJson, MImagePullListPresenter> implements
		MImagePullListView<MArticleJson, MImagePullListPresenter> {
	public MImageListAdapter mMImageListAdapter;
	public View headview, footview;

	public static MImagePullListMVPFragment newInstance(String url, boolean isVisibleToUser) {
		MImagePullListMVPFragment fragment = new MImagePullListMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_pulllistview, container, false);
		mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		headview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_m_head, null);
		footview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_expend_footview, null);
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
		// mPullToRefreshListView.getRefreshableView().addFooterView(footview);
		mPullToRefreshListView.getRefreshableView().addHeaderView(headview);
		// MImageHeadFragmnet hfragment = MImageHeadFragmnet.newInstance(url,
		// true);
		// getChildFragmentManager().beginTransaction().replace(R.id.id_expend_foot,
		// hfragment).commit();
		//
		//
		MImageFootExpendGridMVPFragmnet ffragment = MImageFootExpendGridMVPFragmnet.newInstance(true);
		getChildFragmentManager().beginTransaction().replace(R.id.id_m_head, ffragment).commit();
		new MImageFootGridPresenterImpl(getActivity(), ffragment, url);

		mMImageListAdapter = new MImageListAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mMImageListAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#onCallback
	 * (com.open.android.json.CommonJson)
	 */
	@Override
	public void onCallback(MArticleJson result) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (result == null) {
			return;
		}
		Log.i(TAG, "getMode ===" + mPullToRefreshListView.getCurrentMode());
		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMImageListAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#onItemClick
	 * (android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		super.onItemClick(parent, view, position, id);
		if (id != -1 && list.get((int) id) != null) {
			MArticleJson mMArticleJson = new MArticleJson();
			mMArticleJson.setList(list);
			mMArticleJson.setCurrentPosition((int) id);
			if (list.get(0).getHref().contains(".mp4")) {
				// MiStatInterface.recordCountEvent("视频", "播放视频");
				// MVideoViewActivity.startMVideoViewActivity(getActivity(),
				// list.get(0).getHref());
			} else {
				MImagePagerAdapterFragmentActivity.startMImagePagerAdapterFragmentActivity(getActivity(), list.get(0).getHref(), mMArticleJson);
			}
		}
	}

}
