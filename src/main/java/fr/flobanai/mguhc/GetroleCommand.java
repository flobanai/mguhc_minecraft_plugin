package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetroleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut utiliser cette commande !");
            return false;
        }

        Player player = (Player) sender;
        Player targetPlayer = player; // Par défaut, on cible le joueur qui tape la commande

        if(args.length > 0 ){
            targetPlayer = player.getServer().getPlayer(args[0]);
        }

        if (targetPlayer != null) {
            // On interroge directement la Map du Main
            DataPlayer dataPlayer = Main.uhcPlayers.get(targetPlayer.getUniqueId());
            
            if (dataPlayer != null && dataPlayer.getRole() != null) {
                sender.sendMessage("Le rôle est : " + dataPlayer.getRole().getName());
            } else {
                sender.sendMessage("Ce joueur n'a pas encore de rôle.");
            }
        } else {
            sender.sendMessage("Joueur introuvable.");
        }

        return true;
    }
}