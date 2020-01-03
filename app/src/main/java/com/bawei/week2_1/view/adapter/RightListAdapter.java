package com.bawei.week2_1.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2_1.R;
import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.ListBean;
import com.bawei.week2_1.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 10:42
 */
public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyViewHolder> {
    private List<ListBean.ResultBean> list;

    public RightListAdapter(List<ListBean.ResultBean> result) {

        list = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.itemright, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListBean.ResultBean resultBean = list.get(position);
        holder.itName.setText(resultBean.getCommodityName());
        holder.itPrice.setText(resultBean.getPrice()+"");
        NetUtil.getInstance().getPhoto(resultBean.getMasterPic(),holder.itImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.it_img)
        ImageView itImg;
        @BindView(R.id.it_name)
        TextView itName;
        @BindView(R.id.it_price)
        TextView itPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
