/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午9:50:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.wap;

import android.view.View;
import android.widget.AdapterView;

import com.open.android.bean.db.OpenDBBean;
import com.open.android.db.service.OpenDBService;
import com.open.mmxzg.activity.wap.MWapImagePullListActivity;
import com.open.mmxzg.bean.m.MArticleBean;
import com.open.mmxzg.fragment.mvp.MArticlePullGridMVPFragment2;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午9:50:42
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapArticlePullGridMVPFragment extends MArticlePullGridMVPFragment2 {
	public static MWapArticlePullGridMVPFragment newInstance(boolean isVisibleToUser) {
		MWapArticlePullGridMVPFragment fragment = new MWapArticlePullGridMVPFragment();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if(id!=-1 && list.get((int)id)!=null){
		//保存收藏
		MArticleBean bean = list.get((int) id);
		String href = "";
		if (bean.getHref().contains("_")) {
			href = bean.getHref().split("_")[0] + ".html";
		} else {
			href = bean.getHref();
		}
		OpenDBBean openbean = new OpenDBBean();
		openbean.setImgsrc(bean.getDataimg());
		openbean.setUrl(href);
		openbean.setType(1);
		openbean.setTitle(bean.getAlt());
		openbean.setTypename(1+"");
		OpenDBService.insert(getActivity(), openbean);

		MWapImagePullListActivity.startMWapImagePullListActivity(getActivity(),
				bean.getHref());
	}
	}
}
