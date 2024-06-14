package me.sourceform.prefixes;

import Commands.SetPrefixCommand;
import Listeners.ChatListener;
import Listeners.PlayerDeathListener;
import Listeners.PlayerJoinListener;
import org.bukkit.ChatColor;
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
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    @Override
    public void onDisable() {
        // Save config on disable to ensure any changes are saved
        saveConfig();
    }

    public void updateTab(Player player, String prefix, int deaths){
        String tabName = ChatColor.translateAlternateColorCodes('&', prefix)+ ChatColor.WHITE + " " + player.getName() + ChatColor.RESET + " | Deaths: " + deaths;
        player.setPlayerListName(tabName);

    }

}