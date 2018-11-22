package com.example.zhaohaoxuan20181120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhaohaoxuan20181120.adapter.ExpandableAdapter;
import com.example.zhaohaoxuan20181120.bean.Bean;
import com.example.zhaohaoxuan20181120.net.Http;
import com.example.zhaohaoxuan20181120.presenter.onCartListChangeListener;
import com.example.zhaohaoxuan20181120.presenter.onList;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ExpandableListView expandable;
    private CheckBox check_all;
    private TextView summation;
    private Button account;
    private ExpandableAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();



        /*text.setOnClickListener(new onCustomClickListener() {
            @Override
            public void Click(int ss) {
                Toast.makeText(MainActivity.this, ss+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void SubToast() {
                Toast.makeText(MainActivity.this, "没有可以减的数据了", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private void initData() {
        Http.getHttp(new onList() {
            @Override
            public void success(List<Bean.DataBean> data) {

                Log.e("zhx",data.get(0).getSellerName()+"");
                mAdapter = new ExpandableAdapter(data);

                mAdapter.setOnCartListChangeListener(new onCartListChangeListener() {
                    @Override
                    public void onSellerCheckedChange(int groupPosition) {
                        boolean currentSellerAllProductSelected = mAdapter.isCurrentSellerAllProductSelected(groupPosition);
                        mAdapter.changeCurrentSellerAllProductsStatus(groupPosition,!currentSellerAllProductSelected);
                        mAdapter.notifyDataSetChanged();
                        refreshSelectedAndTotalPriceAndTotalNumber();

                    }

                    @Override
                    public void onProductCheckedChange(int groupPosition, int childPosition) {
                        //点击商品得checkbox
                        mAdapter.changeCurrentProductStatus(groupPosition,childPosition);
                        mAdapter.notifyDataSetChanged();
                        //B.刷新底部数据
                        refreshSelectedAndTotalPriceAndTotalNumber();
                    }

                    @Override
                    public void onProducNumberChange(int groupPosition, int childPosition, int num) {
                        //当加减被点击
                        mAdapter.changeCurrentProductNumber(groupPosition,childPosition,num);
                        mAdapter.notifyDataSetChanged();
                        //B.刷新底部数据
                        refreshSelectedAndTotalPriceAndTotalNumber();
                    }
                });
                expandable.setAdapter(mAdapter);
                for(int x=0; x<data.size(); x++){
                    expandable.expandGroup(x);
                }
                //B.刷新checkbox状态和总价和总数量
                refreshSelectedAndTotalPriceAndTotalNumber();
            }



            @Override
            public void error() {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        expandable = (ExpandableListView) findViewById(R.id.expandable);
        check_all = (CheckBox) findViewById(R.id.check_all);
        summation = (TextView) findViewById(R.id.summation);
        account = (Button) findViewById(R.id.account);

        check_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_all:
                //底部全选按钮
                //时候所有得商品都被选中
                boolean allProductsSelected = mAdapter.isAllProductsSelected();
                mAdapter.changeAllProductStatus(!allProductsSelected);
                mAdapter.notifyDataSetChanged();
                //刷新底部数据
                refreshSelectedAndTotalPriceAndTotalNumber();
                Toast.makeText(this, "全选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //B.刷新checkbox状态和总价和总数量
    private void refreshSelectedAndTotalPriceAndTotalNumber() {
        //去判断是否所有得商品都被选中
        boolean allProductsSelected = mAdapter.isAllProductsSelected();
        //设置给全选checkBox
        check_all.setChecked(allProductsSelected);

        //计算总价
        float totalPrice = mAdapter.calculateTotalPrice();
        summation.setText("总价 " + totalPrice);

        //计算总数量
        int totalNumber = mAdapter.calculateTotalNumber();
        account.setText("去结算(" + totalNumber + ")");
    }
}
