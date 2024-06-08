package Listeners;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

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

        // Increment death count
        int deaths = config.getInt("players." + uuid + ".deaths", 0);
        deaths++;
        config.set("players." + uuid + ".deaths", deaths);
        plugin.saveConfig();

        // Retrieve prefix
        String prefix = config.getString("prefixes." + uuid, "");

        // Update tab list name
        setPlayerPrefixAndTabName(player, prefix, deaths);
    }

    private void setPlayerPrefixAndTabName(Player player, String prefix, int deaths) {
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());

        if (team != null) {
            team.unregister();
        }

        team = scoreboard.registerNewTeam(player.getName());
        team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.RESET + " ");
        team.addEntry(player.getName());

        String tabName = ChatColor.translateAlternateColorCodes('&', prefix)+ ChatColor.WHITE + " " + player.getName() + ChatColor.RESET + " | Deaths: " + deaths;
        plugin.getLogger().info("Setting tab name: " + tabName);
        player.setPlayerListName(tabName);
    }
}
