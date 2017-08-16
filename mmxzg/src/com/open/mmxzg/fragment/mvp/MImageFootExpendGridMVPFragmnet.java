/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午10:44:16
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.android.fragment.BaseV4MVPFragment;
import com.open.android.view.ExpendGridView;
import com.open.mmxzg.R;
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
 * @createTime:2017-8-16上午10:44:16
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MImageFootExpendGridMVPFragmnet extends BaseV4MVPFragment<MArticleJson, MImageFootExpendGridMVPFragmnet> 
implements MImageFootGridView<MArticleJson, MImageFootGridPresenter>,OnItemClickListener {
	
	private MArticleGridAdapter mMArticleGridAdapter;
	private ExpendGridView mExpendGridView;
	public List<MArticleBean> list = new ArrayList<MArticleBean>();
	private MImageFootGridPresenter mPresenter;
	
	public static MImageFootExpendGridMVPFragmnet newInstance (boolean isVisibleToUser) {
		MImageFootExpendGridMVPFragmnet fragment = new MImageFootExpendGridMVPFragmnet();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_expend_gridview, container, false);
		mExpendGridView = (ExpendGridView) view.findViewById(R.id.expend_grid);
		return view;
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
		mExpendGridView.setAdapter(mMArticleGridAdapter);
		
	}
	
	/* (non-Javadoc)
	 * @see com.open.android.fragment.BaseV4MVPFragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		mExpendGridView.setOnItemClickListener(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
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
		list.clear();
		list.addAll(result.getList());
//		Log.i(TAG, "getMode ===" + mPullToRefreshHeadGridView.getCurrentMode());
//		if (mPullToRefreshHeadGridView.getCurrentMode() == Mode.PULL_FROM_START) {
//			list.clear();
//			list.addAll(result.getList());
//			pageNo = 1;
//		} else {
//			if (result.getList() != null && result.getList().size() > 0) {
//				list.addAll(result.getList());
//			}
//		}
		mMArticleGridAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
//		mPullToRefreshHeadGridView.onRefreshComplete();
	}
	

	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(MImageFootGridPresenter presenter) {
		// TODO Auto-generated method stub
		mPresenter = checkNotNull(presenter);
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
			if (bean.getHref()!=null && bean.getHref().contains("_")) {
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
