/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午2:32:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.presenter;

import static com.google.common.base.Preconditions.checkNotNull;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.open.android.activity.common.CommonALLActivity;
import com.open.mmxzg.SplashActivity;
import com.open.mmxzg.application.PXingApplication;
import com.open.mmxzg.db.UserInfoDBService;
import com.open.mmxzg.model.SplashContract;
import com.open.mmxzg.service.PXingMainPagerPushService;
import com.open.mmxzg.utils.DeviceUtils;
import com.open.mmxzg.utils.ServiceUtils;
import com.taobao.sophix.SophixManager;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午2:32:55
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SplashPresenter implements SplashContract.Presenter {
	private final SplashContract.View mSplashView;
	 private Context mContext;
	
	private static final int SHOW_TIME_MIN = 3000;// 最小显示时间
	private long mStartTime;// 开始时间
	private static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 0;
	
	public SplashPresenter(Context context,@NonNull SplashContract.View splashView) {
		mSplashView = checkNotNull(splashView, "splashView cannot be null!");
		mSplashView.setPresenter(this);
		mContext = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.mvp.base.BasePresenter#start()
	 */
	@Override
	public void start() {
		initData();
	}
 

	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.SplashContract.Presenter#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mStartTime = System.currentTimeMillis();// 记录开始时间，
		SophixManager.getInstance().queryAndLoadNewPatch();
		// if (Build.VERSION.SDK_INT >= 23) {
		// requestExternalStoragePermission();
		// }
		MiPushClient.setAlias(mContext, DeviceUtils.getDeviceId(mContext), null);
		Log.d("SplashActivity", "Alias===" + DeviceUtils.getDeviceId(mContext));
		MiPushClient.resumePush(mContext, null);
//		PXingApplication.msgDisplayListener = new PXingApplication.MsgDisplayListener() {
//			@Override
//			public void handle(final String msg) {
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						updateConsole(msg);
//					}
//				});
//			}
//		};
		
//		Intent intent = new Intent(this, MImagePullListActivity.class);
//		intent.putExtra("URL", "1112");
//		String uriString = intent.toUri(Intent.URI_INTENT_SCHEME);//该uriString就是Constants.EXTRA_PARAM_INTENT_URI对应的值
//        Log.d("uriString===", uriString);
		
		if (!ServiceUtils.isServiceExisted(mContext, PXingMainPagerPushService.class.getSimpleName())) {
			ServiceUtils.startPollingService(mContext, 5, PXingMainPagerPushService.class, PXingMainPagerPushService.ACTION);
		}

		new Thread() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
					UserInfoDBService.userinfo(mContext);
					mHandler.sendEmptyMessage(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.run();
			}

		}.start();
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1000:// 如果城市列表加载完毕，就发送此消息
				long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
				if (loadingTime < SHOW_TIME_MIN) {// 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
					mHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN - loadingTime);
				} else {
					mHandler.post(goToMainActivity);
				}
				break;
			default:
				break;
			}
		}
	};
	// 进入下一个Activity
	Runnable goToMainActivity = new Runnable() {
		@Override
		public void run() {
			mSplashView.goToMain();
		}
	};


}
