package com.github.rain_hon.danmumod;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.rain_hon.danmumod.EnumDecompositionTask.*;

public enum EnumActions{
    MOVE_FORWARD(new String[]{"前进", "向前"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_W);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_W);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    MOVE_BACK(new String[]{"后退", "向后"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_S);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_S);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    MOVE_RIGHT(new String[]{"向左"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_A);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_A);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    MOVE_LEFT(new String[]{"向右"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_D);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_D);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_1(new String[] {"物品栏1"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_1);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_1);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_2(new String[] {"物品栏2"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_2);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_2);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_3(new String[] {"物品栏3"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_3);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            ActionTaskHandler.getInstance().put(RELEASE_3);
        }, 2000, TimeUnit.MILLISECONDS);
    }),
    CHOOSE_4(new String[] {"物品栏4"}, ()->{
        ActionTaskHandler.getInstance().put(PRESS_4);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
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
