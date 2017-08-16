/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午5:40:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.activity.mvp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.handmark.pulltorefresh.library.HeaderGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshHeadGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.open.android.activity.CommonFragmentActivity;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.weak.WeakActivityReferenceHandler;
import com.open.mmxzg.R;
import com.open.mmxzg.activity.m.MImagePullListActivity;
import com.open.mmxzg.adapter.m.MArticleGridAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.MArticlePullGridPresenter;
import com.open.mmxzg.presenter.impl.MArticlePullGridPresenterImpl;
import com.open.mmxzg.utils.UrlUtils;
import com.open.mmxzg.view.MArticlePullGridView;

/**
 *****************************************************************************************************************************************************************************
 * mvp
 * @author :fengguangjing
 * @createTime:2017-8-14下午5:40:28
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MArticlePullGridMVPActivity2 extends CommonFragmentActivity<MArticleJson> 
implements MArticlePullGridView<MArticleJson,MArticlePullGridPresenter>,OnItemClickListener,OnRefreshListener<HeaderGridView>{
	public MArticleGridAdapter mMArticleGridAdapter;
	public MArticlePullGridPresenter mPresenter;
	public PullToRefreshHeadGridView mPullToRefreshHeadGridView;
	public List<MArticleBean> list = new ArrayList<MArticleBean>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.enrz.activity.CommonFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mvp_pullgridview);
		init();
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
		mPresenter.doAsync();
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.activity.CommonFragmentActivity#findView()
	 */
	@Override
	protected void findView() {
		// TODO Auto-generated method stub
		super.findView();
		mPullToRefreshHeadGridView = (PullToRefreshHeadGridView) findViewById(R.id.pull_refresh_grid);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		// TODO Auto-generated method stub
//		super.initValue();
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		} else {
			url = UrlUtils.MMXZG;
		}
		mPresenter = new MArticlePullGridPresenterImpl(this, this, url);
		weakReferenceHandler = new WeakActivityReferenceHandler(this);
		
		mMArticleGridAdapter = new MArticleGridAdapter(this, list);
		mPullToRefreshHeadGridView.setAdapter(mMArticleGridAdapter);
		mPullToRefreshHeadGridView.setMode(Mode.BOTH);
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


	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(MArticlePullGridPresenter presenter) {
		// TODO Auto-generated method stub
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.activity.BaseFragmentActivity#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handlerMessage(msg);
		mPresenter.doAsync();
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
			OpenDBService.insert(this, openbean);
	
			MImagePullListActivity.startMImagePullListActivity(this,
					bean.getHref());
		}
	}

	/* (non-Javadoc)
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	/* (non-Javadoc)
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener#onRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onRefresh(PullToRefreshBase<HeaderGridView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
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

	
	public static void startMArticlePullGridMVPActivity2(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MArticlePullGridMVPActivity2.class);
		context.startActivity(intent);
	}
 
}