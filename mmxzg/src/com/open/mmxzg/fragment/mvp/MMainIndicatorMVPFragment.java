/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:27:21
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
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.android.adapter.CommonFragmentPagerAdapter;
import com.open.android.fragment.BaseV4MVPFragment;
import com.open.indicator.TabPageIndicator;
import com.open.mmxzg.R;
import com.open.mmxzg.bean.m.MSlideMenuBean;
import com.open.mmxzg.json.m.MSlideMenuJson;
import com.open.mmxzg.presenter.MMainIndicatorPresenter;
import com.open.mmxzg.presenter.impl.MArticlePullGridPresenterImpl;
import com.open.mmxzg.view.MMainIndicatorView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:27:21
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MMainIndicatorMVPFragment extends BaseV4MVPFragment<MSlideMenuJson, MMainIndicatorMVPFragment> implements MMainIndicatorView<MSlideMenuJson, MMainIndicatorPresenter> {
	public ArrayList<MSlideMenuBean> list = new ArrayList<MSlideMenuBean>();
	public ViewPager viewpager;
	public TabPageIndicator indicator;
	public List<String> titleList = new ArrayList<String>();
	public List<Fragment> listRankFragment = new ArrayList<Fragment>();// view数组
	public CommonFragmentPagerAdapter mRankPagerAdapter;
	public List<MArticlePullGridPresenterImpl> listPresenterImpl = new ArrayList<MArticlePullGridPresenterImpl>();// 
	private MMainIndicatorPresenter mPresenter;
	
	public static MMainIndicatorMVPFragment newInstance(String url, boolean isVisibleToUser) {
		MMainIndicatorMVPFragment fragment = new MMainIndicatorMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_common_indicator_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#initValues()
	 */
	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		super.initValues();
		mRankPagerAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), listRankFragment, titleList);
		viewpager.setAdapter(mRankPagerAdapter);
		indicator.setViewPager(viewpager);
	}
	
	 

	/* (non-Javadoc)
	 * @see com.open.android.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(MMainIndicatorPresenter presenter) {
		// TODO Auto-generated method stub
		mPresenter = checkNotNull(presenter);
	}
	
	@Override
	public void onCallback(MSlideMenuJson result) {
		// TODO Auto-generated method stub
		if(result==null){
			return;
		}
		list.clear();
		list.addAll(result.getList());
		titleList.clear();

		MArticlePullGridMVPFragment2 fragment;
		for (int i=0;i< result.getList().size();i++) {
			MSlideMenuBean bean = result.getList().get(i);
			titleList.add(bean.getTitle());
			if(i==0){
				fragment = MArticlePullGridMVPFragment2.newInstance(true);
			}else{
//				if("性爱技巧".equals(bean.getTitle())){
//    				fragment = MSexNovelPullListFragmnet.newInstance(bean.getHref(), false);
//				}else{
//					fragment = MArticlePullGridFragmnet.newInstance(bean.getHref(),false);
//				}
				fragment = MArticlePullGridMVPFragment2.newInstance(false);
			}
			listPresenterImpl.add(new MArticlePullGridPresenterImpl(getActivity(), fragment, bean.getHref()));
			listRankFragment.add(fragment);
		}
		mRankPagerAdapter.notifyDataSetChanged();
		indicator.notifyDataSetChanged();
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

}
