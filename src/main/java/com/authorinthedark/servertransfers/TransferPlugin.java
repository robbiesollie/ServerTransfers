package com.authorinthedark.servertransfers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class TransferPlugin extends JavaPlugin {
    FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        this.saveDefaultConfig();
        this.getConfig();
        getCommand("transfer").setExecutor(new TransferCommand(this));
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
