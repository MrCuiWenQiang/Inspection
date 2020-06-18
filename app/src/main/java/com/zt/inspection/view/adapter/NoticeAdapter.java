package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zt.inspection.R;
import com.zt.inspection.model.entity.response.NoticeBean;

import java.util.List;



public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {

    private List<NoticeBean> data;
    private OnItemListener listener;

    public void setData(List<NoticeBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notice, viewGroup, false);
        return new NoticeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeHolder noticeHolder, int i) {
        NoticeBean item = data.get(i);
        noticeHolder.tv_title.setText(item.getTITLE());
        noticeHolder.tv_content.setText(item.getCONTENT());
        noticeHolder.tv_man.setText("由" + item.getUSERNAME() + "发布");
        noticeHolder.tv_date.setText("发布于" + item.getCREATEDATE());
        noticeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(i,item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    protected class NoticeHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_man;
        TextView tv_date;

        public NoticeHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_man = itemView.findViewById(R.id.tv_man);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
    public interface OnItemListener{
        void onClick(int postoin,Object data);
    }
}
