package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.WorkUserBean;
import com.zt.inspection.model.entity.response.WorkUserItemBean;
import com.zt.inspection.util.WorkUtil;

import java.util.Date;
import java.util.List;

import cn.faker.repaymodel.util.DateUtils;

/**
 * 个人考勤
 */
public class WorkStatisAdapter extends RecyclerView.Adapter<WorkStatisAdapter.WorkStatisViewHolder> {

    private List<WorkUserBean> data;

    public void setUserData(List<WorkUserBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public WorkStatisViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work_statis_user, viewGroup, false);

        return new WorkStatisViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkStatisViewHolder viewHloder, int i) {
        WorkUserBean workItem = data.get(i);
        String typeName = WorkUtil.typeName(workItem.getType());
        viewHloder.tv_status.setText(typeName);
        viewHloder.tv_sum.setText(workItem.getSum() + "次,共" + workItem.getSum() + "分钟");
        WorkStatisDetailsAdapter adapter = new WorkStatisDetailsAdapter();
        adapter.setData(workItem.getList());
        viewHloder.ll_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHloder.rv_child.getVisibility()==View.GONE){
                    viewHloder.rv_child.setVisibility(View.VISIBLE);
                }else {
                    viewHloder.rv_child.setVisibility(View.GONE);
                }
            }
        });
        viewHloder.rv_child.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    protected class WorkStatisViewHolder extends RecyclerView.ViewHolder {
        TextView tv_status;
        TextView tv_sum;
        ImageView im_right;
        RelativeLayout ll_t;
        RecyclerView rv_child;

        public WorkStatisViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.tv_status);
            im_right = itemView.findViewById(R.id.im_right);
            ll_t = itemView.findViewById(R.id.ll_t);
            tv_sum = itemView.findViewById(R.id.tv_sum);
            rv_child = itemView.findViewById(R.id.rv_child);
            rv_child.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }


    /**
     * 详情的list
     */

    protected class WorkStatisDetailsAdapter extends RecyclerView.Adapter<WorkStatisDetailsAdapter.WorkStatisDetailsViewHolder> {
        private List<WorkUserItemBean> list;

        @NonNull
        @Override
        public WorkStatisDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work_statis_user_details, viewGroup, false);
            return new WorkStatisDetailsViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull WorkStatisDetailsViewHolder viewHolder, int i) {
            WorkUserItemBean item = list.get(i);
            viewHolder.tv_date.setText(DateUtils.dateToString(new Date(item.getData()), DateUtils.DATE_FORMAT));
            if (item.getTime() > 0) {
                viewHolder.tv_right_time.setText(item.getTime() + "分钟");
            }
            viewHolder.tv_remars.setText(item.getRemarks());
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public void setData(List<WorkUserItemBean> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        protected class WorkStatisDetailsViewHolder extends RecyclerView.ViewHolder {
            TextView tv_date;
            TextView tv_right_time;
            TextView tv_remars;

            public WorkStatisDetailsViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_date = itemView.findViewById(R.id.tv_date);
                tv_right_time = itemView.findViewById(R.id.tv_right_time);
                tv_remars = itemView.findViewById(R.id.tv_remars);
            }
        }
    }

}
