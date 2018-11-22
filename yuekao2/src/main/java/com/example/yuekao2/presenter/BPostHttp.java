package com.example.yuekao2.presenter;

import com.example.yuekao2.Model.LeftBean;
import com.example.yuekao2.Model.RightBean;
import com.example.yuekao2.net.OKHttPUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class BPostHttp {
    private static BPostHttp bposthttp;
    private BPostHttp() {}
    public static BPostHttp getinstance(){
        if (bposthttp == null){
            synchronized (BGetHttp.class){
                if (bposthttp == null){
                    bposthttp = new BPostHttp();
                }
            }
        }
        return bposthttp;
    }

    public void postHttp(String path, Map<String,String> map, final JsonPostInterface mjsonPostInterface){

        OKHttPUtils.getinstance().doPost(path,map, new OkInterface() {
            @Override
            public void response(String json) {
                RightBean bean = new Gson().fromJson(json,RightBean.class);
                List<RightBean.DataBean> data = bean.getData();
                if (mjsonPostInterface != null){
                    mjsonPostInterface.success(data);
                }
            }

            @Override
            public void failtrue(Exception e) {
                if (mjsonPostInterface != null){
                    mjsonPostInterface.error();
                }
            }
        });
    }
}
