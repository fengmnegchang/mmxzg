/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午1:49:11
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.presenter.impl;

import android.content.Context;

import com.open.android.db.service.OpenDBService;
import com.open.android.mvp.base.CommonAsyncTaskPresenter;
import com.open.mmxzg.json.m.OpenDBJson;
import com.open.mmxzg.presenter.MHistoryListGridPresenter;
import com.open.mmxzg.view.MHistoryListGridView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午1:49:11
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MHistoryListGridPresenterImpl extends CommonAsyncTaskPresenter<OpenDBJson> implements MHistoryListGridPresenter {
	private MHistoryListGridView<OpenDBJson, MHistoryListGridPresenter> mMHistoryListGridView;
	public MHistoryListGridPresenterImpl(Context mContext,MHistoryListGridView<OpenDBJson, MHistoryListGridPresenter> view){
		this.mContext = mContext;
		this.mMHistoryListGridView = view;
		this.mMHistoryListGridView.setPresenter(this);
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(OpenDBJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMHistoryListGridView.onCallback(result);
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.fragment.common.CommonPullToRefreshGridFragment#call()
	 */
	@Override
	public OpenDBJson call() throws Exception {
		// TODO Auto-generated method stub
		OpenDBJson mOpenDBJson = new OpenDBJson();
		mOpenDBJson.setList(OpenDBService.queryListType(mContext, 1));
		return mOpenDBJson;
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.presenter.CommonPresenter#doAsync()
	 */
	@Override
	public void doAsync() {
		// TODO Auto-generated method stub
		doAsync(this, this, this);
	}

	/* (non-Javadoc)
	 * @see com.open.android.mvp.presenter.CommonPresenter#setPageNo(int)
	 */
	@Override
	public void setPageNo(int pageNo) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.BasePresenter#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
