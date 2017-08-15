/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午3:35:57
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
import com.open.android.fragment.common.CommonPullToRefreshGridMVPFragment;
import com.open.mmxzg.adapter.m.MArticleGridAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.MArticlePullGridPresenter;
import com.open.mmxzg.view.MArticlePullGridView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午3:35:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MArticlePullGridMVPFragment2 extends CommonPullToRefreshGridMVPFragment<MArticleBean,MArticleJson,MArticlePullGridPresenter>
implements MArticlePullGridView<MArticleJson,MArticlePullGridPresenter>{
	public MArticleGridAdapter mMArticleGridAdapter;

	public static MArticlePullGridMVPFragment2 newInstance(boolean isVisibleToUser) {
		MArticlePullGridMVPFragment2 fragment = new MArticlePullGridMVPFragment2();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}
	 
	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		mMArticleGridAdapter = new MArticleGridAdapter(getActivity(), list);
		mPullToRefreshHeadGridView.setAdapter(mMArticleGridAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.BOTH);
	}
 
	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}
	
	  
	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#onCallback(com.open.mmxzg.json.m.MArticleJson)
	 */
	@Override
	public void onCallback(MArticleJson result) {
		if(result==null){
			return;
		}
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Log.i(TAG, "getMode ===" + mPullToRefreshHeadGridView.getCurrentMode());
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
			mPresenter.setPageNo(1);
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMArticleGridAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshHeadGridView.onRefreshComplete();
		
	}
 

}
