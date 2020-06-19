package com.github.rain_hon.danmumod;

import com.github.rain_hon.bilidanmu.TCPClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber(Dist.CLIENT)
@Mod("bilidanmu")
public class BiliDanmu {

    public final static Pattern startPattern = Pattern.compile("^roomid (\\d+)");
    public final static Pattern shutdownPattern = Pattern.compile("^close danmu");
    public static TCPClient danmuClient = null;
//    private static final KeyboardController keyboardController = new KeyboardController();

    private static final ActionTaskHandler actionTaskHandler = ActionTaskHandler.getInstance();

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

    }

    @SubscribeEvent
    public static void onTick(TickEvent event){
        if(actionTaskHandler.hasTask()){
            ArrayList<EnumDecompositionTask> tasks = actionTaskHandler.getTaskList();
            for(EnumDecompositionTask task : tasks){
                task.keyBoardTask.run();
            }
        }
    }
}