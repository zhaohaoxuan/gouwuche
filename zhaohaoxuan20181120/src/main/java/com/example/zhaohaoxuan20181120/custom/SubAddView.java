package com.example.zhaohaoxuan20181120.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhaohaoxuan20181120.R;
import com.example.zhaohaoxuan20181120.presenter.onCustomClickListener;

/**
 * date:2018/11/20
 * author:赵豪轩(xuan)
 * function:
 */
public class SubAddView extends LinearLayout implements View.OnClickListener {

    private TextView custom_sub;
    private TextView custom_text;
    private TextView custom_add;
    private int number = 1;
    private onCustomClickListener mOnCustomClickListener;

    public SubAddView(Context context) {
        this(context, null);
    }

    public SubAddView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubAddView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        View view = View.inflate(context, R.layout.sub_and_view, this);
        custom_sub = view.findViewById(R.id.custom_sub);
        custom_text = view.findViewById(R.id.custom_text);
        custom_add = view.findViewById(R.id.custom_add);

        custom_add.setOnClickListener(this);
        custom_sub.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.custom_add:
                number++;
                setString(number);
                if (mOnCustomClickListener != null){
                    mOnCustomClickListener.Click(number);
                }
                break;
            case R.id.custom_sub:
                if (number > 1){
                    number--;
                    setString(number);
                    if (mOnCustomClickListener != null){
                        mOnCustomClickListener.Click(number);
                    }
                }else{
                    if (mOnCustomClickListener != null){
                        mOnCustomClickListener.SubToast();
                    }
                }
                break;
        }
    }
    public void setString(int ss){
        custom_text.setText(ss+"");
    }
    public void setOnClickListener(onCustomClickListener onClickListener){
        mOnCustomClickListener = onClickListener;
    }
    public void setText(int num){
        this.number = num;
        custom_text.setText(num+"");
    }
    public int getText(){
        return number;
    }

}
