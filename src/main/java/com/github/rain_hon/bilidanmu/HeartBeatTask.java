package com.github.rain_hon.bilidanmu;

import java.io.IOException;
import java.net.Socket;

public class HeartBeatTask implements Runnable {
    private Socket socket;

    public HeartBeatTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try{
            SendDataHandler.sendHeartBeat(socket);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
