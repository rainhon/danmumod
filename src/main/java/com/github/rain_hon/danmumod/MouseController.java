package com.github.rain_hon.danmumod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.util.InputMappings;

import java.lang.reflect.Field;
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

    Field mouseGrabbed;

    Field ignoreFirstMove;

    Field leftClickCounter;

//    Field mouseX;
//
//    Field mouseY;

    double windowWidth;
    double windowHeight;



    private MouseController(){
        try{
            //反射获取mousehelper的cursorPosCallback
            mouseButtonCallback = mouseHelper.getClass()
                    .getDeclaredMethod("mouseButtonCallback", long.class, int.class, int.class, int.class);
            mouseButtonCallback.setAccessible(true);
            cursorPosCallback = mouseHelper.getClass()
                    .getDeclaredMethod("cursorPosCallback", long.class, double.class, double.class);
            cursorPosCallback.setAccessible(true);

            mouseGrabbed = mouseHelper.getClass()
                    .getDeclaredField("mouseGrabbed");
            mouseGrabbed.setAccessible(true);

            ignoreFirstMove = mouseHelper.getClass()
                    .getDeclaredField("ignoreFirstMove");
            ignoreFirstMove.setAccessible(true);

            leftClickCounter = MCReference.minecraft.getClass()
                    .getDeclaredField("leftClickCounter");
            leftClickCounter.setAccessible(true);

//            mouseX = mouseHelper.getClass()
//                    .getDeclaredField("mouseX");
//            mouseX.setAccessible(true);
//
//            mouseY = mouseHelper.getClass()
//                    .getDeclaredField("mouseY");
//            mouseY.setAccessible(true);

            windowWidth = MCReference.minecraft.getMainWindow().getWidth();
            windowHeight = MCReference.minecraft.getMainWindow().getHeight();


        }catch(NoSuchMethodException|NoSuchFieldException e){
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
        targetX = windowWidth / 2d;
        targetY = windowHeight * 0.25;
        return this;
    }

    public MouseController mouseDown(){
        targetX = windowWidth / 2d;
        targetY = windowHeight * 0.75;
        return this;
    }

    public MouseController mouseLeft(){
        targetX = windowWidth * 0.25;
        targetY = windowHeight / 2d;
        return this;
    }

    public MouseController mouseRight(){
        targetX = windowWidth * 0.75;
        targetY = windowHeight / 2d;
        return this;
    }

    public void executeMove(){
        try{
            MCReference.minecraft.gameSettings.smoothCamera = false;
            MCReference.minecraft.setGameFocused(true);
//            mouseHelper.grabMouse();
            mouseGrabbed.set(mouseHelper, true);
            ignoreFirstMove.set(mouseHelper, false);

            cursorPosCallback.invoke(mouseHelper, MCReference.window, targetX, targetY);
            MCReference.minecraft.gameSettings.smoothCamera = false;
        } catch (IllegalAccessException|InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeButton(int button, int action){
        try{
            MCReference.minecraft.setGameFocused(true);

            mouseHelper.grabMouse();
            ignoreFirstMove.set(mouseHelper, false);
            leftClickCounter.set(MCReference.minecraft, 0);
            mouseButtonCallback.invoke(mouseHelper, MCReference.window, button, action, 0);
        }catch (InvocationTargetException|IllegalAccessException e){
            throw new RuntimeException(e);
        }

    }
}
