package fr.flobanai.mguhc;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

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
                    Object role = getRoleFromMain(targetPlayer.getUniqueId());
                    if (role != null) {
                        sender.sendMessage("Le rôle de " + targetPlayer.getName() + " est : " + role.toString());
                    } else {
                        sender.sendMessage("Rôle introuvable pour " + targetPlayer.getName() + ".");
                    }
                } else {
                    sender.sendMessage("Joueur introuvable.");
                }
            } catch (Exception e) {
                sender.sendMessage("Erreur lors de la récupération du rôle du joueur.");
            }
        }
        else {
            Object role = getRoleFromMain(player.getUniqueId());
            if (role != null) {
                sender.sendMessage("Votre rôle actuel est : " + role.toString());
            } else {
                sender.sendMessage("Votre rôle actuel est introuvable.");
            }
        }

        return true;
    }

    private Object getRoleFromMain(UUID uuid) {
        try {
            Class<?> mainClass = Class.forName("fr.flobanai.mguhc.Main");
            Field field = mainClass.getDeclaredField("playerRoles");
            field.setAccessible(true);
            Object mapObj = field.get(null); // expect static field
            if (mapObj instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) mapObj;
                return map.get(uuid);
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}