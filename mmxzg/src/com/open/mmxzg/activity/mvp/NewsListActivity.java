/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-11下午2:59:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.activity.mvp;

import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.open.mmxzg.R;
import com.open.mmxzg.adapter.m.NewsAdapter;
import com.open.mmxzg.bean.m.News;
import com.open.mmxzg.mvp.base.BaseActivity;
import com.open.mmxzg.presenter.NewsPresenter;
import com.open.mmxzg.view.INewsView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-11下午2:59:25
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class NewsListActivity extends BaseActivity<INewsView, NewsPresenter> implements INewsView {
    private ListView listView;
    private NewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_news_list);

        listView = (ListView) findViewById(R.id.pull_refresh_list);
        //view 和 model绑定
        mPresenter.fetch();
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void showData(List<News> news) {
        adapter = new NewsAdapter(NewsListActivity.this, news);
        listView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "正在加载中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }
}
