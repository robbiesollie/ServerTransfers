package com.robbiesollie.servertransfers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.net.URISyntaxException;

public class TransferPlugin extends JavaPlugin {
    FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        this.saveDefaultConfig();
        this.getConfig();
        getCommand("transfer").setExecutor(new TransferCommand(this));
        var links = config.getConfigurationSection("links");
        if (links != null) {
            for (String link : links.getKeys(false)) {
                try {
                    String display = links.getString(link + ".display");
                    String uri = links.getString(link + ".uri");
                    Bukkit.getServerLinks().addLink(display,
                            new URI(uri));
                } catch (URISyntaxException e) {
                    getLogger().severe(e.getMessage());
                } catch (NullPointerException e) {
                    getLogger().severe("Failed to parse config");
                    getLogger().severe(e.getMessage());
                }
            }

        }

    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
