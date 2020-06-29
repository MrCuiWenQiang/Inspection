package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.PatrolSectionListBean;

import java.util.List;

import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class PatrolSectionListAdapter extends RecyclerView.Adapter<PatrolSectionListAdapter.PatrolSectioViewHolder> {
    private List<PatrolSectionListBean> datas;
    private BaseRecycleView.OnItemClickListener<PatrolSectionListBean> onItemClickListener;

    public void setDatas(List<PatrolSectionListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(BaseRecycleView.OnItemClickListener<PatrolSectionListBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PatrolSectioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_patrol, viewGroup, false);
        return new PatrolSectioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatrolSectioViewHolder viewHolder, int i) {
        PatrolSectionListBean bean = datas.get(i);
        viewHolder.tvTitle.setText(bean.getPATROLSECTIONNAME());
        viewHolder.tvDate.setText(bean.getOUTTIME());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,bean,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected class PatrolSectioViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDate;

        public PatrolSectioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
