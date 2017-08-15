///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-14下午3:35:57
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.fragment.mvp;
//
//import static com.google.common.base.Preconditions.checkNotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.os.Bundle;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.text.format.DateUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.open.android.bean.CommonBean;
//import com.open.android.fragment.BaseV4MVPFragment;
//import com.open.android.json.CommonJson;
//import com.open.android.mvp.presenter.CommonPresenter;
//import com.open.mmxzg.R;
//import com.open.mmxzg.view.MArticlePullListView;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-14下午3:35:57
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class CommonPullToRefreashListMVPFragment<B extends CommonBean, C extends CommonJson,P extends CommonPresenter> extends BaseV4MVPFragment<C, CommonPullToRefreashListMVPFragment> 
//implements MArticlePullListView<C,P>,OnRefreshListener<ListView> ,OnItemClickListener{
//	public P mPresenter;
//	public PullToRefreshListView mPullToRefreshListView;
//	public List<B> list = new ArrayList<B>();
////	public MArticleListAdapter mMArticleListAdapter;
//
//	public static CommonPullToRefreashListMVPFragment newInstance(boolean isVisibleToUser) {
//		CommonPullToRefreashListMVPFragment fragment = new CommonPullToRefreashListMVPFragment();
//		fragment.setFragment(fragment);
//		fragment.setUserVisibleHint(isVisibleToUser);
//		return fragment;
//	}
//	
//	@Override
//	public void onResume() {
//		super.onResume();
//		mPresenter.start();
//	}
//	
//	@Nullable
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		View root = inflater.inflate(R.layout.fragment_common_pulllistview, container, false);
//		mPullToRefreshListView = (PullToRefreshListView) root.findViewById(R.id.pull_refresh_list);
//		setHasOptionsMenu(true);
//		return root;
//	}
//	
//	
//	/* (non-Javadoc)
//	 * @see com.open.mmxzg.mvp.base.BaseView#setPresenter(java.lang.Object)
//	 */
//	@Override
//	public void setPresenter(P presenter) {
//		// TODO Auto-generated method stub
//		mPresenter = checkNotNull(presenter);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.open.mmxzg.model.MArticlePullListContract.View#bindEvent()
//	 */
//	@Override
//	public void bindEvent() {
//		// TODO Auto-generated method stub
//		mPullToRefreshListView.setOnRefreshListener(this);
//		mPullToRefreshListView.setOnItemClickListener(this);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.open.mmxzg.model.MArticlePullListContract.View#initValues()
//	 */
//	@Override
//	public void initValues() {
//		// TODO Auto-generated method stub
////		mMArticleListAdapter = new MArticleListAdapter(getActivity(), list);
////		mPullToRefreshListView.setAdapter(mMArticleListAdapter);
////		mPullToRefreshListView.setMode(Mode.BOTH);
//	}
// 
//	/* (non-Javadoc)
//	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
//	 */
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.open.enrz.fragment.BaseV4Fragment#handlerMessage(android.os.Message)
//	 */
//	@Override
//	public void handlerMessage(Message msg) {
//		// TODO Auto-generated method stub
//		switch (msg.what) {
//		case MESSAGE_HANDLER:
//			mPresenter.doAsync();
//			break;
//		default:
//			break;
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
//	 */
//	@Override
//	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//		// TODO Auto-generated method stub
//		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//		// Update the LastUpdatedLabel
//		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
//		// Do work to refresh the list here.
//		if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_START) {
//			pageNo = 1;
//			mPresenter.setPageNo(1);
//			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
//		} else if (mPullToRefreshListView.getCurrentMode() == Mode.PULL_FROM_END) {
//			pageNo++;
//			mPresenter.setPageNo(pageNo);
//			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see com.open.mmxzg.model.MArticlePullListContract.View#onCallback(com.open.mmxzg.json.m.MArticleJson)
//	 */
//	@Override
//	public void onCallback(C result) {
//		if(result==null){
//			return;
//		}
//	}
//
//}
