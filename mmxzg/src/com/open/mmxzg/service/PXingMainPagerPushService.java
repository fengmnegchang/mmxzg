/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-9下午4:46:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.open.mmxzg.R;
import com.open.mmxzg.activity.m.MImagePullListActivity;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.jsoup.m.MArticleJsoupService;
import com.open.mmxzg.utils.UrlUtils;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-9下午4:46:00
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PXingMainPagerPushService extends Service {
	public static final String TAG = "PXingMainPagerPushService";
	public static final String ACTION = "com.open.pxing.service.PXingMainPagerPushService";
	private Notification mNotification;
	private NotificationManager mManager;
	private ExecutorService executorService = Executors.newFixedThreadPool(5); 
	 
	private Handler mHandler = new Handler(){
		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what==1000){
//				MArticleBean bean = (MArticleBean) msg.obj;
//				dialog(bean.getAlt(),bean.getHref());
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		initNotifiManager();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		executorService.submit(new PollingRunnable());
//		new PollingThread().start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	// 初始化通知栏配置
	private void initNotifiManager() {
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int icon = R.drawable.ic_launcher;
		mNotification = new Notification();
		mNotification.icon = icon;
		mNotification.tickerText = "New Message";
		mNotification.defaults |= Notification.DEFAULT_SOUND;
		mNotification.flags = Notification.FLAG_AUTO_CANCEL;
	}

	// 弹出Notification
	private void showNotification(String msg,String url) {
		mNotification.when = System.currentTimeMillis();
		// Navigator to the new activity when click the notification title
		Intent i = new Intent(this, MImagePullListActivity.class);
		i.putExtra("URL", url);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
		mNotification.setLatestEventInfo(this, getResources().getString(R.string.app_name)+" 养眼美女", msg, pendingIntent);
		mManager.notify(0, mNotification);
	}

	/**
	 * Polling thread 模拟向Server轮询的异步线程
	 * 
	 * @Author Ryan
	 * @Create 2013-7-13 上午10:18:34
	 */
	int count = 0;
//	class PollingThread extends Thread {
//		@Override
//		public void run() {
//			countData();
//		}
//	}
	
	class PollingRunnable implements Runnable{
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			countData();
		}
		
	}
	
	public void countData(){
		System.out.println(TAG+"... count==="+count);
		count++;
		// 当计数能被2整除时弹出通知
		if (count % 2 == 0) {
			List<MArticleBean> list = MArticleJsoupService.parsePXMainTopPager(UrlUtils.PXING,0);
			if(list!=null && list.size()>0){
				java.util.Random random=new java.util.Random();// 定义随机类
				int size=random.nextInt(list.size());// 返回[0,10)集合中的整数，注意不包括10
				MArticleBean bean = list.get(size);
				showNotification(bean.getAlt(),bean.getHref());
				
				Message msg = mHandler.obtainMessage();
				msg.what=1000;
				msg.obj = bean;
				mHandler.sendMessage(msg);
				
				Gson gson = new Gson();
				System.out.println(gson.toJson(bean));
			}
		}
	}
	
	public void dialog(String alt,final String href){
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(alt);
		builder.setNegativeButton("取消", new OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		           // to do
		    }
		});
		builder.setPositiveButton("确定", new OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		           // to do
		    	Intent intent = new Intent(PXingMainPagerPushService.this, MImagePullListActivity.class);
		    	intent.putExtra("URL", href);
		    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
		    }
		});
		final AlertDialog dialog = builder.create();
		//在dialog show前添加此代码，表示该dialog属于系统dialog。
		dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
		dialog.show();
	}

}