package com.zt.inspection.util.socket;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import cn.faker.repaymodel.util.error.ErrorUtil;

public class SocketClient {
    public static Socket socket;

    public static void startClient(final String address ,final int port,MessageListener listener){
        if (address == null){
            return;
        }
        if (socket == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.i("tcp", "启动客户端");
                        socket = new Socket(address, port);
                        Log.i("tcp", "客户端连接成功");
                        PrintWriter pw = new PrintWriter(socket.getOutputStream());

                        InputStream inputStream = socket.getInputStream();

                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1) {
                            String data = new String(buffer, 0, len);
                            listener.result(data);
                            Log.i("tcp", "收到服务器的数据---------------------------------------------:" + data);
                        }
                        Log.i("tcp", "客户端断开连接");
                        pw.close();

                    } catch (Exception e) {
//                        EE.printStackTrace();
                        ErrorUtil.showError(e);
                        Log.i("tcp", "客户端无法连接服务器");

                    }finally {
                        try {
                            if (socket!=null){
                                socket.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        socket = null;
                    }
                }
            }).start();
        }
    }

}
