package com.bawei.week2_1.widget;

import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 9:53
 */
public interface Api {
    @GET("small/commodity/v1/findCategory")
    Observable<CategoryBean> getTitle();
    @GET("small/commodity/v1/findCommodityByCategory")
    Observable<ListBean> getJson(@Query("page")int page,@Query("count")int count,@Query("categoryId")String cate);
}
