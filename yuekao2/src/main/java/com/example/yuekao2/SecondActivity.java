package com.example.yuekao2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.yuekao2.Model.SecondBean;
import com.example.yuekao2.adapter.SecondRecyclerViewAdapter;
import com.example.yuekao2.presenter.SecondPostHttp;
import com.example.yuekao2.presenter.SecondPostInterface;

import java.util.HashMap;
import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class SecondActivity extends AppCompatActivity {
    private String pscid = "1";
    private String second_path = "http://www.zhaoapi.cn/product/getProducts?source=android";
    private RecyclerView second_recyclerview;
    private SecondRecyclerViewAdapter mSecondRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        pscid = getIntent().getStringExtra("pscid");
        initData();



    }
    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pscid",pscid);
        SecondPostHttp.getinstance().postHttp(second_path, map, new SecondPostInterface() {
            @Override
            public void success(List<SecondBean.DataBean> data) {

                if (data.size() != 0){
                    Log.e("zhx","zhx");
                    Log.e("zhx",data.get(0).getTitle());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SecondActivity.this, LinearLayoutManager.VERTICAL, false);
                    second_recyclerview.setLayoutManager(linearLayoutManager);
                    mSecondRecyclerViewAdapter = new SecondRecyclerViewAdapter(SecondActivity.this, data);
                    second_recyclerview.setAdapter(mSecondRecyclerViewAdapter);
                }
            }

            @Override
            public void error() {

            }
        });

    }

    private void initView() {
        second_recyclerview = (RecyclerView) findViewById(R.id.second_recyclerview);
    }
}
