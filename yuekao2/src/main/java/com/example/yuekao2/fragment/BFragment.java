package com.example.yuekao2.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekao2.Model.LeftBean;
import com.example.yuekao2.Model.RightBean;
import com.example.yuekao2.R;
import com.example.yuekao2.SecondActivity;
import com.example.yuekao2.adapter.LeftRecyclerViewAdapter;
import com.example.yuekao2.adapter.RightRecyclerViewAdapter;
import com.example.yuekao2.presenter.BGetHttp;
import com.example.yuekao2.presenter.BPostHttp;
import com.example.yuekao2.presenter.GetCid;
import com.example.yuekao2.presenter.GetPscid;
import com.example.yuekao2.presenter.JsonInterface;
import com.example.yuekao2.presenter.JsonPostInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends Fragment {
    private RecyclerView left_recyclerview;
    private LinearLayout linear;
    String left_path="http://www.zhaoapi.cn/product/getCatagory?source=android";
    String right_path="http://www.zhaoapi.cn/product/getProductCatagory?source=android";
    private LeftRecyclerViewAdapter mLeftRecyclerViewAdapter;
    String ccid = "1";
    private RecyclerView mRightRecyclerView;
    private RightRecyclerViewAdapter mRightRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initData() {
        BGetHttp.getinstance().getHttp(left_path, new JsonInterface() {
            @Override
            public void success(List<LeftBean.DataBean> data) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
                left_recyclerview.setLayoutManager(linearLayoutManager);
                mLeftRecyclerViewAdapter = new LeftRecyclerViewAdapter(getActivity(),data);
                left_recyclerview.setAdapter(mLeftRecyclerViewAdapter);
                mLeftRecyclerViewAdapter.setGetCid(new GetCid() {
                    @Override
                    public void setCid(String cid) {
                        ccid = cid;
                        Log.e("zhx",cid);
                        initRight();
                    }
                });
            }

            @Override
            public void error() {
                Toast.makeText(getActivity(), "网络请求失败！！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRight() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("cid",ccid);
        BPostHttp.getinstance().postHttp(right_path, map, new JsonPostInterface() {
            @Override
            public void success(List<RightBean.DataBean> data) {
//                Log.e("zhx",data.get(0).getName());
                linear.removeAllViews();
                if (data.size() != 0){
                    for (int i = 0 ; i < data.size() ; i++){
                        List<RightBean.DataBean.ListBean> list = data.get(i).getList();
                        TextView textView = new TextView(getActivity());
                        textView.setText(data.get(i).getName());
                        textView.setTextColor(Color.RED);
                        textView.setTextSize(30);
                        mRightRecyclerView = new RecyclerView(getActivity());
                        mRightRecyclerViewAdapter = new RightRecyclerViewAdapter(getActivity(), list);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                        mRightRecyclerView.setLayoutManager(gridLayoutManager);
                        mRightRecyclerView.setAdapter(mRightRecyclerViewAdapter);
                        mRightRecyclerViewAdapter.setGetPscid(new GetPscid() {
                            @Override
                            public void setPscid(String pscid) {
                                Intent intent = new Intent(getActivity(), SecondActivity.class);
                                intent.putExtra("pscid",pscid);
                                getActivity().startActivity(intent);
                            }
                        });
                        mRightRecyclerViewAdapter.notifyDataSetChanged();
                        linear.addView(textView);
                        linear.addView(mRightRecyclerView);
                    }
                }

            }

            @Override
            public void error() {
                Toast.makeText(getActivity(), "网络请求失败！！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        left_recyclerview = (RecyclerView) view.findViewById(R.id.left_recyclerview);
        linear = (LinearLayout) view.findViewById(R.id.linear);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
