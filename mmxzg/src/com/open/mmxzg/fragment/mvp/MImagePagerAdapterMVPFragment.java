/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:11:55
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
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.android.fragment.BaseV4MVPFragment;
import com.open.android.weak.WeakActivityReferenceHandler;
import com.open.mmxzg.R;
import com.open.mmxzg.adapter.m.MImagePagerAdapter;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.MImagePagerAdapterPresenter;
import com.open.mmxzg.view.MImagePagerAdapterView;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16上午11:11:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MImagePagerAdapterMVPFragment extends BaseV4MVPFragment<MArticleJson, MImagePagerAdapterMVPFragment> implements MImagePagerAdapterView<MArticleJson, MImagePagerAdapterPresenter> {
	public ViewPager viewpager;
	public MImagePagerAdapter mMImagePagerAdapter;
	public List<MArticleBean> list = new ArrayList<MArticleBean>();
	// public String url = UrlUtils.PXING_IMAGE;
	public WeakActivityReferenceHandler weakActivityReferenceHandler;
	// public int position;
	public TextView text_page_foot;
	public boolean isHasData;
	private MImagePagerAdapterPresenter mPresenter;

	public static MImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser) {
		MImagePagerAdapterMVPFragment fragment = new MImagePagerAdapterMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	public static MImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser, WeakActivityReferenceHandler weakActivityReferenceHandler) {
		MImagePagerAdapterMVPFragment fragment = new MImagePagerAdapterMVPFragment();
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		fragment.weakActivityReferenceHandler = weakActivityReferenceHandler;
		return fragment;
	}

	public static MImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser, WeakActivityReferenceHandler weakActivityReferenceHandler, List<MArticleBean> list, int position) {
		MImagePagerAdapterMVPFragment fragment = new MImagePagerAdapterMVPFragment();
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		fragment.weakActivityReferenceHandler = weakActivityReferenceHandler;
		if (list != null && list.size() > 0) {
			fragment.list = list;
			fragment.position = position;
			fragment.isHasData = true;
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_m_image_viewpager, container, false);
		viewpager = (ViewPager) view.findViewById(R.id.viewpager);
		text_page_foot = (TextView) view.findViewById(R.id.text_page_foot);
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
	 * @see com.open.android.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(MImagePagerAdapterPresenter presenter) {
		// TODO Auto-generated method stub
		mPresenter = checkNotNull(presenter);
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
		mMImagePagerAdapter = new MImagePagerAdapter(getActivity(), list, weakActivityReferenceHandler);
		viewpager.setAdapter(mMImagePagerAdapter);
		if (list != null && list.size() > 0) {
			mMImagePagerAdapter.notifyDataSetChanged();
			viewpager.setCurrentItem(position);
			text_page_foot.setText((position + 1) + " / " + list.size());
		} else {
			// doAsync(this, this, this);
		}
		mPresenter.doAsync();
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
//			mPresenter.doAsync();
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		super.bindEvent();
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				text_page_foot.setText((position + 1) + " / " + list.size());
				MImagePagerAdapterMVPFragment.this.position = position;
				pageNo++;
				mPresenter.setPageNo(pageNo);
				mPresenter.setPosition(position);
				mPresenter.setUrl(list.get(position).getHref());
				mPresenter.doAsync();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.umei.activity.BaseFragmentActivity#onCallback(java.lang.Object)
	 */
	@Override
	public void onCallback(MArticleJson result) {
		// TODO Auto-generated method stub
		// super.onCallback(result);
		if (result == null) {
			return;
		}
		if (list.size() == 0) {
			// list.clear();
			list.addAll(result.getList());
		} else {
			if (isHasData) {
				list.clear();
				list.addAll(result.getList());
				isHasData = false;
			} else {
				list.set(result.getCurrentPosition(), result.getList().get(0));
			}
		}

		mMImagePagerAdapter.notifyDataSetChanged();
		// text_page_foot.setText((position+1)+" / "+list.size());
	}

}
