package com.example.yuekao2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuekao2.Model.RightBean;
import com.example.yuekao2.R;
import com.example.yuekao2.presenter.GetPscid;

import java.util.List;

/**
 * date:2018/11/22
 * author:赵豪轩(xuan)
 * function:
 */
public class RightRecyclerViewAdapter extends RecyclerView.Adapter<RightRecyclerViewAdapter.MViewHolder> {
    List<RightBean.DataBean.ListBean> list;
    Context context;

    public RightRecyclerViewAdapter(Context context, List<RightBean.DataBean.ListBean> data) {
        this.context = context;
        list = data;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.right_item, null);
        MViewHolder mViewHolder = new MViewHolder(inflate);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MViewHolder mViewHolder, final int i) {
        mViewHolder.right_text.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(mViewHolder.right_img);
        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGetPscid != null){
                    mGetPscid.setPscid(list.get(i).getPscid()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        TextView right_text;
        ImageView right_img;
        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            right_text = itemView.findViewById(R.id.right_text);
            right_img = itemView.findViewById(R.id.right_img);
        }
    }
    private GetPscid mGetPscid;

    public void setGetPscid(GetPscid getPscid) {
        mGetPscid = getPscid;
    }
}
