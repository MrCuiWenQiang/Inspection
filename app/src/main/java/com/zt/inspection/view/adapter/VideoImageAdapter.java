package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zt.inspection.R;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;


public class VideoImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private List<String> photoPaths;
    private OnVideoPhotoListener onPhotoListener;


    public void setOnPhotoListener(OnVideoPhotoListener onPhotoListener) {
        this.onPhotoListener = onPhotoListener;
    }


    public void setPhotoPaths(String url,String ps) {
        if (!TextUtils.isEmpty(ps)){
            List<String> photoPaths = new ArrayList<>();
            String[] pss = ps.split(",");
            for (String path: pss ) {
                photoPaths.add( url+path);
            }
            this.photoPaths = photoPaths;
            notifyDataSetChanged();
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        RecyclerView.ViewHolder viewHolder = null;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_image, viewGroup, false);
        viewHolder = new PhotoViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) viewHolder;
//        ImageLoadHelper.loadImage(photoViewHolder.itemView.getContext(), photoViewHolder.iv_pto, photoPaths.get(i));
        photoViewHolder.iv_pto.setBackgroundResource(R.mipmap.video);
        photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPhotoListener!=null){
                    onPhotoListener.onClick(-1,i,photoPaths.get(i));
                }
            }
        });
    }



    public interface OnVideoPhotoListener{
        void onClick(int type, int postoin, Object data);
    }

    @Override
    public int getItemCount() {
        return photoPaths == null ? 0 : photoPaths.size() ;
    }


    /**
     * 图片and 视频第一帧展示viewholder
     */
    protected class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_pto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pto = itemView.findViewById(R.id.iv_pto);
        }
    }

}
