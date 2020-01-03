package com.bawei.week2_1.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 11:35
 */
@Entity
public class LeftBean {
    private String leftjson;

    @Generated(hash = 1191005632)
    public LeftBean(String leftjson) {
        this.leftjson = leftjson;
    }

    @Generated(hash = 1558097993)
    public LeftBean() {
    }

    public String getLeftjson() {
        return this.leftjson;
    }

    public void setLeftjson(String leftjson) {
        this.leftjson = leftjson;
    }


}
