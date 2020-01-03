package com.bawei.week2_1.presenter;

import com.bawei.week2_1.base.BasePresenter;
import com.bawei.week2_1.contract.IMainContract;
import com.bawei.week2_1.model.MainModel;
import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.ListBean;

import retrofit2.http.POST;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 10:31
 */
public class MainPresenter extends BasePresenter {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }

    @Override
    public void getMainData(String param) {
        mainModel.getMainData(param, new IMainContract.IModel.IModelMainCallback() {
            @Override
            public void onMainSuccess(ListBean listBean) {
                getView().onMainSuccess(listBean);
            }

            @Override
            public void onMainFailure(Throwable throwable) {
                getView().onMainFailure(throwable);
            }
        });
    }

    @Override
    public void getTitleData() {
        mainModel.getTitleData(new IMainContract.IModel.IModelTitleCallback() {
            @Override
            public void onTitleSuccess(CategoryBean categoryBean) {
                getView().onTitleSuccess(categoryBean);
            }

            @Override
            public void onTitleFailure(Throwable throwable) {
                getView().onTitleFailure(throwable);
            }
        });
    }
}
