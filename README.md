# 用b站直播弹幕控制游戏

## 使用方法
1.开启游戏
2.在聊天栏中输入 `roomid <房间id>`
3.去对应的直播间刷弹幕吧
4.也可以直接在聊天栏输入弹幕指令测试

**注意:如果是大主播的话，房间号可能不是连接后的数字,需要进行查询一次
https://api.live.bilibili.com/room/v1/Room/room_init?id=<url中的id>

可用弹幕指令
````
"前进", "向前", "w", "W"
"后退", "向后", "s", "S"
"一直往前", "按住w","按住前进"
"停下", "松开w","松开前进"
"向左看", "向左", "l", "LEFT", "left"
"向右看", "向右", "r", "RIGHT", "right"
"向下看", "向下", "d", "DOWN", "down"
"向上看", "向上", "u", "UP", "UP"
"按住ctrl", "ctrl"
"按住shift", "shift"
"松ctrl", "松开ctrl"
"松shift", "松开shift"
"空格","跳","跳跃"
"按住空格"
"松开空格"
"1"
"2"
"3"
"4"
"按住左键", "lp", "LP"
"松开左键", "lr", "LR"
"左击", "lc", "LC"
"右击", "rc", "RC"
```