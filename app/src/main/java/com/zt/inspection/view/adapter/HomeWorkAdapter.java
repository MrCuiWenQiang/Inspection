package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.view.HomeWorkBean;

import java.util.List;

import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.HomeWorkViewHolder> {

    private List<HomeWorkBean> datas;

    private BaseRecycleView.OnItemClickListener<HomeWorkBean> onItemClickListener;

    public void setOnItemClickListener(BaseRecycleView.OnItemClickListener<HomeWorkBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setDatas(List<HomeWorkBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeWorkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_homework, viewGroup, false);
        return new HomeWorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkViewHolder homeWorkViewHolder, int i) {
        HomeWorkBean workBean = datas.get(i);
        homeWorkViewHolder.tvTitle.setText(workBean.getTitle());
        homeWorkViewHolder.tvHint.setText(workBean.getHint());
        homeWorkViewHolder.imTab.setBackgroundResource(workBean.getIcon());
        homeWorkViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,workBean,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected class HomeWorkViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvHint;
        ImageView imTab;

        public HomeWorkViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvHint = itemView.findViewById(R.id.tv_hint);
            imTab = itemView.findViewById(R.id.im_tab);
        }
    }
}
