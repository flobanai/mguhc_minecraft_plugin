package fr.flobanai.mguhc.command;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.Main;
import fr.flobanai.mguhc.player.DataPlayer;

public class GetRoleCommand implements SubCommand {

    @Override
    public String permission() {
        return "mguhc.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        Player targetPlayer = player;

        if (args.length > 0) {
            targetPlayer = player.getServer().getPlayer(args[0]);
        }

        if (targetPlayer != null) {
            DataPlayer dataPlayer = Main.getInstance().getPlayerRegistry().get(targetPlayer);

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

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return null;
        }
        return Collections.emptyList();
    }
}
