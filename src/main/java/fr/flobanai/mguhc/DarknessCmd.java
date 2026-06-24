package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.roles.Nyx;

public class DarknessCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSeul un joueur peut utiliser cette commande !");
            return true;
        }

        Player player = (Player) sender;
        DataPlayer dp = Main.uhcPlayers.get(player.getUniqueId());

        if (dp != null && dp.getRole() != null) {
            if (dp.getRole() instanceof Nyx) {
                dp.getRole().usePower(player);
            } else {
                player.sendMessage("§cVous n'avez pas la capacité d'utiliser les Ténèbres.");
            }
        } else {
            player.sendMessage("§cVous n'avez pas de rôle !");
        }

        return true;
    }
}