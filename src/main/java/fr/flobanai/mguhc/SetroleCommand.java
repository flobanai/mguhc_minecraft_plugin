package fr.flobanai.mguhc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetroleCommand implements CommandExecutor {

    // Local storage for player roles if Main.playerRoles is not available.
    private static final Map<UUID, Roles> playerRoles = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        // 1. Vérifier que c'est bien un joueur qui exécute la commande
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut utiliser cette commande !");
            return false;
        }

        Player player = (Player) sender;

        // 2. Logique de la commande (Exemple : envoyer un message)
        player.sendMessage("Commande exécutée avec succès !");
        
        // Exemple d'utilisation de tes rôles :
        // Main.playerRoles.put(player.getUniqueId(), Roles.HADES);
        if (args.length > 1) {
            String roleName = args[1].toUpperCase();
            try{
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                if (targetPlayer != null) {
                    // Store role in local map if Main.playerRoles is not present.
                    playerRoles.put(targetPlayer.getUniqueId(), Roles.valueOf(roleName));
                    sender.sendMessage("Le rôle de " + targetPlayer.getName() + " a été changé en : " + roleName);
                } else {
                    sender.sendMessage("Joueur introuvable.");
                }
            } catch (IllegalArgumentException e) {
                player.sendMessage("Rôle invalide. Veuillez utiliser un rôle valide.");
            }
        } else {
            player.sendMessage("Veuillez spécifier un joueur et un rôle.");
        }

        // 3. Retourner true car la commande a fonctionné
        return true;
    }
}