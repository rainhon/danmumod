package com.github.rain_hon.danmumod;

import net.minecraft.client.KeyboardListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;

public class MCReference {
    public static Long window = Minecraft.getInstance().getMainWindow().getHandle();
    public static KeyboardListener keyboardListener = Minecraft.getInstance().keyboardListener;
    public static MouseHelper mouseHelper = Minecraft.getInstance().mouseHelper;
}
