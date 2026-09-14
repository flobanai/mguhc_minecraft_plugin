package fr.flobanai.mguhc.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut utiliser cette commande !");
            return false;
        }

        sender.sendMessage("Liste des commandes disponibles : \n\n");
        if (!sender.hasPermission("mguhc.admin")) {
            sender.sendMessage("");
        } else {
            sender.sendMessage("- getrole\n- setrole");
        }
        return true;
    }
}
