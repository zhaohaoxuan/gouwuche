package com.example.ddd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    String path = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private TextView text;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
        text = (TextView) findViewById(R.id.text);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                OKHTTPUtils okhttpUtils = OKHTTPUtils.getinstance();
                okhttpUtils.doGet(path, new onLoadData() {
                    @Override
                    public void success(String json) {
                        text.setText(json);
                    }

                    @Override
                    public void error(Exception e) {

                    }
                });
                break;
            case R.id.btn2:
                OKHTTPUtils getinstance = OKHTTPUtils.getinstance();
                Map<String, String> map = new HashMap<>();
                map.put("source","1.0.1");
                getinstance.doPost(path,map, new onLoadData() {
                    @Override
                    public void success(String json) {
                        text.setText(json);
                    }

                    @Override
                    public void error(Exception e) {

                    }
                });
                break;
        }
    }
}
