package com.zt.inspection.view.adapter;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zt.inspection.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.faker.repaymodel.net.loadimage.ImageLoadHelper;

/**
 * 图片and 视频第一帧展示
 */
public class ResourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int type = 0;

    public final static int ADAPTER_TYPR_SHOW_PHOTO = 0X002; //照片
    public final static int ADAPTER_TYPR_SHOW_VIDEO = 0X003;//视频第一帧图片

    public final static int TYPE_ADD = 2;
    public final static int TYPE_PHOTO = 1;

    private List<String> photoPaths;
    private OnPhotoListener onPhotoListener;

    public ResourceAdapter(int type) {
        this.type = type;
    }

    public void setOnPhotoListener(OnPhotoListener onPhotoListener) {
        this.onPhotoListener = onPhotoListener;
    }

    public void setPhotoPaths(List<String> photoPaths) {
        this.photoPaths = photoPaths;
        notifyDataSetChanged();
    }
    public void setPhotoPaths(String ps) {
        if (TextUtils.isEmpty(ps)){
            String[] pss = ps.split(",");
            List<String> photoPaths = Arrays.asList(pss);
            this.photoPaths = photoPaths;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == position + 1) {
            return TYPE_ADD;
        } else {
            return TYPE_PHOTO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (type) {
            case TYPE_ADD: {
                LinearLayout ll_g = new LinearLayout(viewGroup.getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(120,LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.leftMargin=6;
                ll_g.setLayoutParams(layoutParams);
                ll_g.setGravity(Gravity.CENTER);
                viewHolder = new AddViewHolder(ll_g);
                break;
            }
            case TYPE_PHOTO: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_resourceshow, viewGroup, false);
                viewHolder = new PhotoViewHolder(v);
                break;
            }
        }

        return viewHolder;
    }
    public void removeItem(int p){
        if (p<photoPaths.size()){
            photoPaths.remove(p);
            notifyDataSetChanged();
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof AddViewHolder) {
            AddViewHolder addViewHolder = (AddViewHolder) viewHolder;
            addViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPhotoListener!=null){
                        onPhotoListener.onClick(TYPE_ADD,i,null);
                    }
                }
            });
        } else if (viewHolder instanceof PhotoViewHolder) {
            PhotoViewHolder photoViewHolder = (PhotoViewHolder) viewHolder;
            ImageLoadHelper.loadImage(photoViewHolder.itemView.getContext(), photoViewHolder.iv_pto, photoPaths.get(i));
            photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPhotoListener!=null){
                        onPhotoListener.onClick(TYPE_PHOTO,i,photoPaths.get(i));
                    }
                }
            });
            photoViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onPhotoListener.onLongClick(TYPE_PHOTO,i,photoPaths.get(i));
                    return false;
                }
            });
        }
    }



    public interface OnPhotoListener{
        void onClick(int type,int postoin,Object data);
        void onLongClick(int type,int postoin,Object data);
    }

    @Override
    public int getItemCount() {
        return photoPaths == null ? 1 : photoPaths.size() + 1;
    }

    /**
     * 添加
     */
    protected class AddViewHolder extends RecyclerView.ViewHolder {
        ImageView addView;

        public AddViewHolder(@NonNull View itemView) {
            super(itemView);
            addView = new ImageView(itemView.getContext());
            addView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.mipmap.photo_add));
            ((ViewGroup) itemView).addView(addView);
        }
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
