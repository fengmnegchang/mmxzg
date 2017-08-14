///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-11下午2:56:50
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.mvp.base;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
///**
// *****************************************************************************************************************************************************************************
// * BaseActivity
// * @author :fengguangjing
// * @createTime:2017-8-11下午2:56:50
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
//
//    protected T mPresenter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //创建presenter
//        mPresenter = createPresenter();
//        //绑定VIew
//        mPresenter.attachView((V) this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //解除绑定
//        mPresenter.detachView();
//    }
//
//    //创建Presenter
//    protected abstract T createPresenter();
//}
