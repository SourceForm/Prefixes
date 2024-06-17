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

import java.util.UUID;

 public class SetPrefixCommand implements CommandExecutor {
     private final CustomPrefixPlugin plugin;

     public SetPrefixCommand(CustomPrefixPlugin plugin) {
         this.plugin = plugin;
     }

     public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
         if (args.length < 2) {
             sender.sendMessage(ChatColor.RED + "Usage: /setprefix <player> <prefix>");
             return false;
         } else {
             Player player = this.plugin.getServer().getPlayer(args[0]);
             if (player == null) {
                 sender.sendMessage(ChatColor.RED + "Player not found!");
                 return false;
             } else {
                 String prefix = ChatColor.translateAlternateColorCodes('&', args[1] + " ");
                 FileConfiguration config = this.plugin.getConfig();
                 config.set("prefixes." + player.getUniqueId(), prefix);
                 this.plugin.saveConfig();
                 if (player == sender) {
                     this.setPlayerPrefix(player, prefix);
                     sender.sendMessage(ChatColor.GREEN + "Prefix set for " + player.getName() + " to " + prefix);
                     return true;
                 } else {
                     return false;
                 }
             }
         }
     }

     private void setPlayerPrefix(Player player, String prefix) {
         ScoreboardManager manager = this.plugin.getServer().getScoreboardManager();
         Scoreboard scoreboard = manager.getMainScoreboard();
         Team team = scoreboard.getTeam(player.getName());
         if (team == null) {
             team = scoreboard.registerNewTeam(player.getName());
         }

         team.setPrefix(prefix);
         team.addEntry(player.getName());
         String uuid = player.getUniqueId().toString();
         int deaths = this.plugin.getConfig().getInt("players." + uuid + ".deaths", 0);
         this.plugin.updateTab(player, prefix, deaths);
     }
 }