/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-15上午10:07:21
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.presenter.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.mvp.base.CommonAsyncTaskPresenter;
import com.open.android.utils.NetWorkUtils;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.jsoup.m.MArticleJsoupService;
import com.open.mmxzg.presenter.MArticlePullListPresenter2;
import com.open.mmxzg.view.MArticlePullListView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-15上午10:07:21
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MArticlePullListPresenterImpl extends CommonAsyncTaskPresenter<MArticleJson> implements MArticlePullListPresenter2 {
	private MArticlePullListView mMArticlePullListView;
	private Context mContext;

	public MArticlePullListPresenterImpl(Context context, @NonNull MArticlePullListView view, String url) {
		mMArticlePullListView = checkNotNull(view, "MArticlePullListView cannot be null!");
		mMArticlePullListView.setPresenter(this);
		this.mContext = context;
		this.url = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.mvp.base.BasePresenter#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

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
		if (pageNo > 1) {
			href = url + "index_" + pageNo + ".html";
		}
		String typename = "MArticleJsoupService-parseMmxzgList-" + pageNo;
		if (NetWorkUtils.isNetworkAvailable(mContext)) {
			mMArticleJson.setList(MArticleJsoupService.parseMmxzgList(href, pageNo));
			try {
				// 数据存储
				Gson gson = new Gson();
				OpenDBBean openbean = new OpenDBBean();
				openbean.setUrl(url);
				openbean.setTypename(typename);
				openbean.setTitle(gson.toJson(mMArticleJson));
				OpenDBService.insert(mContext, openbean);

				// jdbc
				// DBMySqlUtils.query();
				// DBMySqlUtils.query(openbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// ImgContainerDBService.imgContainer(mMArticleJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			List<OpenDBBean> beanlist = OpenDBService.queryListType(mContext, url, typename);
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
		mMArticlePullListView.onCallback(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.mmxzg.model.MArticlePullListContract.Presenter#handlerMessage()
	 */
	@Override
	public void doAsync() {
		// TODO Auto-generated method stub
		try {
			doAsync(this, this, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.mmxzg.model.MArticlePullListContract.Presenter#setPageNo(int)
	 */
	@Override
	public void setPageNo(int pageNo) {
		// TODO Auto-generated method stub
		this.pageNo = pageNo;
	}
}
