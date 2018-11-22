package com.example.yuekao2.presenter;

import com.example.yuekao2.Model.RightBean;

import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public interface JsonPostInterface {
    void success(List<RightBean.DataBean> data);
    void error();
}
