package com.github.rain_hon.bilidanmu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceiveDataHandler {

    private final String[] available_action = {"向前", "向右", "向左", "向后"};
    private static ReceiveDataHandler instance = null;
    ArrayList<String> task_list = new ArrayList<>();

    private ReceiveDataHandler(){

    }

    public static ReceiveDataHandler getInstance(){
        if(instance == null) instance = new ReceiveDataHandler();
        return instance;
    }

    public void put(String danmu){
        if(Arrays.asList(available_action).contains(danmu)){
            task_list.add(danmu);
        }
    }

    public ArrayList<String> getTask_list(){
        ArrayList<String> tasks = (ArrayList<String>) task_list.clone();
        task_list.clear();
        return tasks;
    }

    public boolean hasTask(){
        return !task_list.isEmpty();
    }
}
