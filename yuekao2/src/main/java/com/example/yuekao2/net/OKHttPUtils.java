package com.example.yuekao2.net;

import android.os.Handler;
import android.os.Looper;

import com.example.yuekao2.presenter.OkInterface;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class OKHttPUtils {
    private static OKHttPUtils okhttputils;
    private final Handler mHandler;
    private final OkHttpClient mOkHttpClient;
    private OKHttPUtils(){
        mHandler = new Handler(Looper.getMainLooper());
        mOkHttpClient = new OkHttpClient.Builder().build();
    }
    public static OKHttPUtils getinstance(){
        if (okhttputils == null){
            synchronized (OKHttPUtils.class){
                if (okhttputils == null){
                    okhttputils=new OKHttPUtils();
                }
            }
        }
        return okhttputils;
    }
    public void doPost(String path, Map<String,String> map, final OkInterface mokinterface){
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null){
            for (String key : map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        FormBody formBody = builder.build();
        Request build = new Request.Builder().url(path).post(formBody).build();
        mOkHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (mokinterface != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mokinterface.failtrue(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()){
                    final String string = response.body().string();
                    if (mokinterface != null){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mokinterface.response(string);
                            }
                        });
                    }
                }
            }
        });
    }

    public void doGet(String path, final OkInterface mokinterface){
        Request build = new Request.Builder().url(path).build();
        mOkHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (mokinterface != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mokinterface.failtrue(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()){
                    final String string = response.body().string();
                    if (mokinterface != null){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mokinterface.response(string);
                            }
                        });
                    }
                }
            }
        });
    }

}
