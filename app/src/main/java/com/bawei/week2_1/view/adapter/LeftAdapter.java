package com.bawei.week2_1.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2_1.R;
import com.bawei.week2_1.model.bean.CategoryBean;
import com.bawei.week2_1.model.bean.LeftBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/3 0003 上午 10:42
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {

    private List<CategoryBean.ResultBean.SecondCategoryVoBean> list;

    public LeftAdapter(List<CategoryBean.ResultBean.SecondCategoryVoBean> categoryVo) {

        list = categoryVo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.itemleft, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryBean.ResultBean.SecondCategoryVoBean secondCategoryVoBean = list.get(position);
        holder.itName.setText(secondCategoryVoBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.it_name)
    TextView itName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
