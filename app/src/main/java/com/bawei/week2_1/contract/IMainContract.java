package com.bawei.week2_1.contract;

import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.ListBean;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 10:02
 */
public interface IMainContract {
    interface IView{
        void onMainSuccess(ListBean listBean);
        void onMainFailure(Throwable throwable);
        void onTitleSuccess(CategoryBean categoryBean);
        void onTitleFailure(Throwable throwable);
    }
    interface IPresenter{
        void getMainData(String param);
        void getTitleData();
    }
    interface IModel{
        void getMainData(String param,IModelMainCallback iModelMainCallback);
        interface IModelMainCallback{
            void onMainSuccess(ListBean listBean);
            void onMainFailure(Throwable throwable);
        }

        void getTitleData(IModelTitleCallback iModelTitleCallback);
        interface IModelTitleCallback{
            void onTitleSuccess(CategoryBean categoryBean);
            void onTitleFailure(Throwable throwable);
        }
    }
}
