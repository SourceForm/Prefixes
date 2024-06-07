package Commands;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class SetPrefixCommand implements CommandExecutor {

    private final CustomPrefixPlugin plugin;

    public SetPrefixCommand(CustomPrefixPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setprefix <player> <prefix>");
            return false;
        }

        Player player = plugin.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return false;
        }

        String prefix = ChatColor.translateAlternateColorCodes('&', args[1]);
        FileConfiguration config = plugin.getConfig();
        config.set("prefixes." + player.getUniqueId(), prefix);
        plugin.saveConfig();

        setPlayerPrefix(player, prefix);
        sender.sendMessage(ChatColor.GREEN + "Prefix set for " + player.getName() + " to " + prefix);
        return true;
    }

    private void setPlayerPrefix(Player player, String prefix) {
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());

        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }

        team.setPrefix(prefix + "&f");
        team.addEntry(player.getName());
    }
}