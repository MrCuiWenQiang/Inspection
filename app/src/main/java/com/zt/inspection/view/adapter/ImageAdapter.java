package com.zt.inspection.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zt.inspection.R;

import java.util.Arrays;
import java.util.List;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;

/**
 * 图片and 视频第一帧展示
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private List<String> photoPaths;
    private OnPhotoListener onPhotoListener;


    public void setOnPhotoListener(OnPhotoListener onPhotoListener) {
        this.onPhotoListener = onPhotoListener;
    }


    public void setPhotoPaths(String url,String ps) {
        if (!TextUtils.isEmpty(ps)){
            String[] pss = ps.split(",");
            for (String path: pss ) {
                path = url+pss;
            }
            List<String> photoPaths = Arrays.asList(pss);
            this.photoPaths = photoPaths;
            notifyDataSetChanged();
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        RecyclerView.ViewHolder viewHolder = null;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image, viewGroup, false);
        viewHolder = new PhotoViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PhotoViewHolder photoViewHolder = (PhotoViewHolder) viewHolder;
        ImageLoadHelper.loadImage(photoViewHolder.itemView.getContext(), photoViewHolder.iv_pto, photoPaths.get(i));
        photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPhotoListener!=null){
                    onPhotoListener.onClick(-1,i,photoPaths.get(i));
                }
            }
        });
    }



    public interface OnPhotoListener{
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
