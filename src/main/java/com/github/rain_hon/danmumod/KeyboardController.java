package com.github.rain_hon.danmumod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFW;

public class KeyboardController {


//    static ExecutorService service = Executors.newCachedThreadPool();
    static int modifiers = 0;
//    int repeat = 1;
    int key;

    public KeyboardController setKey(int key){
        this.key = key;
        return this;
    }

//    public KeyboardController setRepeat(int repeat){
//        if(key > 0 && key < 10){
//            this.repeat = delay;
//        }
//        return this;
//    }

    public void pressModifiers(int mod){
        modifiers = modifiers & mod;
    }

    public void releaseModifiers(int mod){
        modifiers = modifiers & (~mod);
    }

    public void execute(){
        MCReference.keyboardListener
                .onKeyEvent(MCReference.window, this.key, GLFW.glfwGetKeyScancode(this.key), GLFW_PRESS, modifiers);
//        MCReference.keyboardListener
//                .onKeyEvent(MCReference.window, this.key, GLFW.glfwGetKeyScancode(this.key), GLFW_RELEASE, modifiers);
    }
}
