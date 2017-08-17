/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午5:57:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.fragment.mvp;

import android.view.View;
import android.widget.AdapterView;

import com.open.mmxzg.PXingWebViewActivity;
import com.open.mmxzg.activity.wap.MWapArticlePullGridMVPActivity;
import com.open.mmxzg.bean.m.MSlideMenuBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-16下午5:57:57
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MWapLeftMenuPullListMVPFragmnet extends MLeftMenuPullListMVPFragment{
	public static MWapLeftMenuPullListMVPFragmnet newInstance(boolean isVisibleToUser) {
		MWapLeftMenuPullListMVPFragmnet fragment = new MWapLeftMenuPullListMVPFragmnet();
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
			if(id!=-1){
				MSlideMenuBean bean = list.get((int)id);
				if(bean!=null){
					 MWapArticlePullGridMVPActivity.startMWapArticlePullGridMVPActivity(getActivity(), bean.getHref());
				}
			}
			
		}
}
