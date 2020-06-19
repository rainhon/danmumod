package com.github.rain_hon.danmumod;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFW;

public class KeyboardController {

    private KeyboardController(){}

    private static KeyboardController instance = null;

    public static KeyboardController getInstance(){
        if(instance == null){
            instance = new KeyboardController();
        }
        return instance;
    }

    static int modifiers = 0;
    int key;
    int action;

    public KeyboardController setKey(int key){
        this.key = key;
        return this;
    }

    public KeyboardController setAction(int action){
        this.action = action;
        return this;
    }

    public void pressModifiers(int mod){
        modifiers = modifiers & mod;
    }

    public void releaseModifiers(int mod){
        modifiers = modifiers & (~mod);
    }

    public void execute(){
        MCReference.keyboardListener
                .onKeyEvent(MCReference.window, this.key, GLFW.glfwGetKeyScancode(this.key), this.action, modifiers);
    }

    public void pressKey(int key){
        MCReference.keyboardListener
                .onKeyEvent(MCReference.window, key, GLFW.glfwGetKeyScancode(key), GLFW_PRESS, modifiers);
    }

    public void releaseKey(int key){
        MCReference.keyboardListener
                .onKeyEvent(MCReference.window, key, GLFW.glfwGetKeyScancode(key), GLFW_RELEASE, modifiers);
    }
}
