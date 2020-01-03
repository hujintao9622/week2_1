package com.bawei.week2_1.base;

import com.bawei.week2_1.contract.IMainContract;

import java.lang.ref.WeakReference;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 10:26
 */
public abstract class BasePresenter <V extends IMainContract.IView> implements IMainContract.IPresenter {
   private WeakReference<V> weakReference;


    public void attach(V view) {
        weakReference = new WeakReference<>(view);
    }
    public void detach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }

    public BasePresenter() {
        initModel();
    }
    public V getView(){
        return weakReference.get();
    }
    protected abstract void initModel();
}
