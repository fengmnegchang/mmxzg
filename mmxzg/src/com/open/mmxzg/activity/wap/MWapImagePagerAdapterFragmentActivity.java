/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:43:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.activity.wap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Message;

import com.open.android.activity.CommonFragmentActivity;
import com.open.android.weak.WeakActivityReferenceHandler;
import com.open.mmxzg.R;
import com.open.mmxzg.fragment.wap.MWapImagePagerAdapterMVPFragment;
import com.open.mmxzg.json.m.MArticleJson;
import com.open.mmxzg.presenter.impl.MWapImagePagerAdapterPresenterImpl;
import com.open.mmxzg.utils.UrlUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:43:01
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapImagePagerAdapterFragmentActivity extends CommonFragmentActivity<MArticleJson> {
	private String url = UrlUtils.MMXZG_IMAGE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m_image_viewpager_f);
		init();
	}

	@Override
	protected void findView() {
		super.findView();
		weakReferenceHandler = new WeakActivityReferenceHandler(this);
	}
	 
	/* (non-Javadoc)
	 * @see com.open.qianbailu.activity.CommonFragmentActivity#initValue()
	 */
	@Override
	protected void initValue() {
		if (getIntent().getStringExtra("URL") != null) {
			url = getIntent().getStringExtra("URL");
		} else {
			url = UrlUtils.MMXZG_E_WAP_IMAGE;
		}
		// TODO Auto-generated method stub
		MArticleJson mShowJson = (MArticleJson) getIntent().getSerializableExtra("SHOW_JSON");
//		Fragment fragment;
//		if(mShowJson!=null && mShowJson.getList()!=null&& mShowJson.getList().size()>0){
//			fragment = MImagePagerAdapterFragment.newInstance(url, true,weakReferenceHandler,mShowJson.getList(),mShowJson.getCurrentPosition());
//		}else{
//			fragment = MImagePagerAdapterFragment.newInstance(url, true,weakReferenceHandler);
//		}
//		getSupportFragmentManager().beginTransaction().replace(R.id.layout_viewpager, fragment).commit();
		
		MWapImagePagerAdapterMVPFragment fragment;
		if(mShowJson!=null && mShowJson.getList()!=null&& mShowJson.getList().size()>0){
			fragment = MWapImagePagerAdapterMVPFragment.newInstance(url, true,weakReferenceHandler,mShowJson.getList(),mShowJson.getCurrentPosition());
		}else{
			fragment = MWapImagePagerAdapterMVPFragment.newInstance(url, true,weakReferenceHandler);
		}
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_viewpager, fragment).commit();
		
		new MWapImagePagerAdapterPresenterImpl(this, fragment, url);
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.open.umei.activity.BaseFragmentActivity#handlerMessage(android.os
	 * .Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handlerMessage(msg);
		switch (msg.what) {
		case MESSAGE_SCREEN_ORIENTATION:
			if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
			} else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
			}else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
			}
			break;
		default:
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		} else{ 
		 super.onBackPressed();
		}
	}
	 
	public static void startMWapImagePagerAdapterFragmentActivity(Context context, String url,MArticleJson mMArticleJson) {
		Intent intent = new Intent();
		intent.putExtra("SHOW_JSON", mMArticleJson);
		intent.putExtra("URL", url);
		intent.setClass(context, MWapImagePagerAdapterFragmentActivity.class);
		context.startActivity(intent);
	}
}
