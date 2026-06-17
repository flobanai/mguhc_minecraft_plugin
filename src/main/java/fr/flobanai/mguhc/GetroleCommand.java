package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetroleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        // 1. Vérifier que c'est bien un joueur qui exécute la commande
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut utiliser cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if(args.length > 0 ){
            try{
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                if (targetPlayer != null) {
                    sender.sendMessage("Le rôle de " + targetPlayer.getName() + " est : " + Main.playerRoles.get(targetPlayer.getUniqueId()).name());
                } else {
                    sender.sendMessage("Joueur introuvable.");
                }
            } catch (Exception e) {
                sender.sendMessage("Erreur lors de la récupération du rôle du joueur.");
            }
        }
        else
            sender.sendMessage("Votre rôle actuel est : " + Main.playerRoles.get(player.getUniqueId()).name());

        return true;
    }
}