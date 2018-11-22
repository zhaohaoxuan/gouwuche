package com.example.yuekao2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.yuekao2.adapter.FragmentAdapter;
import com.example.yuekao2.fragment.AFragment;
import com.example.yuekao2.fragment.BFragment;
import com.example.yuekao2.fragment.CFragment;
import com.example.yuekao2.fragment.DFragment;
import com.example.yuekao2.fragment.EFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private TabLayout tab;
    List<Fragment> list;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new AFragment());
        list.add(new BFragment());
        list.add(new CFragment());
        list.add(new DFragment());
        list.add(new EFragment());
        mFragmentAdapter = new FragmentAdapter(getFragmentManager());
        mFragmentAdapter.setData(list);
        tab.setupWithViewPager(viewpager);
        viewpager.setAdapter(mFragmentAdapter);

    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
