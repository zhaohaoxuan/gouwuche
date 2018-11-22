package com.example.zhaohaoxuan20181120.presenter;

/**
 * date:2018/11/20
 * author:赵豪轩(xuan)
 * function:
 */
public interface onCartListChangeListener {
//* 当商家的checkBox点击时回调
    void onSellerCheckedChange(int groupPosition);
// * 当点击子条目商品的CheckBox回调
    void onProductCheckedChange(int groupPosition,int childPosition);
//  * 当点击加减按钮的回调
    void onProducNumberChange(int groupPosition,int childPosition,int num);
}
