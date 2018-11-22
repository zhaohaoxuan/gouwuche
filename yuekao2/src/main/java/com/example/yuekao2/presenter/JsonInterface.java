package com.example.yuekao2.presenter;

import com.example.yuekao2.Model.LeftBean;

import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public interface JsonInterface {
    void success(List<LeftBean.DataBean> data);
    void error();
}
