package com.github.rain_hon.danmumod;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ActionTaskHandler {

    private static ActionTaskHandler instance = null;

    /**
     * 用以执行延迟任务的线程服务
     */
    public static ScheduledExecutorService scheduledExecutorService
            = Executors.newSingleThreadScheduledExecutor();

    KeyboardController keyboardController = KeyboardController.getInstance();
    ArrayList<EnumDecompositionTask> task_list = new ArrayList<>();

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
        task_list.add(decompositionTask);
    }

    public ArrayList<EnumDecompositionTask> getTaskList(){
        ArrayList<EnumDecompositionTask> tasks = (ArrayList<EnumDecompositionTask>) task_list.clone();
        task_list.clear();
        return tasks;
    }

    public boolean hasTask(){
        return !task_list.isEmpty();
    }

}
