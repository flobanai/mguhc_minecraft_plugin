package fr.flobanai.mguhc;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MgCommand implements CommandExecutor {

    private final SetroleCommand setroleCmd = new SetroleCommand();
    private final GetroleCommand getroleCmd = new GetroleCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length == 0) {
            sender.sendMessage("§cUtilisation: /mg <setrole|getrole> [paramètres]");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        switch (subCommand) {
            case "setrole":
                return setroleCmd.onCommand(sender, command, label, subArgs);
                
            case "getrole":
                return getroleCmd.onCommand(sender, command, label, subArgs);
                
            default:
                sender.sendMessage("§cSous-commande inconnue. Utilisez setrole ou getrole.");
                return true;
        }
    }
}