package com.chenmaunion.app.cmdriver.bean.order;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/12.
 * 用户保存没有网络情况下的位置点的实体类
 */
public class LocationContineTime implements Serializable {

    public String id;
    public String userIdx;
    public Double CORDINATEX;
    public Double CORDINATEY;
    public String ADDRESS;
    public String TIME;



    @Override
    public String toString() {
        return "CORDINATEX:"+CORDINATEX+"\t,CORDINATEY"+CORDINATEY;
    }

}

