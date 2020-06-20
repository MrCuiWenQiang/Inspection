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

import java.util.List;

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
        viewHolder.tvHandlebegintime.setText(wordInfoBean.getHANDLEENDTIME());
        viewHolder.tvHandleendtime.setText(wordInfoBean.getHANDLEENDTIME());
        viewHolder.tvFkuid.setText(wordInfoBean.getSENDUNAME());
        viewHolder.tvFeedbacktime.setText(wordInfoBean.getFEEDBACKTIME());
        viewHolder.tvFeedbackcontent.setText(wordInfoBean.getFEEDBACKCONTENT());
        ImageAdapter adapter = new ImageAdapter();
        adapter.setPhotoPaths(wordInfoBean.getUrl(),wordInfoBean.getSGHIMAGES());
        viewHolder.rv_photo.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHandlebegintime;
        TextView tvHandleendtime;
        TextView tvFkuid;
        TextView tvFeedbacktime;
        TextView tvFeedbackcontent;
        RecyclerView rv_photo;
        RecyclerView rv_video;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHandlebegintime = itemView.findViewById(R.id.tv_handlebegintime);
            tvHandleendtime = itemView.findViewById(R.id.tv_handleendtime);
            tvFkuid = itemView.findViewById(R.id.tv_fkuid);
            tvFeedbacktime = itemView.findViewById(R.id.tv_feedbacktime);
            tvFeedbackcontent = itemView.findViewById(R.id.tv_feedbackcontent);
            rv_photo = itemView.findViewById(R.id.rv_photo);
            rv_video = itemView.findViewById(R.id.rv_video);
            rv_photo.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rv_video.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
