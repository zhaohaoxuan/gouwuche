package com.example.zhaohaoxuan20181120.net;


import android.os.Handler;
import android.os.Looper;

import com.example.zhaohaoxuan20181120.presenter.onLoadData;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/11/20
 * author:赵豪轩(xuan)
 * function:
 */
public class OKHTTPUtils {
    private static OKHTTPUtils okhttputils;
    private final Handler mHandler;
    private final OkHttpClient mHttpClient;

    private OKHTTPUtils(){
        mHandler = new Handler(Looper.getMainLooper());
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }
    public static OKHTTPUtils getinstance(){
        if (okhttputils == null){
            synchronized (OKHTTPUtils.class){
                if (okhttputils == null){
                    okhttputils = new OKHTTPUtils();
                }
            }
        }
        return okhttputils;
    }
    public void doPost(String path , Map<String,String> map, final onLoadData monLoadData){

        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()){
            builder.add(key,map.get(key));
        }
        FormBody formBody = builder.build();
        Request build = new Request.Builder().url(path).post(formBody).build();

        mHttpClient.newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        if (monLoadData != null){
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    monLoadData.error(e);
                                }
                            });
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response != null && response.isSuccessful()){
                            final String string = response.body().string();
                            if (monLoadData != null){
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        monLoadData.success(string);
                                    }
                                });
                            }
                        }
                    }
                });
    }
    public void doGet(String path , final onLoadData monLoadData){

        Request build = new Request.Builder().url(path).build();

        mHttpClient.newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        if (monLoadData != null){
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                     monLoadData.error(e);
                                }
                            });
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response != null && response.isSuccessful()){
                            final String string = response.body().string();
                            if (monLoadData != null){
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        monLoadData.success(string);
                                    }
                                });
                            }
                        }
                    }
                });
    }

}
