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
import com.open.mmxzg.activity.mvp.MArticlePullGridMVPActivity;
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
					if("动态图片".equals(bean.getTitle()) || "性爱技巧".equals(bean.getTitle())){
						PXingWebViewActivity.startPXingWebViewActivity(getActivity(), bean.getHref());
					}else{
						MArticlePullGridMVPActivity.startMArticlePullGridMVPActivity(getActivity(), bean.getHref());
					}
					
				}
			}
			
		}
}
