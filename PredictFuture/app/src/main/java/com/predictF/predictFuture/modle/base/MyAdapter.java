package com.predictF.predictFuture.modle.base;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private ItemClick itemClick;


    public interface ItemClick{
        void onItemClick(String object_id);
    }
    //对外提供的点击方法
    public void setOnItemClickListner(ItemClick itemClick){
        this.itemClick=itemClick;
    }

    public MyAdapter(ArrayList<UrlBean.Try> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.free_item, parent, false);
        final MyViewHodler holder = new MyViewHodler(view);

        if (itemClick!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     int position = holder.getLayoutPosition();
                    Log.d("position",position+"-");
                    itemClick.onItemClick(list.get(position).object_id);

                }
            });
        }

        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
       // Uri uri = Uri.parse(list.get(position).image);
        Glide.with(context).load(list.get(position).image).into(holder.syItemImg);
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
