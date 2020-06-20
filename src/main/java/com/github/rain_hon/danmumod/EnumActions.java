package com.github.rain_hon.danmumod;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.rain_hon.danmumod.EnumDecompositionTask.*;

public enum EnumActions{
    MOVE_FORWARD(new String[]{"前进", "向前", "w", "W"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_W);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_W);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    MOVE_BACK(new String[]{"后退", "向后", "s", "S"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_S);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_S);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    MOVE_FORWARD_STILL(new String[]{"一直往前", "按住w","按住前进"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_S);
    }),
    MOVE_FORWARD_STOP(new String[]{"停下", "松开w","松开前进"}, ()->{
        ActionTaskHandler.getInstance().put(RELEASE_W);
    }),

    LOOK_LEFT(new String[]{"向左看", "向左", "l", "LEFT", "left"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_LEFT);
    }),
    LOOK_RIGHT(new String[]{"向右看", "向右", "r", "RIGHT", "right"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_RIGHT);
    }),
    LOOK_DOWN(new String[]{"向下看", "向下", "d", "DOWN", "down"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_DOWN);
    }),
    LOOK_UP(new String[]{"向上看", "向上", "u", "UP", "UP"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_UP);
    }),
    CHOOSE_1(new String[] {"1"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_1);
        ActionTaskHandler.getInstance().put(RELEASE_1);
    }),
    CHOOSE_2(new String[] {"2"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_2);
        ActionTaskHandler.getInstance().put(RELEASE_2);
    }),
    CHOOSE_3(new String[] {"3"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_3);
        ActionTaskHandler.getInstance().put(RELEASE_3);
    }),
    CHOOSE_4(new String[] {"4"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_4);
        ActionTaskHandler.getInstance().put(RELEASE_4);
    }),
    CTRL_PRESS(new String[]{"按住ctrl", "ctrl"},()->{
        ActionTaskHandler.getInstance().put(PRESS_CTRL);
    }),
    SHIFT_PRESS(new String[]{"按住shift", "shift"},()->{
        ActionTaskHandler.getInstance().put(PRESS_SHIFT);
    }),
    CTRL_RELEASE(new String[]{"松ctrl", "松开ctrl"},()->{
        ActionTaskHandler.getInstance().put(RELEASE_CTRL);
    }),
    SHIFT_RELEASE(new String[]{"松shift", "松开shift"}, ()->{
        ActionTaskHandler.getInstance().put(RELEASE_SHIFT);
    }),
    SPACE_CLICK(new String[]{"空格","跳","跳跃"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_SPACE);
        ActionTaskHandler.getInstance().put(RELEASE_SPACE);
    }),
    SPACE_PRESS(new String[]{"按住空格"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_SPACE);
    }),
    SPACE_RELEASE(new String[]{"松开空格"}, ()->{
        ActionTaskHandler.getInstance().put(RELEASE_SPACE);
    }),
    MOUSE_LEFT_PRESS(new String[]{"按住左键", "lp", "LP"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_LEFT_BUTTON_PRESS);
    }),
    MOUSE_LEFT_RELEASE(new String[]{"松开左键", "lr", "lR"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_LEFT_BUTTON_RELEASE);
    }),

    MOUSE_LEFT_CLICK(new String[]{"左击", "lc", "LC"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_LEFT_BUTTON_PRESS);
        ActionTaskHandler.getInstance().put(MOUSE_LEFT_BUTTON_RELEASE);
    }),
    MOUSE_RIGHT_CLICK(new String[]{"右击", "rc", "RC"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_RIGHT_BUTTON_PRESS);
        ActionTaskHandler.getInstance().put(MOUSE_RIGHT_BUTTON_RELEASE);
    }),
    ;

    String[] alias;
    IActionDecomposition taskDecomposition;

    EnumActions(String[] alias, IActionDecomposition taskDecomposition){
        this.taskDecomposition = taskDecomposition;
        this.alias = alias;
    }

    public static EnumActions ActionOf(String action_str){
        for(EnumActions action : EnumActions.values()){
            if(Arrays.asList(action.alias).contains(action_str)){
                return action;
            }
        }
        return null;
    }
}
