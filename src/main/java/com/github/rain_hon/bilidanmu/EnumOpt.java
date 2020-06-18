package com.github.rain_hon.bilidanmu;

public enum EnumOpt{
//2	客户端发送的心跳包
//3	人气值，数据不是JSON，是4字节整数
//5	命令，数据中['cmd']表示具体命令
//7	认证并加入房间
//8	服务器发送的心跳包


    HEARTBEAT(2),
    HOT(3),
    CMD(5),
    AUTH(7),
    HEARTBEAT_REPLY(8);

    private final int i;
    EnumOpt(Integer i){
        this.i = i;
    }

    public int getI() {
        return i;
    }
}