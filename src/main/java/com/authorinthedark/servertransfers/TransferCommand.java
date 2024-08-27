package com.authorinthedark.servertransfers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TransferCommand implements CommandExecutor, TabCompleter {
    TransferPlugin plugin;

    public TransferCommand(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        FileConfiguration config = plugin.getConfig();


        String host = config.getString("servers." + args[0] + ".ip");
        int port = config.getInt("servers." + args[0] + ".port");
        if (commandSender instanceof Player p) {
            p.transfer(host, port);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        FileConfiguration config = plugin.getConfig();
        Map<String, Object> servers = config.getConfigurationSection("servers").getValues(false);
        return new LinkedList<>(servers.keySet());
    }
}