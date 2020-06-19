package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.BranchBean;

import java.util.List;

import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class BranchModelAdapter extends RecyclerView.Adapter<BranchModelAdapter.ViewHolder> {

    private List<BranchBean> datas;
    private BaseRecycleView.OnItemClickListener<BranchBean> onItemClickListener;
    public void setDatas(List<BranchBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(BaseRecycleView.OnItemClickListener<BranchBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_branchmodel, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BranchBean branchBean = datas.get(i);
        viewHolder.rv_name.setText(branchBean.getPATROLNAME());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,branchBean,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView rv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_name = itemView.findViewById(R.id.rv_name);
        }
    }
}
