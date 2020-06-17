package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.SectionBean;

import java.util.List;

import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class HistoryRouteAdapter extends RecyclerView.Adapter<HistoryRouteAdapter.ViewHolder> {

    private List<SectionBean> datas;
    private BaseRecycleView.OnItemClickListener<SectionBean> onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_route, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SectionBean data = datas.get(i);
        viewHolder.tvPatrolsectionname.setText(data.getPATROLSECTIONNAME());
        viewHolder.tvUserid.setText("由"+data.getUSERID()+"创建");
        viewHolder.tvSigntime.setText(data.getSIGNTIME());
        viewHolder.tvOuttime.setText(data.getOUTTIME());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,data,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<SectionBean> datas) {
        if (datas == null) {
            this.datas = datas;
            return;
        } else if (this.datas == null) {
            this.datas = datas;
        } else {
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(BaseRecycleView.OnItemClickListener<SectionBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPatrolsectionname;
        private TextView tvUserid;
        private TextView tvSigntime;
        private TextView tvOuttime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPatrolsectionname = itemView.findViewById(R.id.tv_patrolsectionname);
            tvUserid = itemView.findViewById(R.id.tv_userid);
            tvSigntime = itemView.findViewById(R.id.tv_signtime);
            tvOuttime = itemView.findViewById(R.id.tv_outtime);
        }
    }
}
