package fr.flobanai.mguhc.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.Main;
import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;

/**
 * Base commune pour les commandes de pouvoir actif de rôle (une commande nommée par rôle,
 * ex. /mg darkness pour Nyx). Chaque sous-classe se contente de déclarer le rôle requis
 * et son message de refus ; la vérification joueur/rôle/pouvoir est factorisée ici.
 */
public abstract class RoleCommand implements SubCommand {

    protected abstract Class<? extends Role> requiredRole();

    protected abstract String powerDeniedMessage();

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSeul un joueur peut utiliser cette commande !");
            return true;
        }

        Player player = (Player) sender;
        DataPlayer dp = Main.getInstance().getPlayerRegistry().get(player);

        if (dp == null || dp.getRole() == null) {
            player.sendMessage("§cVous n'avez pas de rôle !");
            return true;
        }

        if (!requiredRole().isInstance(dp.getRole())) {
            player.sendMessage(powerDeniedMessage());
            return true;
        }

        dp.getRole().usePower(player);
        return true;
    }
}
