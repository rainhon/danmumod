package com.github.rain_hon.bilidanmu;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TCPClient {

    Socket socket;

    boolean isRunning;

    /**
     * 接收数据的服务
     */
    ExecutorService receiveDataService;

    /**
     * 心跳包服务
     */
    ScheduledExecutorService heartBeatService;

    /**
     * 所有未捕获异常处理
     */
    TaskUncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * 房间id（在websocket包中查看）
     */
    long room_id = 1026372;

    /**
     * 直播地址
     */
    String host = "broadcastlv.chat.bilibili.com";
    int port = 2243;

//    public static void main(String[] args){
//        new TCPClient().start(this.room_id);
//    }

    public void connect(String addr, int port) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(addr, port));
        socket.setReceiveBufferSize(1024 * 10);
    }

    public void start(long room_id){
        this.room_id = room_id;
        if(isRunning){
            return;
        }
        try{
            connect(host, port);
            SendDataHandler.sendAuth(socket, room_id);
            SendDataHandler.sendHeartBeat(socket);
            System.out.println("完成连接");

            uncaughtExceptionHandler = new TaskUncaughtExceptionHandler(this);//捕获线程抛出的异常

            runHeartBeat();
            runReceiveData();

        }catch (IOException exception){
            System.out.println("连接失败");
            exception.printStackTrace();
            try{
                socket.close();
            }catch(IOException ignored){

            }
        }
    }

    private void runReceiveData(){
        receiveDataService = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("receive_data_thread");
            t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            return t;
        });
        receiveDataService.execute(new ReceiveDataTask(socket));
    }

    private void runHeartBeat(){
        heartBeatService = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            t.setName("heart_beat_thread");
            return t;
        });
        heartBeatService.scheduleAtFixedRate(new HeartBeatTask(socket), 0, 30, TimeUnit.SECONDS);
    }

    public void shutdownService(){
        receiveDataService.shutdown();
        heartBeatService.shutdownNow();
    }
}
