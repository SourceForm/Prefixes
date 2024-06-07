package Listeners;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final CustomPrefixPlugin plugin;


    public ChatListener(CustomPrefixPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        String prefix = config.getString("prefixes." + player.getUniqueId(), "");
        String message = e.getMessage();
        e.setFormat(prefix + player.getName() + ChatColor.WHITE + ": " + message);
        }
    }
