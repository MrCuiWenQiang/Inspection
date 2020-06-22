package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.CaseInfoBean;
import com.zt.inspection.util.StatusUtil;

import java.util.List;

import cn.faker.repaymodel.widget.view.BaseRecycleView;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CaseInfoBean data = datas.get(i);
        viewHolder.tvTitle.setText(data.getTITLE());
        viewHolder.tvCtid.setText(StatusUtil.getName(data.getCSTATE()));
        viewHolder.tvCtype.setText(data.getCTYPE());
        viewHolder.tvUpuid.setText(data.getUPUID());
        viewHolder.tvSenduid.setText(data.getSENDUID());
        viewHolder.tvSendtime.setText(data.getSENDTIME());
        viewHolder.tvHandleuid.setText(data.getHANDLEUID());
        viewHolder.tvHandlebegintime.setText(data.getHANDLEBEGINTIME());
        viewHolder.tvHandleendtime.setText(data.getHANDLEENDTIME());
        viewHolder.tvFeedbacktime.setText(data.getFEEDBACKTIME());
        viewHolder.tvClosetime.setText(data.getCLOSETIME());
        viewHolder.tvCaddress.setText(data.getCADDRESS());
        viewHolder.tvFeedbackcontent.setText(data.getFEEDBACKCONTENT());
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

        ImageAdapter adapter = new ImageAdapter();
        adapter.setPhotoPaths(data.getUrl(),data.getSGQIMAGES());
        adapter.setOnPhotoListener(onPhotoListener);
        viewHolder.rv_photo.setAdapter(adapter);

        VideoImageAdapter videoImageAdapter = new VideoImageAdapter();
        videoImageAdapter.setPhotoPaths(data.getUrl(),data.getSGQVIDEO());
        videoImageAdapter.setOnPhotoListener(onVideoPhotoListener);
        viewHolder.rv_video.setAdapter(videoImageAdapter);
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
            return;
        } else if (this.datas == null) {
            this.datas = datas;
        } else {
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvCtid;
        private TextView tvCtype;
        private TextView tvUpuid;
        private TextView tvSenduid;
        private TextView tvSendtime;
        private TextView tvHandleuid;
        private TextView tvHandlebegintime;
        private TextView tvHandleendtime;
        private TextView tvFeedbacktime;
        private TextView tvClosetime;
        private TextView tvFeedbackcontent;
        private TextView tvCaddress;
        private RecyclerView rv_video;
        private RecyclerView rv_photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCaddress = itemView.findViewById(R.id.tv_caddress);
            tvCtid = itemView.findViewById(R.id.tv_ctid);
            tvCtype = itemView.findViewById(R.id.tv_ctype);
            tvUpuid = itemView.findViewById(R.id.tv_upuid);
            tvSenduid = itemView.findViewById(R.id.tv_senduid);
            tvSendtime = itemView.findViewById(R.id.tv_sendtime);
            tvHandleuid = itemView.findViewById(R.id.tv_handleuid);
            tvHandlebegintime = itemView.findViewById(R.id.tv_handlebegintime);
            tvHandleendtime = itemView.findViewById(R.id.tv_handleendtime);
            tvFeedbacktime = itemView.findViewById(R.id.tv_feedbacktime);
            tvClosetime = itemView.findViewById(R.id.tv_closetime);
            tvFeedbackcontent = itemView.findViewById(R.id.tv_feedbackcontent);
            rv_video = itemView.findViewById(R.id.rv_video);
            rv_photo = itemView.findViewById(R.id.rv_photo);
            rv_photo.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            rv_video.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));

        }
    }
}
