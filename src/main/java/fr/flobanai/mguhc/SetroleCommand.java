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
            sender.sendMessage("§cSeul un joueur peut utiliser cette commande !");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 1) {
            String roleName = args[1].toUpperCase();
            
            Player targetPlayer = player.getServer().getPlayer(args[0]);
            if (targetPlayer != null) {
                
                DataPlayer dataPlayer = Main.uhcPlayers.get(targetPlayer.getUniqueId());
                
                if (dataPlayer == null) {
                    dataPlayer = new DataPlayer(targetPlayer.getUniqueId());
                    Main.uhcPlayers.put(targetPlayer.getUniqueId(), dataPlayer);
                }
                    
                Role newRole = RoleFactory.getRoleFromString(roleName);
                
                if (newRole != null) {
                    dataPlayer.setRole(newRole);
                    dataPlayer.updateRealSpeed(targetPlayer);
                    sender.sendMessage("§aLe rôle de " + targetPlayer.getName() + " a été changé en : " + newRole.getName());
                } else {
                    player.sendMessage("§cRôle introuvable ! As-tu ajouté " + roleName + " dans RoleFactory.java ?");
                }
                
            } else {
                sender.sendMessage("§cJoueur introuvable.");
            }
        } else {
            player.sendMessage("§cVeuillez spécifier un joueur et un rôle. (Ex: /mg setrole Notch Zeus)");
        }

        return true;
    }
}