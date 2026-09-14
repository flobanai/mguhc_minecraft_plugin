package fr.flobanai.mguhc.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.Main;
import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.RoleFactory;
import fr.flobanai.mguhc.roles.RoleType;

public class SetRoleCommand implements SubCommand {

    @Override
    public String permission() {
        return "mguhc.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSeul un joueur peut utiliser cette commande !");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 1) {
            String roleName = args[1].toUpperCase();

            Player targetPlayer = player.getServer().getPlayer(args[0]);
            if (targetPlayer != null) {
                DataPlayer dataPlayer = Main.getInstance().getPlayerRegistry().getOrCreate(targetPlayer);

                Role newRole = RoleFactory.getRoleFromString(roleName);

                if (newRole != null) {
                    dataPlayer.setRole(newRole);
                    dataPlayer.updateRealSpeed(targetPlayer);
                    sender.sendMessage("§aLe rôle de " + targetPlayer.getName() + " a été changé en : " + newRole.getName());
                } else {
                    player.sendMessage("§cRôle introuvable ! As-tu ajouté " + roleName + " dans RoleType.java ?");
                }

            } else {
                sender.sendMessage("§cJoueur introuvable.");
            }
        } else {
            player.sendMessage("§cVeuillez spécifier un joueur et un rôle. (Ex: /mg setrole Notch Zeus)");
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return null;
        }

        if (args.length == 2) {
            List<String> completions = new ArrayList<>();
            for (RoleType type : RoleType.values()) {
                if (type.getLabel().toLowerCase().startsWith(args[1].toLowerCase())) {
                    completions.add(type.getLabel());
                }
            }
            return completions;
        }

        return Collections.emptyList();
    }
}
