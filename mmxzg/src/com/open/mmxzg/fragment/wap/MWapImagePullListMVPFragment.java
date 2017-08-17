/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:39:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.wap;

import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.mmxzg.R;
import com.open.mmxzg.activity.wap.MWapImagePagerAdapterFragmentActivity;
import com.open.mmxzg.adapter.m.MImageListAdapter;
import com.open.mmxzg.fragment.mvp.MImagePullListMVPFragment;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.impl.MWapImageFootGridPresenterImpl;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:39:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapImagePullListMVPFragment extends MImagePullListMVPFragment {
	public static MWapImagePullListMVPFragment newInstance(String url, boolean isVisibleToUser) {
		MWapImagePullListMVPFragment fragment = new MWapImagePullListMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
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
		MWapImageFootExpendGridMVPFragmnet ffragment = MWapImageFootExpendGridMVPFragmnet.newInstance(true);
		getChildFragmentManager().beginTransaction().replace(R.id.id_m_head, ffragment).commit();
		new MWapImageFootGridPresenterImpl(getActivity(), ffragment, url);

		mMImageListAdapter = new MImageListAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mMImageListAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
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
				MWapImagePagerAdapterFragmentActivity.startMWapImagePagerAdapterFragmentActivity(getActivity(), list.get(0).getHref(), mMArticleJson);
			}
		}
	}
}
