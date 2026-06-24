package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seul un joueur peut utiliser cette commande !");
            return false;
        }

        sender.sendMessage("Liste des commandes disponibles : \n\n");
        if (!sender.hasPermission("mguhc.admin")) {
            sender.sendMessage("");
        }
        else{
            sender.sendMessage("- getrole\n- setrole");
        }
        return true;
    }
}