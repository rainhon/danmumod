package com.github.rain_hon.danmumod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.github.rain_hon.danmumod.MCReference.mouseHelper;

/**
 * 模拟鼠标控制
 * 单例
 */
public class MouseController {

    /**
     * private void mouseButtonCallback(long handle, int button, int action, int mods)
     */
    Method mouseButtonCallback;
    /**
     * private void cursorPosCallback(long handle, double xpos, double ypos)
     */
    Method cursorPosCallback;

    private MouseController(){
        try{
            //反射获取mousehelper的cursorPosCallback
            //调用私有方法必须用getDeclaredMethod
            mouseButtonCallback = mouseHelper.getClass()
                    .getDeclaredMethod("mouseButtonCallback", long.class, int.class, int.class, int.class);
            mouseButtonCallback.setAccessible(true);
            cursorPosCallback = mouseHelper.getClass()
                    .getDeclaredMethod("cursorPosCallback", long.class, double.class, double.class);
            cursorPosCallback.setAccessible(true);
        }catch(NoSuchMethodException e){
            throw new RuntimeException(e);
        }

    }

    private static MouseController instance = null;

    public static MouseController getInstance(){
        if(instance == null){
            instance = new MouseController();
        }
        return instance;
    }

    double targetX;
    double targetY;

    public MouseController mouseUp(){
        targetX = mouseHelper.getMouseX();
        targetY = mouseHelper.getMouseY() - 100;
        return this;
    }

    public MouseController mouseDown(){
        targetX = mouseHelper.getMouseX();
        targetY = mouseHelper.getMouseY() + 100;
        return this;
    }

    public MouseController mouseLeft(){
        targetX = mouseHelper.getMouseX();
        targetY = mouseHelper.getMouseY() - 100;
        return this;
    }

    public MouseController mouseRight(){
        targetX = mouseHelper.getMouseX();
        targetY = mouseHelper.getMouseY() + 100;
        return this;
    }

    public void executeMove(){
        try{
//            mouseHelper.grabMouse();
            cursorPosCallback.invoke(mouseHelper, MCReference.window, targetX, targetY);
        } catch (IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
