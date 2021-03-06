/*
 * SpawnChecker.
 * 
 * (c) 2014 alalwww
 * https://github.com/alalwww
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the contents of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt
 * 
 * この MOD は、Minecraft Mod Public License (MMPL) 1.0 の条件のもとに配布されています。
 * ライセンスの内容は次のサイトを確認してください。 http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package net.awairo.mcmod.spawnchecker;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.awairo.mcmod.common.v1.util.Fingerprint;
import net.awairo.mcmod.spawnchecker.client.mode.Mode;

/**
 * side proxy.
 * 
 * @author alalwww
 */
public abstract class Proxy
{
    protected static final Logger LOGGER = LogManager.getLogger(SpawnChecker.MOD_ID);

    /**
     * FML初期化前イベントを処理します.
     * 
     * @param event FMLPreInitializationEvent
     */
    protected void handleModEvent(FMLPreInitializationEvent event)
    {
        final Properties prop = event.getVersionProperties();

        LOGGER.info("initialization SpawnChecker version {} (hash:{})",
                prop.getProperty(SpawnChecker.MOD_ID + ".version"),
                prop.getProperty(SpawnChecker.MOD_ID + ".version.githash"));
    }

    /**
     * FML初期化イベントを処理します.
     * 
     * @param event FMLInitializationEvent
     */
    protected void handleModEvent(FMLInitializationEvent event)
    {
    }

    /**
     * FML初期化後イベントを処理します.
     * 
     * @param event FMLPostInitializationEvent
     */
    protected void handleModEvent(FMLPostInitializationEvent event)
    {
    }

    /**
     * 必要に応じてIMCメッセージを処理します.
     * 
     * @param message message
     */
    protected void handleMessage(IMCMessage message)
    {
        switch (message.key)
        {
            case SpawnChecker.IMC_HELLO:
                LOGGER.info("recived hello message from {}.", message.getSender());
                return;

            default:
                handleUnknownMessageKey(message);
        }
    }

    /**
     * 必要に応じて想定外のIMCメッセージを処理します.
     * 
     * @param message message
     */
    protected final void handleUnknownMessageKey(IMCMessage message)
    {
        // いえ、知らない子ですね・・・
        LOGGER.warn("unexpected key from {} (key:{})", message.getSender(), message.key);
    }

    /**
     * モードを登録します.
     * 
     * @param mode モード
     */
    protected void registerMode(Mode mode)
    {
        LOGGER.warn("mode registration is an unsupported operation.");
    }

    /**
     * FML署名違反イベントを処理します.
     * 
     * @param event FMLFingerprintViolationEvent
     */
    final void handleModEvent(FMLFingerprintViolationEvent event)
    {
        Fingerprint.HANDLER.handle(SpawnChecker.MOD_ID, event);
    }

    /**
     * IMCイベントを処理します.
     * 
     * @param event IMCEvent
     */
    final void handleModEvent(IMCEvent event)
    {
        LOGGER.debug("IMCEvent: {}", event.getMessages());

        for (IMCMessage message : event.getMessages())
        {
            // null値送信もできちゃうので、switchで落とさないように
            if (message.key == null)
            {
                handleUnknownMessageKey(message);
                continue;
            }

            handleMessage(message);
        }
    }

    //------------------------

    /**
     * イベントリスナーのマネージャーを登録します.
     * 
     * @param manager manager
     */
    protected static void registerEventListener(HandlerManager manager)
    {
        registerFMLEventListener(manager.newFmlEventListener());
        registerForgeEventListener(manager.newForgeEventListener());
    }

    /**
     * FMLイベントのリスナーを登録します.
     * 
     * @param eventListener イベントリスナーインスタンス
     */
    @Deprecated
    protected static void registerFMLEventListener(Object eventListener)
    {
        if (eventListener == null) return;
        FMLCommonHandler.instance().bus().register(eventListener);
    }

    /**
     * Forgeイベントのリスナーを登録します.
     * 
     * @param eventListener イベントリスナーインスタンス
     */
    protected static void registerForgeEventListener(Object eventListener)
    {
        if (eventListener == null) return;
        MinecraftForge.EVENT_BUS.register(eventListener);
    }
}
