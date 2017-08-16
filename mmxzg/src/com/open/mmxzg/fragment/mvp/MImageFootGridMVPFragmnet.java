/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:27:05
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
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.fragment.common.CommonPullToRefreshGridMVPFragment;
import com.open.mmxzg.activity.m.MImagePullListActivity;
import com.open.mmxzg.adapter.m.MArticleGridAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.MImageFootGridPresenter;
import com.open.mmxzg.view.MImageFootGridView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:27:05
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MImageFootGridMVPFragmnet extends CommonPullToRefreshGridMVPFragment<MArticleBean, MArticleJson, MImageFootGridPresenter> 
implements MImageFootGridView<MArticleJson, MImageFootGridPresenter> {
private MArticleGridAdapter mMArticleGridAdapter;
	
	public static MImageFootGridMVPFragmnet newInstance(boolean isVisibleToUser) {
		MImageFootGridMVPFragmnet fragment = new MImageFootGridMVPFragmnet();
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
		mMArticleGridAdapter = new MArticleGridAdapter(getActivity(), list);
		mPullToRefreshHeadGridView.setAdapter(mMArticleGridAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.BOTH);
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
		if(result==null){
			return;
		}
		Log.i(TAG, "getMode ===" + mPullToRefreshHeadGridView.getCurrentMode());
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMArticleGridAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshHeadGridView.onRefreshComplete();
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.fragment.common.CommonPullToRefreshListFragment#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
//		super.onItemClick(parent, view, position, id);
		if(id!=-1 && list.get((int)id)!=null){
			//保存收藏
			MArticleBean bean = list.get((int) id);
			String href = "";
			if (bean.getHref().contains("_")) {
				href = bean.getHref().split("_")[0] + ".html";
			} else {
				href = bean.getHref();
			}
			OpenDBBean openbean = new OpenDBBean();
			openbean.setImgsrc(bean.getDataimg());
			openbean.setUrl(href);
			openbean.setType(1);
			openbean.setTitle(bean.getAlt());
			openbean.setTypename(1+"");
			OpenDBService.insert(getActivity(), openbean);

			MImagePullListActivity.startMImagePullListActivity(getActivity(),
					bean.getHref());
		}
	}

}
