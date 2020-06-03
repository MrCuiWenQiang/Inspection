package cn.faker.repaymodel.net.loadimage;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Mr.C on 2017/11/4 0004.
 */

public class ImageLoadHelper {
    /**
     * 正常加载图片
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadImage(Context context, ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)){
            return;
        }
//        Picasso.with(context).load(url).into(imageView);

        Uri loadUri = null;
        if (url.startsWith("http")) {
            //网络图片
            loadUri = Uri.parse(url);
        } else {
            //本地文件
            if (url.startsWith("file://")) {
                //文件的方式
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                    //Android 7.0系统开始 使用本地真实的Uri路径不安全,使用FileProvider封装共享Uri
                    url = Uri.parse(url).getPath();
                }
            }
            File file = new File(url);
            if (file != null && file.exists()) {
                //本地文件
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                    //Android 7.0系统开始 使用本地真实的Uri路径不安全,使用FileProvider封装共享Uri
                    loadUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
                } else {
                    loadUri = Uri.fromFile(file);
                }
            } else {
                //可能是资源路径的地址
                loadUri = Uri.parse(url);
            }
        }
        Picasso.with(context).load(loadUri).into(imageView);
    }

    /**
     *  设置宽高 减少内存开支
     * @param context
     * @param imageView
     * @param width
     * @param height
     * @param url
     */
    public static void loadImage(Context context, ImageView imageView,int width,int height, String url) {
        Picasso.with(context).load(url).resize(width,height).centerCrop().into(imageView);
    }


    /**
     * 设置图片加载中和加载失败的提示图片
     * @param context
     * @param imageView
     * @param url
     * @param placeimage 加载中
     * @param errorimage 加载失败
     */
    public static void loadImage(Context context, ImageView imageView, String url, int placeimage, int errorimage) {
        Picasso.with(context).load(url).placeholder(placeimage).error(errorimage).into(imageView);
    }

}
