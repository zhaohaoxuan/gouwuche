package com.example.zhaohaoxuan20181120.net;

import com.example.zhaohaoxuan20181120.bean.Bean;
import com.example.zhaohaoxuan20181120.presenter.onList;
import com.example.zhaohaoxuan20181120.presenter.onLoadData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date:2018/11/20
 * author:赵豪轩(xuan)
 * function:
 */
public class Http {
    private static Http http;
    private static String path="http://www.zhaoapi.cn/product/getCarts";
    private Http(){}
    private static Http getinstance(){
        if (http == null){
            synchronized (OKHTTPUtils.class){
                if (http == null){
                    http = new Http();
                }
            }
        }
        return http;
    }
    public static void getHttp(final onList monList){
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","71");
        OKHTTPUtils.getinstance().doPost(path,map,new onLoadData() {
            @Override
            public void success(String ss) {
                Bean bean = new Gson().fromJson(ss,Bean.class);
                List<Bean.DataBean> data = bean.getData();
                monList.success(data);
            }
            @Override
            public void error(Exception e) {
                monList.error();
            }
        });
    }

}
