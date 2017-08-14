/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午2:35:30
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.mvp;

import static com.google.common.base.Preconditions.checkNotNull;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.android.activity.common.CommonALLActivity;
import com.open.mmxzg.R;
import com.open.mmxzg.model.SplashContract;
import com.open.mmxzg.model.SplashContract.Presenter;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-8-14下午2:35:30
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class SplashFragment extends Fragment implements SplashContract.View {
	private SplashContract.Presenter mPresenter;

	public static SplashFragment newInstance() {
		Bundle arguments = new Bundle();
		SplashFragment fragment = new SplashFragment();
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.start();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_splash, container, false);
		setHasOptionsMenu(true);
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.open.mmxzg.mvp.base.BaseView#setPresenter(java.lang.Object)
	 */
	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		mPresenter = checkNotNull(presenter);
	}

	/* (non-Javadoc)
	 * @see com.open.mmxzg.model.SplashContract.View#goToMain()
	 */
	@Override
	public void goToMain() {
		// TODO Auto-generated method stub
		getActivity().startActivity(new Intent(getActivity(), CommonALLActivity.class));
		getActivity().finish();
	}
	 
}
