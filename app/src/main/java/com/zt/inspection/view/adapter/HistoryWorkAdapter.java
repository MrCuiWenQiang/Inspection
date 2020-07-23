package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.util.ResurltUtil;
import com.zt.inspection.util.StatusUtil;

import java.util.List;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;
import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class HistoryWorkAdapter extends RecyclerView.Adapter<HistoryWorkAdapter.ViewHolder> {
    private List<CaseInfoBean> datas;
    private BaseRecycleView.OnItemClickListener<CaseInfoBean> onItemClickListener;
    private OnLongListener<CaseInfoBean> onLongListener;

    public void setOnItemClickListener(BaseRecycleView.OnItemClickListener<CaseInfoBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongListener(OnLongListener<CaseInfoBean> onLongListener) {
        this.onLongListener = onLongListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_work, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CaseInfoBean data = datas.get(i);
        viewHolder.tvTitle.setText(data.getTYPENAME());
        viewHolder.tvCasenumber.setText(data.getCASENUMBER());
        viewHolder.tvCdatetime.setText(data.getCDATETIME());
        viewHolder.tvRemarks.setText(data.getFEEDBACKCONTENT());
        viewHolder.tvCaddress.setText(data.getCADDRESS());

        List<String> paths = ResurltUtil.toPaths(data.getSGQIMAGES(),data.getUrl());
        if (paths!=null&&paths.size()>0){
            ImageLoadHelper.loadImage(viewHolder.itemView.getContext(), viewHolder.im_sgq, paths.get(0));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, data, i);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongListener != null) {
                    onLongListener.onlongListerer(i, data);
                }
                return true;
            }
        });
    }
    private ImageAdapter.OnPhotoListener onPhotoListener;
    private VideoImageAdapter.OnVideoPhotoListener onVideoPhotoListener;

    public void setOnVideoPhotoListener(VideoImageAdapter.OnVideoPhotoListener onVideoPhotoListener) {
        this.onVideoPhotoListener = onVideoPhotoListener;
    }

    public void setOnPhotoListener(ImageAdapter.OnPhotoListener onPhotoListener) {
        this.onPhotoListener = onPhotoListener;
    }
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setDatas(List<CaseInfoBean> datas) {
        if (datas == null) {
            this.datas = datas;
        } else if (this.datas == null) {
            this.datas = datas;
        } else {
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCasenumber;
        private TextView tvCdatetime;
        private TextView tvTitle;
        private TextView tvRemarks;
        private TextView tvCaddress;
        private ImageView im_sgq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCasenumber = itemView.findViewById(R.id.tv_casenumber);
            tvCdatetime = itemView.findViewById(R.id.tv_cdatetime);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRemarks = itemView.findViewById(R.id.tv_remarks);
            tvCaddress = itemView.findViewById(R.id.tv_caddress);
            im_sgq = itemView.findViewById(R.id.im_sgq);
        }
    }
}
