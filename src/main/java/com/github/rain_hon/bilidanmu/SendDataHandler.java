package com.github.rain_hon.bilidanmu;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

//import org.apache.commons.lang3.RandomUtils;

public class SendDataHandler {

    public static void sendAuth(Socket socket, long room_id) throws IOException {
        JSONObject auth_info = new JSONObject();
//        long clientId = RandomUtils.nextLong(100000000000000L, 300000000000000L);
//        auth_info.put("uid", clientId);
        auth_info.put("roomid", room_id);
//        auth_info.put("protover", 2);
//        auth_info.put("platform", "danmuji");
//        auth_info.put("clientver", "1.13.1");
//        auth_info.put("token", "");
        String content = auth_info.toString();
        ByteBuffer pack = Pack.packContent(content, EnumOpt.AUTH);

        socket.getOutputStream().write(pack.array());
    }

    public static void sendHeartBeat(Socket socket) throws IOException {
        ByteBuffer pack = Pack.packContent("", EnumOpt.HEARTBEAT);
        socket.getOutputStream().write(pack.array());
    }
}
