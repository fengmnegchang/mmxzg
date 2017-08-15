/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午3:36:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.activity.mvp;

import android.content.Context;
import android.content.Intent;

import com.open.mmxzg.R;
import com.open.mmxzg.activity.m.MCommonTitleBarActivity;
import com.open.mmxzg.fragment.mvp.MArticlePullListMVPFragment2;
import com.open.mmxzg.presenter.impl.MArticlePullListPresenterImpl;
import com.open.mmxzg.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午3:36:59
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MArticlePullListMVPActivity extends MCommonTitleBarActivity {
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
////		setContentView(R.layout.taskdetail_act);
//
//		MArticlePullListMVPFragment mMArticlePullListMVPFragmnet = (MArticlePullListMVPFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//		if (mMArticlePullListMVPFragmnet == null) {
//			mMArticlePullListMVPFragmnet = MArticlePullListMVPFragment.newInstance(true);
//			ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMArticlePullListMVPFragmnet, R.id.contentFrame);
//		}
//
//		// Create the presenter
//		new MArticlePullListPresenter(this, mMArticlePullListMVPFragmnet,UrlUtils.MMXZG);
//		 
//	}
	
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
//		MArticlePullListMVPFragment mMArticlePullListMVPFragmnet = (MArticlePullListMVPFragment) getSupportFragmentManager().findFragmentById(R.id.layout_content);
//		if (mMArticlePullListMVPFragmnet == null) {
//			mMArticlePullListMVPFragmnet = MArticlePullListMVPFragment.newInstance(true);
//			ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMArticlePullListMVPFragmnet, R.id.layout_content);
//		}
		
		MArticlePullListMVPFragment2 fragment =  MArticlePullListMVPFragment2.newInstance(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
		
		// Create the presenter
		new MArticlePullListPresenterImpl(this, fragment,url);
	}

	public static void startMArticlePullListMVPActivity(Context context, String url) {
		Intent intent = new Intent();
		intent.putExtra("URL", url);
		intent.setClass(context, MArticlePullListMVPActivity.class);
		context.startActivity(intent);
	}

//	@Override
//	public boolean onSupportNavigateUp() {
//		onBackPressed();
//		return true;
//	}
}
