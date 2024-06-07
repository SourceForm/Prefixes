package me.sourceform.prefixes;

import Commands.SetPrefixCommand;
import Listeners.ChatListener;
import Listeners.PlayerJoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPrefixPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Save default config if it doesn't exist
        saveDefaultConfig();

        // Register commands and events
        this.getCommand("setprefix").setExecutor(new SetPrefixCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Save config on disable to ensure any changes are saved
        saveConfig();
    }

    public void updatePlayerPrefix(Player player) {
        String prefix = getConfig().getString("prefixes." + player.getUniqueId(), "");
        player.setDisplayName(prefix + player.getName());
        player.setPlayerListName(prefix + player.getName());
    }

}