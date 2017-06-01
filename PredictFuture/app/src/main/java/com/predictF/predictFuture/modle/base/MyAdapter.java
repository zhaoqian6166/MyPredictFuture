package com.predictF.predictFuture.modle.base;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.UrlBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 赵倩 on 2017/5/27.
 * <p/>
 * 类的用途：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {
    private ArrayList<UrlBean.Try> list;
    private LayoutInflater inflater;
    private Context context;

    public MyAdapter(ArrayList<UrlBean.Try> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.free_item, parent, false);
        MyViewHodler holder = new MyViewHodler(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        Uri uri = Uri.parse(list.get(position).image);
        holder.syItemImg.setImageURI(uri);
        //    holder.syItemPrice.setText(list.get(position).);
        holder.syItemTime.setText("时常："+ list.get(position).length + "分钟");
        holder.syItemTitle.setText(list.get(position).title);
        holder.syItemTitle2.setText(list.get(position).title2);
        //holder.syItemPrice.setText(list);

    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.sy_item_img)
        ImageView syItemImg;
        @BindView(R.id.sy_item_listen)
        ImageView syItemListen;
        @BindView(R.id.sy_item_title)
        TextView syItemTitle;
        @BindView(R.id.sy_item_title2)
        TextView syItemTitle2;
        @BindView(R.id.sy_item_teacherName)
        TextView syItemTeacherName;
        @BindView(R.id.sy_item_price)
        TextView syItemPrice;
        @BindView(R.id.sy_item_time)
        TextView syItemTime;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
