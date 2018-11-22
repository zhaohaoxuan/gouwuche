package com.example.yuekao2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuekao2.Model.LeftBean;
import com.example.yuekao2.Model.SecondBean;
import com.example.yuekao2.R;
import com.example.yuekao2.presenter.SecondPostInterface;

import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class SecondRecyclerViewAdapter extends RecyclerView.Adapter<SecondRecyclerViewAdapter.MViewHolder> {
    List<SecondBean.DataBean> list;
    Context context;

    public SecondRecyclerViewAdapter(Context context, List<SecondBean.DataBean> data) {
        this.context = (Context) context;
        list = data;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.second_item, null);
        MViewHolder mViewHolder = new MViewHolder(inflate);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MViewHolder mViewHolder, final int i) {
        mViewHolder.second_item_title.setText(list.get(i).getTitle());
        mViewHolder.second_item_price.setText(list.get(i).getPrice()+"");
        String images = list.get(i).getImages();
        String[] split = images.split("!");
        Glide.with(context).load(split[0]).into(mViewHolder.second_item_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        TextView second_item_title;
        TextView second_item_price;
        ImageView second_item_img;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            second_item_title = itemView.findViewById(R.id.second_item_title);
            second_item_price = itemView.findViewById(R.id.second_item_price);
            second_item_img = itemView.findViewById(R.id.second_item_img);
        }
    }
}
