package com.github.rain_hon.danmumod;

import static org.lwjgl.glfw.GLFW.*;

public enum EnumDecompositionTask {
    PRESS_W(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_W);
    }),
    RELEASE_W(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_W);
    }),
    PRESS_S(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_S);
    }),
    RELEASE_S(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_S);
    }),
    PRESS_A(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_A);
    }),
    RELEASE_A(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_A);
    }),
    PRESS_D(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_D);
    }),
    RELEASE_D(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_D);
    }),
    PRESS_1(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_1);
    }),
    RELEASE_1(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_1);
    }),
    PRESS_2(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_2);
    }),
    RELEASE_2(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_2);
    }),
    PRESS_3(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_3);
    }),
    RELEASE_3(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_3);
    }),
    PRESS_4(()->{
        KeyboardController.getInstance().pressKey(GLFW_KEY_4);
    }),
    RELEASE_4(()->{
        KeyboardController.getInstance().releaseKey(GLFW_KEY_4);
    }),
    PRESS_CTRL(()->{
        KeyboardController.getInstance().pressModifiers(GLFW_MOD_CONTROL);
    }),
    RELEASE_CTRL(()->{
        KeyboardController.getInstance().releaseModifiers(GLFW_MOD_CONTROL);
    }),
    PRESS_SHIFT(()->{
        KeyboardController.getInstance().pressModifiers(GLFW_MOD_SHIFT);
        KeyboardController.getInstance().pressKey(GLFW_KEY_LEFT_SHIFT);
    }),
    RELEASE_SHIFT(()->{
        KeyboardController.getInstance().releaseModifiers(GLFW_MOD_SHIFT);
        KeyboardController.getInstance().releaseKey(GLFW_KEY_LEFT_SHIFT);
    }),
    PRESS_SPACE(()->KeyboardController.getInstance().pressKey(GLFW_KEY_SPACE)),
    RELEASE_SPACE(()->KeyboardController.getInstance().releaseKey(GLFW_KEY_SPACE)),
    MOUSE_LEFT(()->MouseController.getInstance().mouseLeft().executeMove()),
    MOUSE_RIGHT(()->MouseController.getInstance().mouseRight().executeMove()),
    MOUSE_UP(()->MouseController.getInstance().mouseUp().executeMove()),
    MOUSE_DOWN(()->MouseController.getInstance().mouseDown().executeMove()),
    MOUSE_LEFT_BUTTON_PRESS(()->MouseController.getInstance().executeButton(GLFW_MOUSE_BUTTON_1, GLFW_PRESS)),
    MOUSE_LEFT_BUTTON_RELEASE(()->MouseController.getInstance().executeButton(GLFW_MOUSE_BUTTON_1, GLFW_RELEASE)),
    MOUSE_RIGHT_BUTTON_PRESS(()->MouseController.getInstance().executeButton(GLFW_MOUSE_BUTTON_2, GLFW_PRESS)),
    MOUSE_RIGHT_BUTTON_RELEASE(()->MouseController.getInstance().executeButton(GLFW_MOUSE_BUTTON_2, GLFW_RELEASE)),
    ;
    IGameTask gameTask;

    EnumDecompositionTask(IGameTask gameTask){
        this.gameTask = gameTask;
    }

}
