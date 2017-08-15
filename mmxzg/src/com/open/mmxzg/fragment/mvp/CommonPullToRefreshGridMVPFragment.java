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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
import com.open.android.bean.CommonBean;
import com.open.android.fragment.BaseV4MVPFragment;
import com.open.android.json.CommonJson;
import com.open.mmxzg.R;
import com.open.mmxzg.mvp.base.CommonPresenter;
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
public class CommonPullToRefreshGridMVPFragment<B extends CommonBean, C extends CommonJson,P extends CommonPresenter> extends BaseV4MVPFragment<C, CommonPullToRefreshGridMVPFragment> 
implements MArticlePullGridView<C,P>,OnRefreshListener<HeaderGridView> ,OnItemClickListener{
	public P mPresenter;
	public PullToRefreshHeadGridView mPullToRefreshHeadGridView;
	public List<B> list = new ArrayList<B>();
//	public MArticleGridAdapter mMArticleGridAdapter;

	public static CommonPullToRefreshGridMVPFragment newInstance(boolean isVisibleToUser) {
		CommonPullToRefreshGridMVPFragment fragment = new CommonPullToRefreshGridMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_common_pullgridview, container, false);
		mPullToRefreshHeadGridView = (PullToRefreshHeadGridView) root.findViewById(R.id.pull_refresh_grid);
		setHasOptionsMenu(true);
		return root;
	}
	
	/* (non-Javadoc)
	 * @see com.open.mmxzg.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(P presenter) {
		// TODO Auto-generated method stub
		mPresenter = checkNotNull(presenter);
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		mPullToRefreshHeadGridView.setOnRefreshListener(this);
		mPullToRefreshHeadGridView.setOnItemClickListener(this);
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
//		mMArticleGridAdapter = new MArticleGridAdapter(getActivity(), list);
//		mPullToRefreshHeadGridView.setAdapter(mMArticleGridAdapter);
//		mPullToRefreshHeadGridView.setMode(Mode.BOTH);
	}
 
	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
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
		case MESSAGE_HANDLER:
			mPresenter.doAsync();
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onRefresh(PullToRefreshBase<HeaderGridView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
			pageNo = 1;
			mPresenter.setPageNo(1);
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		} else if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_END) {
			pageNo++;
			mPresenter.setPageNo(pageNo);
			weakReferenceHandler.sendEmptyMessage(MESSAGE_HANDLER);
		}
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.MArticlePullListContract.View#onCallback(com.open.mmxzg.json.m.MArticleJson)
	 */
	@Override
	public void onCallback(C result) {
		if(result==null){
			return;
		}
		
	}
 

}
