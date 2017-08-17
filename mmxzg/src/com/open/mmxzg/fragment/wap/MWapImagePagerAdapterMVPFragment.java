/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:45:05
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.wap;

import java.util.List;

import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.open.android.weak.WeakActivityReferenceHandler;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.fragment.mvp.MImagePagerAdapterMVPFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:45:05
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapImagePagerAdapterMVPFragment extends MImagePagerAdapterMVPFragment {
	public static MWapImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser) {
		MWapImagePagerAdapterMVPFragment fragment = new MWapImagePagerAdapterMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		return fragment;
	}

	public static MWapImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser, WeakActivityReferenceHandler weakActivityReferenceHandler) {
		MWapImagePagerAdapterMVPFragment fragment = new MWapImagePagerAdapterMVPFragment();
		fragment.setUserVisibleHint(isVisibleToUser);
		fragment.url = url;
		fragment.weakActivityReferenceHandler = weakActivityReferenceHandler;
		return fragment;
	}

	public static MWapImagePagerAdapterMVPFragment newInstance(String url, boolean isVisibleToUser, WeakActivityReferenceHandler weakActivityReferenceHandler, List<MArticleBean> list, int position) {
		MWapImagePagerAdapterMVPFragment fragment = new MWapImagePagerAdapterMVPFragment();
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
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.qianbailu.fragment.BaseV4Fragment#bindEvent()
	 */
	@Override
	public void bindEvent() {
		// TODO Auto-generated method stub
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				text_page_foot.setText((position + 1) + " / " + list.size());
//				MImagePagerAdapterMVPFragment.this.position = position;
//				pageNo++;
//				mPresenter.setPageNo(pageNo);
//				mPresenter.setPosition(position);
//				mPresenter.setUrl(list.get(position).getHref());
//				mPresenter.doAsync();
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
}
