package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.WorkDeptBean;
import com.zt.inspection.model.entity.response.WorkDeptItemStatusBean;
import com.zt.inspection.model.entity.response.WorkUserBean;
import com.zt.inspection.model.entity.response.WorkUserItemBean;
import com.zt.inspection.util.WorkUtil;

import java.util.List;

/**
 * 部门考勤 多viewholder
 */
public class WorkManualStatisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private WorkDeptBean data;

    public void setUserData(WorkDeptBean data) {
        this.data = data;
    }

    private final int TYPE_TOP = 2;
    private final int TYPE_BODY = 3;
    private WorkManualStatisDetailsAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(WorkManualStatisDetailsAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_TOP : TYPE_BODY;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        if (i == TYPE_TOP) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work_statis_user_manual_top, viewGroup, false);
            viewHolder = new TopViewHolder(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work_statis_user_manual, viewGroup, false);
            viewHolder = new WorkStatisViewHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHloder, int i) {
        if (viewHloder instanceof WorkStatisViewHolder) {
            WorkDeptItemStatusBean workItem = data.getStatus().get(i - 1);
            WorkStatisViewHolder statisViewHolder = (WorkStatisViewHolder) viewHloder;
            String typeName = WorkUtil.typeName(workItem.getType());
            statisViewHolder.tv_status.setText(typeName);
            statisViewHolder.tv_sum.setText(workItem.getSum() + "人");

            WorkManualStatisDetailsAdapter adapter = new WorkManualStatisDetailsAdapter();
            adapter.setOnItemClickListener(onItemClickListener);
            adapter.setData(workItem.getList());
            statisViewHolder.rv_child.setAdapter(adapter);
        } else {
            TopViewHolder topViewHolder = (TopViewHolder) viewHloder;
            String hint = "部门总人数:" + data.getNumber() + ";异常考勤人数共" + data.getErrorNumber() + "人";
            topViewHolder.tv_hint.setText(hint);
        }
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else if (data.getStatus() == null) {
            return 1;
        } else {
            return data.getStatus().size() + 1;
        }
    }


    protected class WorkStatisViewHolder extends RecyclerView.ViewHolder {
        TextView tv_status;
        TextView tv_sum;
        RecyclerView rv_child;

        public WorkStatisViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_sum = itemView.findViewById(R.id.tv_sum);
            rv_child = itemView.findViewById(R.id.rv_child);
            rv_child.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }

    protected class TopViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hint;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hint = itemView.findViewById(R.id.tv_hint);
        }
    }

}
