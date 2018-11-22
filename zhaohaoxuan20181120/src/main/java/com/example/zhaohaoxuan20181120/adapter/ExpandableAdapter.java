package com.example.zhaohaoxuan20181120.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhaohaoxuan20181120.R;
import com.example.zhaohaoxuan20181120.bean.Bean;
import com.example.zhaohaoxuan20181120.custom.SubAddView;
import com.example.zhaohaoxuan20181120.presenter.onCartListChangeListener;
import com.example.zhaohaoxuan20181120.presenter.onCustomClickListener;

import java.util.List;

/**
 * date:2018/11/20
 * author:赵豪轩(xuan)
 * function:
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    List<Bean.DataBean> list;

    public ExpandableAdapter(List<Bean.DataBean> data) {
        this.list = data;
    }

    @Override
    public int getGroupCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size() == 0 ? 0 : list.get(i).getList().size();
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_group, null);
            groupViewHolder = new GroupViewHolder(view);
            view.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }

        groupViewHolder.group_text.setText(list.get(i).getSellerName());
        //D.根据当前商家的所有商品,确定商家的CheckBox是否选中
        boolean currentSellerAllProductSelected = isCurrentSellerAllProductSelected(i);
        //D.根据这个boolean判断是否选中
        groupViewHolder.group_checkbox.setChecked(currentSellerAllProductSelected);

        groupViewHolder.group_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCartListChangeListener != null){
                    mOnCartListChangeListener.onSellerCheckedChange(i);
                }
            }
        });

        return view;

    }

    public static class GroupViewHolder {
        public CheckBox group_checkbox;
        public TextView group_text;

        public GroupViewHolder(View rootView) {
            this.group_checkbox = (CheckBox) rootView.findViewById(R.id.group_checkbox);
            this.group_text = (TextView) rootView.findViewById(R.id.group_text);
        }

    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, final ViewGroup viewGroup) {
        List<Bean.DataBean.ListBean> childs = this.list.get(i).getList();
        ChildViewHolder childViewHolder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_child, null);
            childViewHolder = new ChildViewHolder(view);
            view.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        String s = childs.get(i1).getImages();
        String[] split = s.split("!");
        Glide.with(viewGroup).load(split[0]).into(childViewHolder.chila_icon);
        childViewHolder.child_title.setText(childs.get(i1).getTitle());
        childViewHolder.child_price.setText(childs.get(i1).getPrice()+"");
        childViewHolder.child_checkbox.setChecked(childs.get(i1).getSelected() == 1);
        childViewHolder.child_custom_sub.setText(childs.get(i1).getNum());

        childViewHolder.child_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCartListChangeListener != null){
                    mOnCartListChangeListener.onProductCheckedChange(i,i1);
                }
            }
        });
        childViewHolder.child_custom_sub.setOnClickListener(new onCustomClickListener() {
            @Override
            public void Click(int ss) {
                if (mOnCartListChangeListener != null){
                    mOnCartListChangeListener.onProducNumberChange(i,i1,ss);
                }
            }

            @Override
            public void SubToast() {

            }
        });
        return view;
    }
    public static class ChildViewHolder {
        public CheckBox child_checkbox;
        public ImageView chila_icon;
        public TextView child_title;
        public TextView child_price;
        public SubAddView child_custom_sub;

        public ChildViewHolder(View rootView) {
            this.child_checkbox = (CheckBox) rootView.findViewById(R.id.child_checkbox);
            this.chila_icon = (ImageView) rootView.findViewById(R.id.chila_icon);
            this.child_title = (TextView) rootView.findViewById(R.id.child_title);
            this.child_price = (TextView) rootView.findViewById(R.id.child_price);
            this.child_custom_sub = (SubAddView) rootView.findViewById(R.id.child_custom_sub);
        }

    }

    ///////////////////////////////////////购物车特有的一些方法///////////////////////////
    //判断当前组的商品是否被选中（一种是所有的都被选中,一种是没有所有的都选中）
    public boolean isCurrentSellerAllProductSelected(int groupposition){
        Bean.DataBean dataBean = list.get(groupposition);
        List<Bean.DataBean.ListBean> listaa= dataBean.getList();
        for (Bean.DataBean.ListBean listbean : listaa){
            if (listbean.getSelected() == 0){
                return false;
            }
        }
        return true;
    }

    //底部有一个全选按钮的逻辑,也要判断,不过范围更大,是所有的商品是否被选中(一种是所有的都被选中,一种是没有所有的都选中)
    public boolean isAllProductsSelected(){
        for (int i = 0 ; i<list.size() ; i++){
            Bean.DataBean dataBean = list.get(i);
            List<Bean.DataBean.ListBean> listaa = dataBean.getList();
            for (int y = 0; y<listaa.size();y++){
                if (listaa.get(y).getSelected() == 0){
                    return false;
                }
            }
        }
        return true;
    }
    //计算商品的总量
    public int calculateTotalNumber(){
        int num = 0;
        for (int i = 0 ; i<list.size() ; i++){
            Bean.DataBean dataBean = list.get(i);
            List<Bean.DataBean.ListBean> listaa = dataBean.getList();
            for (int y = 0; y<listaa.size();y++){
                if (listaa.get(y).getSelected() == 1){
                    int num1 = listaa.get(y).getNum();
                    num += num1;
                }
            }
        }
        return num;
    }
    //计算商品的总价
    public float calculateTotalPrice(){
        float prices = 0;
        for (int i = 0 ; i<list.size() ; i++){
            Bean.DataBean dataBean = list.get(i);
            List<Bean.DataBean.ListBean> listaa = dataBean.getList();
            for (int y = 0; y<listaa.size();y++){
                if (listaa.get(y).getSelected() == 1){
                    int num1 = listaa.get(y).getNum();
                    float price = listaa.get(y).getPrice();
                    prices += num1*price;
                }
            }
        }
        return prices;
    }

    ///////////////////////////////////////C.根据用户的选择,改变选框里的状态///////////////////////////////////
    //C.当商品组的全选框点击时,更新所有商品的状态
    public void changeCurrentSellerAllProductsStatus(int groupPosition,boolean isselected){
        Bean.DataBean dataBean = list.get(groupPosition);
        List<Bean.DataBean.ListBean> listaa = dataBean.getList();
        for (int i = 0 ; i <listaa.size() ; i++){
            Bean.DataBean.ListBean listBean = listaa.get(i);
            listBean.setSelected(isselected ? 1 : 0);
        }
    }
    //C.当商品子条目全选框点击时,更新所有商品的状态
    public void changeCurrentProductStatus(int groupPosition,int childPosition){
        Bean.DataBean dataBean = list.get(groupPosition);
        List<Bean.DataBean.ListBean> listaa = dataBean.getList();
        Bean.DataBean.ListBean listBean = listaa.get(childPosition);
        //不太明白
        listBean.setSelected(listBean.getSelected()==0?1:0);
    }
    //C.设置所有商品的状态
    public void changeAllProductStatus(boolean selected){
        for (int i = 0 ; i<list.size() ; i++){
            Bean.DataBean dataBean = list.get(i);
            List<Bean.DataBean.ListBean> listaa = dataBean.getList();
            for (int y = 0; y<listaa.size();y++){
                listaa.get(y).setSelected(selected ? 1 :0);
            }
        }
    }
    //C.当加减器被点击时,调用,改变里面当前商品的数量  参数1定位那个商家
    // 参数2定位哪个商品  参数3定位改变具体的数量是多少
    public void changeCurrentProductNumber(int groupPosition , int childPosition ,int num){
        Bean.DataBean dataBean = list.get(groupPosition);
        List<Bean.DataBean.ListBean> listss = dataBean.getList();
        Bean.DataBean.ListBean listBean = listss.get(childPosition);
        listBean.setNum(num);
    }


    onCartListChangeListener mOnCartListChangeListener;

    public void setOnCartListChangeListener(onCartListChangeListener onCartListChangeListener) {
        mOnCartListChangeListener = onCartListChangeListener;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



}
