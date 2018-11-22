package com.example.yuekao2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuekao2.Model.LeftBean;
import com.example.yuekao2.R;
import com.example.yuekao2.presenter.GetCid;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class LeftRecyclerViewAdapter extends RecyclerView.Adapter<LeftRecyclerViewAdapter.MViewHolder> {

    List<LeftBean.DataBean> list;
    Context context;

    public LeftRecyclerViewAdapter(Context context, List<LeftBean.DataBean> data) {
        this.context = context;
        list = data;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.left_item, null);
        MViewHolder mViewHolder = new MViewHolder(inflate);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MViewHolder mViewHolder, final int i) {
        mViewHolder.left_text.setText(list.get(i).getName());
        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGetCid != null){
                    mGetCid.setCid(list.get(i).getCid()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        TextView left_text;
        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            left_text = itemView.findViewById(R.id.left_text);
        }
    }
    private GetCid mGetCid;
    public void setGetCid(GetCid getCid){
        mGetCid = getCid;
    }
}
