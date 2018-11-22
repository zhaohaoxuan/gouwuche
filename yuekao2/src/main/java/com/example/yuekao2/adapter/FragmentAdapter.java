package com.example.yuekao2.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    /**
     * @param fm
     * @deprecated
     */
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    public void setData(List<Fragment> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "首页";
            case 1:
                return "分类";
            case 2:
                return "发现";
            case 3:
                return "购物车";
            case 4:
                return "我的";

        }
        return null;
    }

    /**
     * @param i
     * @deprecated
     */
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = list.get(i);


        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
