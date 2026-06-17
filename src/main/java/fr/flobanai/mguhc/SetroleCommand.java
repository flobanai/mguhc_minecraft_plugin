package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetroleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut utiliser cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length > 1) {
            String roleName = args[1].toUpperCase();
            try {
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                if (targetPlayer != null) {
                    
                    // 1. On récupère le profil du joueur depuis la Map globale du Main
                    DataPlayer dataPlayer = Main.uhcPlayers.get(targetPlayer.getUniqueId());
                    
                    if (dataPlayer != null) {
                        Roles newRole = Roles.valueOf(roleName);
                        
                        // 2. On change le rôle (ce qui recalcule les multiplicateurs en interne)
                        dataPlayer.setRole(newRole);
                        
                        // 3. On applique RÉELLEMENT la nouvelle vitesse en jeu
                        dataPlayer.updateRealSpeed(targetPlayer);
                        
                        sender.sendMessage("Le rôle de " + targetPlayer.getName() + " a été changé en : " + roleName);
                    }
                } else {
                    sender.sendMessage("Joueur introuvable.");
                }
            } catch (IllegalArgumentException e) {
                player.sendMessage("Rôle invalide. Veuillez utiliser un rôle valide.");
            }
        } else {
            player.sendMessage("Veuillez spécifier un joueur et un rôle.");
        }

        return true;
    }
}