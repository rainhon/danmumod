package com.github.rain_hon.danmumod;

import java.util.ArrayList;

public class ActionTaskHandler {

    private static ActionTaskHandler instance = null;
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

    public void pressKey(int key){

    }

    public void releaseKey(int key){

    }
}
