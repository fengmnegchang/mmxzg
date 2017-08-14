/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午2:36:19
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.activity.mvp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.open.mmxzg.R;
import com.open.mmxzg.application.PXingApplication;
import com.open.mmxzg.bean.m.PatchBean;
import com.open.mmxzg.fragment.mvp.SplashFragment;
import com.open.mmxzg.presenter.SplashPresenter;
import com.open.mmxzg.utils.ActivityUtils;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午2:36:19
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class SplashMvpActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        SplashFragment splashFragment = (SplashFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (splashFragment == null) {
        	splashFragment = SplashFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
            		splashFragment, R.id.contentFrame);
        }

        // Create the presenter
        new SplashPresenter(this,splashFragment);
        
        PXingApplication.msgDisplayListener = new PXingApplication.MsgDisplayListener() {
			@Override
			public void handle(final String msg) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						updateConsole(msg);
					}
				});
			}
		};
    }
    
    /**
	 * 更新监控台的输出信息
	 * 
	 * @param content
	 *            更新内容
	 */
	private void updateConsole(String content) {
		try {
			Gson gson = new Gson();
			PatchBean bean = gson.fromJson(content, PatchBean.class);
			if (bean != null) {
				if (bean.getHandlePatchVersion() > 0) {
					alertNewPatch(content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void alertNewPatch(String content) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(new String[] { content + "检测到热更新包，重启生效" }, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		builder.show();
	}

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
