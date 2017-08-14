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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.open.mmxzg.R;
import com.open.mmxzg.fragment.mvp.MArticlePullListMVPFragment;
import com.open.mmxzg.presenter.MArticlePullListPresenter;
import com.open.mmxzg.utils.ActivityUtils;
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
public class MArticlePullListMVPActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskdetail_act);

		MArticlePullListMVPFragment mMArticlePullListMVPFragmnet = (MArticlePullListMVPFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
		if (mMArticlePullListMVPFragmnet == null) {
			mMArticlePullListMVPFragmnet = MArticlePullListMVPFragment.newInstance(true);
			ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMArticlePullListMVPFragmnet, R.id.contentFrame);
		}

		// Create the presenter
		new MArticlePullListPresenter(this, mMArticlePullListMVPFragmnet,UrlUtils.MMXZG);
		 
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
