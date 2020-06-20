package com.github.rain_hon.danmumod;

import java.util.concurrent.LinkedBlockingQueue;

public class ActionTaskHandler {

    private static ActionTaskHandler instance = null;

//    /**
//     * 用以执行延迟任务的线程服务
//     */
//    public static ScheduledExecutorService scheduledExecutorService
//            = Executors.newSingleThreadScheduledExecutor();

//    KeyboardController keyboardController = KeyboardController.getInstance();
    LinkedBlockingQueue<EnumDecompositionTask> task_list = new LinkedBlockingQueue<>(1024*10);

    private ActionTaskHandler(){

    }

    public static ActionTaskHandler getInstance(){
        if(instance == null) instance = new ActionTaskHandler();
        return instance;
    }

    public void dispatch(String danmu){
        EnumActions action = EnumActions.ActionOf(danmu);
        if(action != null){
            action.taskDecomposition.decomposition();
        }
    }

    public void put(EnumDecompositionTask decompositionTask){
        task_list.offer(decompositionTask);
    }

    public EnumDecompositionTask getTask(){
        return task_list.poll();
    }

//    public boolean hasTask(){
//        return !task_list.isEmpty();
//    }

}
