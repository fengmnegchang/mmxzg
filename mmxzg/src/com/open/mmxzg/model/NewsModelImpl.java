/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-11下午2:53:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.open.mmxzg.model;

import java.util.ArrayList;
import java.util.List;

import com.open.mmxzg.bean.m.News;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-8-11下午2:53:22
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class NewsModelImpl implements INewsModel {
    //加载数据
    @Override
    public void loadloadNews(GirlLoadOnListener listener) {
        List<News> dataList = new ArrayList<News>();
        for (int i = 0; i < 15; i++) {
            News g = new News();
            //内容随便敲的，不重要
            g.setName("案件发is的肌肤" + i);
            g.setContent("撒娇佛is戴假发老司机放假啊考虑实际得分i11" + i);
            dataList.add(g);
        }

        listener.onComplete(dataList);
    }
}
