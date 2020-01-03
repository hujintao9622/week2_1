package com.bawei.week2_1.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 11:36
 */
@Entity
public class RightBean {
    private String rightjson;

    @Generated(hash = 393913379)
    public RightBean(String rightjson) {
        this.rightjson = rightjson;
    }

    @Generated(hash = 1817483323)
    public RightBean() {
    }

    public String getRightjson() {
        return this.rightjson;
    }

    public void setRightjson(String rightjson) {
        this.rightjson = rightjson;
    }


}
