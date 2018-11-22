package com.example.zhaohaoxuan20181120.presenter;

import com.example.zhaohaoxuan20181120.bean.Bean;

import java.util.List;

/**
 * date:2018/11/20
 * author:赵豪轩(xuan)
 * function:
 */
public interface onList {
    void success(List<Bean.DataBean> data);
    void error();
}
