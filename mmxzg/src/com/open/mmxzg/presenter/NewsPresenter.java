///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2017-8-11下午2:58:03
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.open.mmxzg.presenter;
//
//import java.util.List;
//
//import com.open.mmxzg.bean.m.News;
//import com.open.mmxzg.model.INewsModel;
//import com.open.mmxzg.model.NewsModelImpl;
//import com.open.mmxzg.mvp.base.BasePresenter;
//import com.open.mmxzg.view.INewsView;
//
///**
// *****************************************************************************************************************************************************************************
// * <p>
// * 中间人
// * girls view 和 model 的桥梁
// * @author :fengguangjing
// * @createTime:2017-8-11下午2:58:03
// * @version:4.2.4
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class NewsPresenter extends BasePresenter<INewsView> {
//
//    private INewsModel mGirlModel = new NewsModelImpl();
//
//    /**
//     * view 和model bind
//     */
//    public void fetch() {
//        //显示进度
//        getView().showLoading();
//        //获取数据
//        mGirlModel.loadloadNews(new INewsModel.GirlLoadOnListener() {
//            @Override
//            public void onComplete(List<News> girls) {
//                //显示数据
//                getView().showData(girls);
//                //隐藏进度
//                getView().hideLoading();
//            }
//        });
//    }
//}
