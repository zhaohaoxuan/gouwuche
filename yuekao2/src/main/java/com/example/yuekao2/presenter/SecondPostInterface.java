package com.example.yuekao2.presenter;

import com.example.yuekao2.Model.RightBean;
import com.example.yuekao2.Model.SecondBean;

import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public interface SecondPostInterface {
    void success(List<SecondBean.DataBean> data);
    void error();
}
