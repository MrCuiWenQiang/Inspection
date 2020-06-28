package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.util.ResurltUtil;

import java.util.List;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;

public class WorksAdapter extends RecyclerView.Adapter<WorksAdapter.WorksViewHolder> {

    private List<CaseInfoBean> datas;

    public void setDatas(List<CaseInfoBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_works,viewGroup,false);
        return new WorksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorksViewHolder viewHolder, int i) {
        CaseInfoBean bean = datas.get(i);
        viewHolder.tvCasenumber.setText(bean.getCASENUMBER());
        viewHolder.tvTitle.setText(bean.getTITLE());
        viewHolder.tvHandleendtime.setText(bean.getHANDLEENDTIME());
        List<String> paths = ResurltUtil.toPaths(bean.getSGQIMAGES(),bean.getUrl());
        if (paths!=null&&paths.size()>0){
            ImageLoadHelper.loadImage(viewHolder.itemView.getContext(), viewHolder.imIcon, paths.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    protected class WorksViewHolder extends RecyclerView.ViewHolder{
         TextView tvCasenumber;
         TextView tvTitle;
         TextView tvHandleendtime;
         ImageView imIcon;
        public WorksViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCasenumber = itemView.findViewById(R.id.tv_casenumber);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvHandleendtime = itemView.findViewById(R.id.tv_handleendtime);
            imIcon = itemView.findViewById(R.id.im_icon);
        }
    }
}
