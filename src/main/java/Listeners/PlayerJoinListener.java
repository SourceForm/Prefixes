package Listeners;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final CustomPrefixPlugin plugin;

    public PlayerJoinListener(CustomPrefixPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.updatePlayerPrefix(event.getPlayer());
    }
}