package com.example.yuekao2.presenter;

import com.example.yuekao2.Model.LeftBean;
import com.example.yuekao2.net.OKHttPUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class BGetHttp {
    private static BGetHttp bgethttp;
    private BGetHttp() {}
    public static BGetHttp getinstance(){
        if (bgethttp == null){
            synchronized (BGetHttp.class){
                if (bgethttp == null){
                    bgethttp = new BGetHttp();
                }
            }
        }
        return bgethttp;
    }

    public void getHttp(String path, final JsonInterface mjsoninterface){
        OKHttPUtils.getinstance().doGet(path, new OkInterface() {
            @Override
            public void response(String json) {
                LeftBean bean = new Gson().fromJson(json,LeftBean.class);
                List<LeftBean.DataBean> data = bean.getData();
                if (mjsoninterface != null){
                    mjsoninterface.success(data);
                }
            }

            @Override
            public void failtrue(Exception e) {
                if (mjsoninterface != null){
                    mjsoninterface.error();
                }
            }
        });
    }

}
