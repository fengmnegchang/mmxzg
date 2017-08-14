package com.open.mmxzg.mvp.base;
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-11下午2:56:06
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.mvp.base;
//
//import java.lang.ref.WeakReference;
//
///**
// *****************************************************************************************************************************************************************************
// * BasePresenter
// * @author :fengguangjing
// * @createTime:2017-8-11下午2:56:06
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class BasePresenter<T>{
//
//    /**
//     * View的弱引用
//     */
//    protected WeakReference<T> mViewRef;
//
//    /**
//     * 关联
//     */
//    public void attachView(T view) {
//        mViewRef = new WeakReference<T>(view);
//    }
//
//    /**
//     * 解除关联
//     */
//    public void detachView() {
//        if (mViewRef != null) {
//            mViewRef.clear();
//        }
//    }
//
//    /**
//     * 得到VIew
//     * @return
//     */
//    protected T getView() {
//        return mViewRef.get();
//    }
//}
