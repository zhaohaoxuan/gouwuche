package com.example.yuekao2.presenter;

import com.example.yuekao2.Model.RightBean;
import com.example.yuekao2.Model.SecondBean;
import com.example.yuekao2.net.OKHttPUtils;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:n
 */
public class SecondPostHttp {
    private static SecondPostHttp secondposthttp;
    private SecondPostHttp() {}
    public static SecondPostHttp getinstance(){
        if (secondposthttp == null){
            synchronized (SecondPostHttp.class){
                if (secondposthttp == null){
                    secondposthttp = new SecondPostHttp();
                }
            }
        }
        return secondposthttp;
    }

    public void postHttp(String path, Map<String,String> map, final  SecondPostInterface   msecondPostInterface){

        OKHttPUtils.getinstance().doPost(path,map, new OkInterface() {
            @Override
            public void response(String json) {
                SecondBean bean = new Gson().fromJson(json,SecondBean.class);
                List<SecondBean.DataBean> data = bean.getData();

                if (msecondPostInterface != null){
                    msecondPostInterface.success(data);
                }
            }

            @Override
            public void failtrue(Exception e) {
                if (msecondPostInterface != null){
                    msecondPostInterface.error();
                }
            }
        });
    }

}
