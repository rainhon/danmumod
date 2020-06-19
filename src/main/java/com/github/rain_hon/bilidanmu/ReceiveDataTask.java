package com.github.rain_hon.bilidanmu;

import com.github.rain_hon.bilidanmu.util.ReadStreamUtil;
import com.github.rain_hon.danmumod.ActionTaskHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReceiveDataTask implements Runnable {

    private final Socket socket;

    public ReceiveDataTask(Socket socket) {
        this.socket =socket;
    }

    @Override
    synchronized public void run() {
        try{
//            synchronized(socket){
                InputStream input = socket.getInputStream();
                ActionTaskHandler handler = ActionTaskHandler.getInstance();

                while(true){
                    if(input.available() > 0){
                        byte[] receive = ReadStreamUtil.readStream(input, 1024*10);
                        String danmu = Pack.analyze(receive);
                        if(danmu != null){
                            handler.dispatch(danmu);
                            System.out.println(danmu);
                        }
                    }
                }
//            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
