package com.zt.inspection.view.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zt.inspection.Urls;
import com.zt.inspection.model.entity.response.MessageBean;
import com.zt.inspection.util.HttpUtil;
import com.zt.inspection.util.socket.MessageListener;
import com.zt.inspection.util.socket.SocketClient;
import com.zt.inspection.view.NoticeActivity;

import cn.faker.repaymodel.net.json.JsonUtil;

public class MessageClientService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    int notifyId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        SocketClient.startClient(HttpUtil.getHttpSetting_ip(), Urls.msgPort, new MessageListener() {
            @Override
            public void result(String msg) {
                MessageBean messageBean = JsonUtil.convertJsonToObject(msg, MessageBean.class);
                if (messageBean != null) {
                    Notification.Builder builder = new Notification.Builder(getApplicationContext())
                            .setTicker("你有一条新公告")
                            .setContentTitle("你有一条新公告")
                            .setDefaults(Notification.FLAG_INSISTENT)
                            .setContentText("点击查看").setAutoCancel(true);
                    Intent intent = NoticeActivity.newInstance(getBaseContext(), Integer.valueOf(messageBean.getType()));
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    manager.notify(notifyId++,builder.build());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
