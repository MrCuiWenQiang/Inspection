package com.zt.inspection.view.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.zt.inspection.R;
import com.zt.inspection.Urls;
import com.zt.inspection.model.entity.response.MessageBean;
import com.zt.inspection.util.HttpUtil;
import com.zt.inspection.util.socket.MessageListener;
import com.zt.inspection.util.socket.SocketClient;
import com.zt.inspection.view.NoticeActivity;

import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.util.error.ErrorUtil;

public class MessageClientService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    int notifyId = 1;
    String id = "my_channel_01";
    String name = "渠道名字";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // TODO: 2020/7/23 震动有问题
        SocketClient.startClient(Urls.msgip, Urls.msgPort, new MessageListener() {
            @Override
            public void result(String msg) {
                MessageBean messageBean = JsonUtil.convertJsonToObject(msg, MessageBean.class);
                if (messageBean != null) {
                    try {
                        Intent intent = NoticeActivity.newInstance(getBaseContext(), Integer.valueOf(messageBean.getType()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                        Notification notification = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
                            // 设置通知出现时的闪灯（如果 android 设备支持的话）
                            mChannel.enableLights(true);
                            mChannel.setLightColor(Color.RED);
                            // 设置通知出现时的震动（如果 android 设备支持的话）
                            mChannel.enableVibration(true);
                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                            manager.createNotificationChannel(mChannel);
                            notification = new Notification.Builder(getBaseContext())
                                    .setChannelId(id)
                                    .setContentIntent(pendingIntent)
                                    .setContentTitle("你有一条新公告")
                                    .setContentText("点击查看")
                                    .setAutoCancel(true)
                                    .setSmallIcon(R.mipmap.ic_launcher).build();
                        } else {
                            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getBaseContext())
                                    .setContentTitle("你有一条新公告")
                                    .setContentText("点击查看")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setOngoing(true)
                                    .setAutoCancel(true)
                                    .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                                    .setContentIntent(pendingIntent)
                                    .setVibrate(new long[]{1000, 500, 2000});
                            notification = notificationBuilder.build();
                        }
                        manager.notify(1, notification);
                    } catch (Exception e) {
                        ErrorUtil.showError(e);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
