///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-14下午3:29:48
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.model;
//
//import com.open.mmxzg.mvp.base.BasePresenter;
//import com.open.mmxzg.mvp.base.BaseView;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-14下午3:29:48
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public interface MArticlePullListContract {
//	/**
//	 * view层 负责view变化展示
//	 */
//	interface View<T> extends BaseView<Presenter> {
//		void onCallback(T result);
//		void bindEvent();
//		void initValues();
//	}
//	
//	/**
//	 * model逻辑跳转
//	 */
//	interface Presenter extends BasePresenter {
//		void doAsync();
//		void setPageNo(int pageNo);
//	}
//}
