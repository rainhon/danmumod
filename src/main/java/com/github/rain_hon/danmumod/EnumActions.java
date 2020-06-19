package com.github.rain_hon.danmumod;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.github.rain_hon.danmumod.EnumDecompositionTask.*;

public enum EnumActions{
    MOVE_FORWARD(new String[]{"前进", "向前"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_W);
        ActionTaskHandler.scheduledExecutorService.schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_W);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    MOVE_BACK(new String[]{"后退", "向后"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_S);
        ActionTaskHandler.scheduledExecutorService.schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_S);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    LOOK_LEFT(new String[]{"向左看"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_LEFT);
    }),
    LOOK_RIGHT(new String[]{"向右看"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_RIGHT);
    }),
    LOOK_DOWN(new String[]{"向下看"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_DOWN);
    }),
    LOOK_UP(new String[]{"向上看"}, ()->{
        ActionTaskHandler.getInstance().put(MOUSE_UP);
    }),
    CHOOSE_1(new String[] {"1"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_1);
        ActionTaskHandler.scheduledExecutorService.schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_1);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_2(new String[] {"2"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_2);
        ActionTaskHandler.scheduledExecutorService.schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_2);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_3(new String[] {"3"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_3);
        ActionTaskHandler.scheduledExecutorService.schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_3);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_4(new String[] {"4"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_4);
        ActionTaskHandler.scheduledExecutorService.schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_4);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CTRL_PRESS(new String[]{"按住ctl"},()->{
        ActionTaskHandler.getInstance().put(PRESS_CTRL);
    }),
    SHIFT_PRESS(new String[]{"按住shift"},()->{
        ActionTaskHandler.getInstance().put(PRESS_SHIFT);
    }),
    CTRL_RELEASE(new String[]{"松ctl"},()->{
        ActionTaskHandler.getInstance().put(RELEASE_CTRL);
    }),
    SHIFT_RELEASE(new String[]{"松shift"}, ()->{
        ActionTaskHandler.getInstance().put(RELEASE_SHIFT);
    }),
    SPACE_PRESS(new String[]{"按住空格"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_SPACE);
    }),
    SPACE_RELEASE(new String[]{"松开空格"}, ()->{
        ActionTaskHandler.getInstance().put(RELEASE_SPACE);
    });

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
