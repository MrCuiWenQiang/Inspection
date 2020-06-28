package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.WordInfoBean;
import com.zt.inspection.util.StatusUtil;

import java.util.Date;
import java.util.List;

import cn.faker.repaymodel.util.DateUtils;
import cn.faker.repaymodel.widget.view.date.DateUtil;

import static com.zt.inspection.view.adapter.ResourceAdapter.ADAPTER_TYPR_SHOW_PHOTO;

public class WorkInfoAdapter extends RecyclerView.Adapter<WorkInfoAdapter.ViewHolder> {
    private List<WordInfoBean> datas;

    public void setData(List<WordInfoBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_workinfo, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WordInfoBean wordInfoBean = datas.get(i);
        viewHolder.tvCstate.setText(StatusUtil.getLcName(wordInfoBean.getCSTATE()));
        Date d = DateUtils.stringToDate(wordInfoBean.getCREATETIME(), DateUtils.DATE_TIME_FORMAT);
        viewHolder.tvCreatetime.setText(DateUtils.dateToString(d, DateUtils.DATE_TIME_FORMAT));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCstate;
        TextView tvCreatetime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCstate = itemView.findViewById(R.id.tv_cstate);
            tvCreatetime = itemView.findViewById(R.id.tv_createtime);
        }
    }
}
