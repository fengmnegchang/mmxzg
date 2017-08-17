/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:30:56
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
import com.open.mmxzg.fragment.mvp.MImageFootExpendGridMVPFragmnet;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-17上午10:30:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapImageFootExpendGridMVPFragmnet extends MImageFootExpendGridMVPFragmnet {
	public static MWapImageFootExpendGridMVPFragmnet newInstance (boolean isVisibleToUser) {
		MWapImageFootExpendGridMVPFragmnet fragment = new MWapImageFootExpendGridMVPFragmnet();
		fragment.setFragment(fragment);
		fragment.setUserVisibleHint(isVisibleToUser);
		return fragment;
	}
	/* (non-Javadoc)
	 * @see com.open.android.fragment.common.CommonPullToRefreshListFragment#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
//		super.onItemClick(parent, view, position, id);
		if(id!=-1 && list.get((int)id)!=null){
			//保存收藏
			MArticleBean bean = list.get((int) id);
			String href = "";
			if (bean.getHref()!=null && bean.getHref().contains("_")) {
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
