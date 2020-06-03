package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.LeaveListBean;

import java.util.List;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.ViewHolder> {
    private List<LeaveListBean> datas;
    private OnItemListener listener;//本该独立出intenface 类懒得创建类了

    public void setDatas(List<LeaveListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_leave, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LeaveListBean data = datas.get(i);
        viewHolder.tv_name.setText(data.getUSER_NAME()+"申请");
        viewHolder.tv_date.setText(data.getSTART_DATE()+"开始,到"+data.getEND_DATE()+"结束");
        viewHolder.tv_type.setText(data.getLEAVE_TYPE());
        viewHolder.tv_status.setText(data.getSTATE());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(i,data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_date;
        TextView tv_type;
        TextView tv_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }

    public interface OnItemListener {
        void onClick(int postoin, Object data);
    }
}
