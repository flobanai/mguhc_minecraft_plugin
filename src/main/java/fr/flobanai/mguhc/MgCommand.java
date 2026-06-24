package fr.flobanai.mguhc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class MgCommand implements TabExecutor {

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

    /**
     * Méthode gérant l'autocomplétion (la touche TAB en jeu)
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            List<String> subCommands = Arrays.asList("setrole", "getrole");
            
            for (String sub : subCommands) {
                if (sub.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(sub);
                }
            }
            return completions;
        }
        
        else if (args.length == 2) {
            return null;
        }
        
        else if (args.length == 3 && args[0].equalsIgnoreCase("setrole")) {
            List<String> roles = Arrays.asList("Zeus", "Ares", "Hades", "Hermes", "Artemis", "Thesee", "Poseidon", "Hera", "Apollon", "Aphrodite", "Hephaistos", "Cronos", "Minotaure", "DemonM", "DemonF");
            
            for (String role : roles) {
                if (role.toLowerCase().startsWith(args[2].toLowerCase())) {
                    completions.add(role);
                }
            }
            return completions;
        }
        return completions;
    }
}