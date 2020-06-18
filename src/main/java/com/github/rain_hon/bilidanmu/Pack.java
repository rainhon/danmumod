package com.github.rain_hon.bilidanmu;

import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import static com.github.rain_hon.bilidanmu.EnumOpt.CMD;

/**
 * 解析b站直播弹幕协议
 */
public class Pack{

    // HEADER (固定总长度16)
    // 偏移量	长度	含义
    //  0	    4	封包总大小
    //  4	    2	头部长度
    //  6	    2	协议版本 payload类型 0-string 1-int 2-zlib
    //  8	    4	操作码（封包类型）
    //  12	    4	sequence，可以取常数1

    public static ByteBuffer packContent(String content, EnumOpt opt) {
        byte[] content_byte = content.getBytes(StandardCharsets.UTF_8);

        ByteBuffer pack = buildHeader(content_byte.length, opt);
        pack.put(content_byte);
        return pack;

    }

    private static ByteBuffer buildHeader(int content_length, EnumOpt opt){
        ByteBuffer bf = ByteBuffer.allocate(16+content_length);
        bf.putInt(content_length + 16);
        bf.putShort((short)16);
        bf.putShort((short)0);
        bf.putInt(opt.getI());
        bf.putInt(1);
        return bf;

    }

    /**
     * 解析接收的包，只返回弹幕，否则返回null
     * @param receive  接收到的包
     * @return 弹幕
     */
    public static String analyze(byte[] receive)  {

        if((int)receive[11] == CMD.getI()){

            int header_length = receive[5];
            Inflater inflater = new Inflater();

            byte[] result = new byte[receive.length * 2];

            byte[] body = new byte[receive.length - header_length];
            System.arraycopy(receive, header_length, body, 0, receive.length - header_length);

            if(receive[7] == 2){
                try{
                    inflater.setInput(body);
                    inflater.inflate(result);
                }catch (DataFormatException e){
                    e.printStackTrace();
                }
                //压缩的包有两个头，所以要截取第二次
                byte[] new_body = new byte[result.length - header_length];
                System.arraycopy(result, header_length, new_body, 0, result.length - header_length);
                result = new_body;
            }else{
                result = body;
            }

            String json_str = new String(result);

            try{
                JSONObject json_obj = new JSONObject(json_str);
                if(json_obj.getString("cmd").equals("DANMU_MSG")){
                    return json_obj.getJSONArray("info").getString(1);
                }
            }catch(Exception ignored){

            }
        }
        return null;
    }

}


