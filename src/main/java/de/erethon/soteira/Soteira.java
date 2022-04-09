package de.erethon.soteira;

import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.bedrock.misc.FileUtil;
import de.erethon.bedrock.plugin.EPlugin;
import de.erethon.bedrock.plugin.EPluginSettings;
import de.erethon.soteira.config.SConfig;
import de.erethon.soteira.listeners.PlayerListener;
import de.erethon.soteira.protection.Protection;
import de.erethon.soteira.protection.ProtectionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.io.File;
import java.io.IOException;

public final class Soteira extends EPlugin {

    private static Soteira instance;

    /* files */
    private File protectionsFile;
    private File sConfigFile;
    /* configs */
    private SConfig sConfig;
    /* instances */
    private ProtectionManager protectionManager;
    private PlayerListener playerListener;

    public Soteira() {
        settings = EPluginSettings.builder().build();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        loadCore();
    }

    public void loadCore() {
        loadFiles();
        loadFiles();
        loadConfigs();
        loadMessages();
        loadProtections();
        registerListeners();
        registerCommands();
    }

    public void loadFolders() {
        getDataFolder().mkdir();
    }

    public void loadFiles() {
        protectionsFile = new File(getDataFolder(), "protections.dat");
        sConfigFile = FileUtil.initFile(this, new File(getDataFolder(), "config.yml"), "config.yml");
    }

    public void loadConfigs() {
        sConfig = new SConfig(sConfigFile);
    }

    public void loadMessages() {
        reloadMessageHandler();
        getMessageHandler().setDefaultLanguage(sConfig.getLanguage());
        getBedrockMessageHandler().setDefaultLanguage(sConfig.getLanguage());
    }

    public void loadProtections() {
        try {
            protectionManager = new ProtectionManager(protectionsFile);
        } catch (IOException e) {
            MessageUtil.log(this, "<red>Couldn't load protections:");
            e.printStackTrace();
            MessageUtil.log(this, "<red>Disabling Soteira...");
        }
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(playerListener = new PlayerListener(), this);
    }

    public void registerCommands() {

    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        protectionManager.close();
    }

    /* resolving */

    /**
     * @return true if the event should be cancelled, false otherwise
     */
    public boolean resolveProtectedInteraction(Player player, Protection protection) {
        return true; // todo: add functionality
    }

    /* getter */

    public SConfig getSConfig() {
        return sConfig;
    }

    public ProtectionManager getProtectionManager() {
        return protectionManager;
    }

    public PlayerListener getPlayerListener() {
        return playerListener;
    }

    public static Soteira inst() {
        return instance;
    }
}
