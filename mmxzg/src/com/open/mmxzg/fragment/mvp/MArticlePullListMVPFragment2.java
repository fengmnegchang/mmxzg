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
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.fragment.common.CommonPullToRefreashListMVPFragment;
import com.open.mmxzg.activity.m.MImagePullListActivity;
import com.open.mmxzg.adapter.m.MArticleListAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.MArticlePullListPresenter2;
import com.open.mmxzg.view.MArticlePullListView;

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
public class MArticlePullListMVPFragment2 extends CommonPullToRefreashListMVPFragment<MArticleBean,MArticleJson,MArticlePullListPresenter2>
implements MArticlePullListView<MArticleJson,MArticlePullListPresenter2>{
	public MArticleListAdapter mMArticleListAdapter;

	public static MArticlePullListMVPFragment2 newInstance(boolean isVisibleToUser) {
		MArticlePullListMVPFragment2 fragment = new MArticlePullListMVPFragment2();
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
		mMArticleListAdapter = new MArticleListAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mMArticleListAdapter);
		mPullToRefreshListView.setMode(Mode.BOTH);
	}
 
	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
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
		Log.i(TAG, "getMode ===" + mPullToRefreshListView.getCurrentMode());
		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
			list.clear();
			list.addAll(result.getList());
			pageNo = 1;
			mPresenter.setPageNo(1);
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMArticleListAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
		
	}

}
