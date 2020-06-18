package com.github.rain_hon.bilidanmu;

/**
 * 心跳包读取抛出的异常在这里捕获，触发重连
 */
public class TaskUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    TCPClient client;

    public TaskUncaughtExceptionHandler(TCPClient client) {
        this.client = client;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("出现异常,即将重新连接:" + e.getLocalizedMessage());
        client.shutdownService();
        client.start(client.room_id);
    }
}
