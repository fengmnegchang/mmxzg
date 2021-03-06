/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-7下午3:29:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.m;

import java.util.List;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.fragment.common.CommonPullToRefreshListAppFragment;
import com.open.android.utils.NetWorkUtils;
import com.open.mmxzg.activity.m.MSearchArticlePullListActivity;
import com.open.mmxzg.adapter.m.MRandTagAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.jsoup.m.MArticleJsoupService;
import com.open.mmxzg.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-7下午3:29:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MRandTagListAppFragment extends CommonPullToRefreshListAppFragment<MArticleBean, MArticleJson> {
	public MRandTagAdapter mMRandTagAdapter;
    
	public static MRandTagListAppFragment newInstance(String url, boolean isVisibleToUser) {
		MRandTagListAppFragment fragment = new MRandTagListAppFragment();
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
		url = UrlUtils.PXING_RAND_TAG;
		mMRandTagAdapter = new MRandTagAdapter(getActivity(), list);
		mPullToRefreshListView.setAdapter(mMRandTagAdapter);
		mPullToRefreshListView.setMode(Mode.PULL_FROM_START);
		doAsync(this, this, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshListFragment#call()
	 */
	@Override
	public MArticleJson call() throws Exception {
		// TODO Auto-generated method stub
		MArticleJson mMArticleJson = new MArticleJson();
		String href = url;
		String typename = "MArticleJsoupService-parsePXRandTagList";
		if(NetWorkUtils.isNetworkAvailable(getActivity())){
			mMArticleJson.setList(MArticleJsoupService.parsePXRandTagList(href, pageNo));
			try {
				//数据存储
				Gson gson = new Gson();
				OpenDBBean  openbean = new OpenDBBean();
	    	    openbean.setUrl(url);
	    	    openbean.setTypename(typename);
			    openbean.setTitle(gson.toJson(mMArticleJson));
			    OpenDBService.insert(getActivity(), openbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			List<OpenDBBean> beanlist =  OpenDBService.queryListType(getActivity(), url,typename);
			String result = beanlist.get(0).getTitle();
			Gson gson = new Gson();
			mMArticleJson = gson.fromJson(result, MArticleJson.class);
		}
		return mMArticleJson;
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
		} else {
			if (result.getList() != null && result.getList().size() > 0) {
				list.addAll(result.getList());
			}
		}
		mMRandTagAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
		mPullToRefreshListView.onRefreshComplete();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
//		case MESSAGE_HANDLER:
//			 doAsync(this, this, this);
//			break;
		default:
			break;
		}
	}
 
	/* (non-Javadoc)
	 * @see com.open.android.fragment.common.CommonPullToRefreshListFragment#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
//		super.onItemClick(parent, view, position, id);
		if(id!=-1 && list.get((int)id)!=null){
			MSearchArticlePullListActivity.startMSearchArticlePullListActivity(getActivity(), list.get((int)id).getHref());
		}
	}
}