package com.github.rain_hon.danmumod;

import com.github.rain_hon.bilidanmu.ReceiveDataHandler;
import com.github.rain_hon.bilidanmu.TCPClient;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.command.CommandDimensions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.lwjgl.glfw.GLFW.*;

@Mod.EventBusSubscriber(Dist.CLIENT)
@Mod("bilidanmu")
public class BiliDanmu {

    public final static Pattern startPattern = Pattern.compile("^roomid (\\d+)");
    public final static Pattern shutdownPattern = Pattern.compile("^close danmu");
    public static TCPClient danmuClient = null;
    private static final KeyboardController keyboardController = new KeyboardController();

    public BiliDanmu() {
        LogManager.getLogger().info("testttttt");

        FMLJavaModLoadingContext.get().getModEventBus().register(this);

    }


    @SubscribeEvent
    public static void onChat(ClientChatEvent e){
        Matcher startMather = startPattern.matcher(e.getMessage());
        Matcher shutdownMather = shutdownPattern.matcher(e.getMessage());
        if(startMather.find()){

            if(danmuClient == null){
                danmuClient = new TCPClient();
            }else{
                danmuClient.shutdownService();
            }

            danmuClient.start(Long.parseLong(startMather.group(1)));
        }

        if(shutdownMather.find()){
            if(danmuClient != null){
                danmuClient.shutdownService();
            }
        }
//        Pattern.matches(pattern, e.getMessage());
//        CommandDispatcher<CommandSource> dispatcher =

    }

    @SubscribeEvent
    public static void onTick(TickEvent event){
        ReceiveDataHandler receiveDataHandler = ReceiveDataHandler.getInstance();
        if(receiveDataHandler.hasTask()){
//            System.out.println("tickEvent");
            ArrayList<String> tasks = receiveDataHandler.getTask_list();
            for(String task : tasks){
                switch (task){
                    case "向前":
                        keyboardController.setKey(GLFW_KEY_W).execute();
                        break;
                    case "向后":
                        keyboardController.setKey(GLFW_KEY_S).execute();
                        break;
                    case "向左":
                        keyboardController.setKey(GLFW_KEY_A).execute();
                        break;
                    case "向右":
                        keyboardController.setKey(GLFW_KEY_D).execute();
                        break;
                }
            }
        }
    }
}



//    Keyboard keyboard = new Keyboard(MinecraftClient.getInstance());
//                                    keyboard.onKey(MinecraftClient.getInstance().getWindow().getHandle(), GLFW_KEY_1, GLFW.glfwGetKeyScancode(GLFW_KEY_W), GLFW_PRESS, 0);