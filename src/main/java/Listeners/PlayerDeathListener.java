package Listeners;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class PlayerDeathListener implements Listener {

    private final CustomPrefixPlugin plugin;

    public PlayerDeathListener(CustomPrefixPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        FileConfiguration config = plugin.getConfig();
        String uuid = player.getUniqueId().toString();
        int deaths = config.getInt("players." + uuid + ".deaths", 0);
        config.set("players." + uuid + ".deaths", deaths + 1);
        plugin.saveConfig();
    }
}
