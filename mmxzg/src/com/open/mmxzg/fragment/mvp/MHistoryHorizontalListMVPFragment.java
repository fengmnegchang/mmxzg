/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午1:59:40
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.open.android.bean.db.OpenDBBean;
import com.open.android.fragment.BaseV4MVPFragment;
import com.open.mmxzg.R;
import com.open.mmxzg.adapter.app.MHoizontalAdapter;
import com.open.mmxzg.json.m.OpenDBJson;
import com.open.mmxzg.presenter.MHistoryHorizontalListPresenter;
import com.open.mmxzg.view.MHistoryHorizontalListView;
import com.open.mmxzg.widget.HorizontalListView;
import android.view.GestureDetector.OnGestureListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午1:59:40
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MHistoryHorizontalListMVPFragment extends BaseV4MVPFragment<OpenDBJson, MHistoryHorizontalListMVPFragment> 
implements MHistoryHorizontalListView<OpenDBJson, MHistoryHorizontalListPresenter> ,OnGestureListener{
	public HorizontalListView mHorizontalListView;
	public MHoizontalAdapter mMHoizontalAdapter;
	public List<OpenDBBean> list = new ArrayList<OpenDBBean>();
	private MHistoryHorizontalListPresenter mPresenter;

	public static MHistoryHorizontalListMVPFragment newInstance(String url, boolean isVisibleToUser) {
		MHistoryHorizontalListMVPFragment fragment = new MHistoryHorizontalListMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_horizontal_listview, container, false);
		mHorizontalListView = (HorizontalListView) view.findViewById(R.id.pull_refresh_list);
		return view;
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mPresenter.start();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mMHoizontalAdapter = new MHoizontalAdapter(getActivity(), list);
		mHorizontalListView.setAdapter(mMHoizontalAdapter);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.android.fragment.common.CommonPullToRefreshGridFragment#onCallback
	 * (com.open.android.json.CommonJson)
	 */
	@Override
	public void onCallback(OpenDBJson result) {
		// TODO Auto-generated method stub
		// super.onCallback(result);
		list.clear();
		for(OpenDBBean bean:result.getList()){
			if("0".equals(bean.getTypename())){
				list.add(bean);
			}
		}
		pageNo = 1;

		// mPullToRefreshHeadGridView.getRefreshableView().setNumColumns(result.getList().size());
		mMHoizontalAdapter.notifyDataSetChanged();
		// Call onRefreshComplete when the list has been refreshed.
	}

	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(MHistoryHorizontalListPresenter presenter) {
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
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onShowPress(android.view.MotionEvent)
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

}
