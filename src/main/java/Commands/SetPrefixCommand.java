package Commands;

import me.sourceform.prefixes.CustomPrefixPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return false;
        }

        String prefix = ChatColor.translateAlternateColorCodes('&', args[1]);

        plugin.getConfig().set("prefixes." + player.getUniqueId(), prefix);
        plugin.saveConfig();

        plugin.updatePlayerPrefix(player);

        sender.sendMessage(ChatColor.GREEN + "Prefix set for " + player.getName() + " to " + prefix);
        return true;
    }
}