/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午1:36:21
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
import com.open.mmxzg.presenter.MCollectionGridPresenter;
import com.open.mmxzg.view.MCollectionGridView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午1:36:21
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MCollectionGridPresenterImpl extends CommonAsyncTaskPresenter<OpenDBJson> implements MCollectionGridPresenter {
	private MCollectionGridView<OpenDBJson,MCollectionGridPresenter> mMCollectionGridView;
	public MCollectionGridPresenterImpl(Context mContext,MCollectionGridView view){
		this.mContext = mContext;
		this.mMCollectionGridView = view;
		this.mMCollectionGridView.setPresenter(this);
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.CommonAsyncTaskPresenter#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(OpenDBJson result) {
		// TODO Auto-generated method stub
		super.onCallback(result);
		mMCollectionGridView.onCallback(result);
	}
	/* (non-Javadoc)
	 * @see com.open.android.fragment.common.CommonPullToRefreshGridFragment#call()
	 */
	@Override
	public OpenDBJson call() throws Exception {
		// TODO Auto-generated method stub
		OpenDBJson mOpenDBJson = new OpenDBJson();
		mOpenDBJson.setList(OpenDBService.queryListType(mContext, 0));
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
