package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.WorkDeptItemDataBean;

import java.util.List;

// TODO: 2020/6/14 需要点击事件
public class WorkManualStatisDetailsAdapter extends RecyclerView.Adapter<WorkManualStatisDetailsAdapter.WorkManualStatisDetailsViewHolder> {
    private List<WorkDeptItemDataBean> list;
    private OnItemClickListener onItemClickListener;


    public void setData(List<WorkDeptItemDataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WorkManualStatisDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work_statis_user_manual_details, viewGroup, false);
        return new WorkManualStatisDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkManualStatisDetailsViewHolder viewHolder, int i) {
        WorkDeptItemDataBean itemDataBean = list.get(i);
        viewHolder.tvDate.setText(itemDataBean.getUserName());
//        viewHolder.tvUserName.setText();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.seectUser(itemDataBean.getUserId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected class WorkManualStatisDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        TextView tvDate;


        public WorkManualStatisDetailsViewHolder(@NonNull View convertView) {
            super(convertView);
            tvUserName = convertView.findViewById(R.id.tv_user_name);
            tvDate = convertView.findViewById(R.id.tv_date);
        }
    }

    public interface OnItemClickListener {
        void seectUser(String userId);
    }
}
