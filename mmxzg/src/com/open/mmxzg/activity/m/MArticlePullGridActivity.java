///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-7-28下午4:07:10
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.activity.m;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//
//import com.open.mmxzg.fragment.m.MArticlePullGridFragmnet;
//import com.open.mmxzg.utils.UrlUtils;
//import com.open.mmxzg.R;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-7-28下午4:07:10
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class MArticlePullGridActivity extends MCommonTitleBarActivity{
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.open.enrz.activity.CommonFragmentActivity#initValue()
//	 */
//	@Override
//	protected void initValue() {
//		// TODO Auto-generated method stub
////		super.initValue();
//		if (getIntent().getStringExtra("URL") != null) {
//			url = getIntent().getStringExtra("URL");
//		} else {
//			url = UrlUtils.MMXZG;
//		}
//		addfragment();
//	}
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.open.android.activity.CommonFragmentActivity#addfragment()
//	 */
//	@Override
//	public void addfragment() {
//		// TODO Auto-generated method stub
//		Fragment fragment = MArticlePullGridFragmnet.newInstance(url, true);
//		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, fragment).commit();
//	}
//
//	public static void startMArticlePullGridActivity(Context context, String url) {
//		Intent intent = new Intent();
//		intent.putExtra("URL", url);
//		intent.setClass(context, MArticlePullGridActivity.class);
//		context.startActivity(intent);
//	}
//}