package com.bawei.week2_1.model;

import com.bawei.week2_1.contract.IMainContract;
import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.ListBean;
import com.bawei.week2_1.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 9:38
 */
public class MainModel implements IMainContract.IModel {
    @Override
    public void getMainData(String param,IModelMainCallback iModelMainCallback) {
        NetUtil.getInstance().getApi().getJson(1,5,param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        iModelMainCallback.onMainSuccess(listBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelMainCallback.onMainFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getTitleData(IModelTitleCallback iModelTitleCallback) {
        NetUtil.getInstance().getApi().getTitle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        iModelTitleCallback.onTitleSuccess(categoryBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelTitleCallback.onTitleFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
