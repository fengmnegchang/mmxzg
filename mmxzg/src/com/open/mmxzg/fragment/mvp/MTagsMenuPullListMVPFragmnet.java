/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:04:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.mvp;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.android.fragment.common.CommonPullToRefreashListMVPFragment;
import com.open.mmxzg.PXingWebViewActivity;
import com.open.mmxzg.activity.mvp.MArticlePullGridMVPActivity;
import com.open.mmxzg.adapter.m.MSlideMenuAdapter;
import com.open.mmxzg.bean.m.MSlideMenuBean;
import com.open.mmxzg.json.m.MSlideMenuJson;
import com.open.mmxzg.presenter.MTagsMenuPullListPresenter;
import com.open.mmxzg.view.MTagsMenuPullListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:04:38
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MTagsMenuPullListMVPFragmnet extends CommonPullToRefreashListMVPFragment<MSlideMenuBean, MSlideMenuJson, MTagsMenuPullListPresenter>
implements MTagsMenuPullListView<MSlideMenuJson, MTagsMenuPullListPresenter>{
	public MSlideMenuAdapter mMSlideMenuAdapter;

	public static MTagsMenuPullListMVPFragmnet newInstance(boolean isVisibleToUser) {
		MTagsMenuPullListMVPFragmnet fragment = new MTagsMenuPullListMVPFragmnet();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
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
		mMSlideMenuAdapter = new MSlideMenuAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mMSlideMenuAdapter);
		mPullToRefreshListView.setMode(Mode.PULL_FROM_START);
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
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(result==null){
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
		mMSlideMenuAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
	}
	
	 /* (non-Javadoc)
	 * @see com.open.android.fragment.common.CommonPullToRefreshListFragment#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		super.onItemClick(parent, view, position, id);
		if(id!=-1){
			MSlideMenuBean bean = list.get((int)id);
			if(bean!=null){
				if("动态图片".equals(bean.getTitle()) || "性爱技巧".equals(bean.getTitle())){
					PXingWebViewActivity.startPXingWebViewActivity(getActivity(), bean.getHref());
				}else{
//					MArticlePullListActivity.startMArticlePullListActivity(getActivity(), bean.getHref());
					MArticlePullGridMVPActivity.startMArticlePullGridMVPActivity(getActivity(), bean.getHref());
				}
				
			}
		}
		
	}

}
