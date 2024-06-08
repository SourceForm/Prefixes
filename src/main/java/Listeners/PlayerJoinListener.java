package Listeners;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class PlayerJoinListener implements Listener {

    private final CustomPrefixPlugin plugin;

    public PlayerJoinListener(CustomPrefixPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        String uuid = player.getUniqueId().toString();
        String prefix = config.getString("prefixes." + uuid, "");
        int deaths = config.getInt("players." + uuid + ".deaths", 0);
        setPlayerPrefixAndTabName(player, prefix, deaths);
    }

    private void setPlayerPrefixAndTabName(Player player, String prefix, int deaths) {
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());
        //Reset tab menu name
        if (team != null) {
            team.unregister();
        }
        //Re-set prefix
        team = scoreboard.registerNewTeam(player.getName());
        team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.RESET + " ");
        team.addEntry(player.getName());
        //Update tab menu name
        String tabName = ChatColor.translateAlternateColorCodes('&', prefix)+ ChatColor.WHITE + " " + player.getName() + ChatColor.RESET + " | Deaths: " + deaths;
        player.setPlayerListName(tabName);
    }
}
