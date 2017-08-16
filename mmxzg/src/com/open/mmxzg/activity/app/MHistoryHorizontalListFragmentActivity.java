/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-12下午1:42:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.activity.app;

import android.content.Context;
import android.content.Intent;

import com.open.mmxzg.R;
import com.open.mmxzg.activity.m.MCommonTitleBarActivity;
import com.open.mmxzg.fragment.mvp.MHistoryHorizontalListMVPFragment;
import com.open.mmxzg.presenter.impl.MHistoryHorizontalListPresenterImpl;
import com.open.mmxzg.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-12下午1:42:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MHistoryHorizontalListFragmentActivity extends MCommonTitleBarActivity{
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
			url = UrlUtils.MM_M;
		}
		addfragment();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.android.activity.CommonFragmentActivity#addfragment()
	 */
	@Override
	public void addfragment() {
		// TODO Auto-generated method stub
		MHistoryHorizontalListMVPFragment fragment = MHistoryHorizontalListMVPFragment.newInstance(url, true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
		new MHistoryHorizontalListPresenterImpl(this, fragment);
	}

	public static void startMHistoryHorizontalListFragmentActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MHistoryHorizontalListFragmentActivity.class);
		context.startActivity(intent);
	}
}