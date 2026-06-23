package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.roles.Role;

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
            
            Player targetPlayer = player.getServer().getPlayer(args[0]);
            if (targetPlayer != null) {
                
                DataPlayer dataPlayer = Main.uhcPlayers.get(targetPlayer.getUniqueId());
                if (dataPlayer != null) {
                    
                    // On utilise la Factory pour créer le bon objet Rôle
                    Role newRole = RoleFactory.getRoleFromString(roleName);
                    
                    if (newRole != null) {
                        dataPlayer.setRole(newRole);
                        dataPlayer.updateRealSpeed(targetPlayer);
                        sender.sendMessage("Le rôle de " + targetPlayer.getName() + " a été changé en : " + newRole.getName());
                    } else {
                        player.sendMessage("Rôle introuvable dans le système. Avez-vous créé la classe ?");
                    }
                }
            } else {
                sender.sendMessage("Joueur introuvable.");
            }
        } else {
            player.sendMessage("Veuillez spécifier un joueur et un rôle. (Ex: /mg_setrole Notch Zeus)");
        }

        return true;
    }
}