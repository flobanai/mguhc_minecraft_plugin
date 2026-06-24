package fr.flobanai.mguhc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class MgCommand implements TabExecutor {

    private final SetroleCommand setroleCmd = new SetroleCommand();
    private final GetroleCommand getroleCmd = new GetroleCommand();
    private final HelpCmd helpCmd = new HelpCmd();
    
    private final Map<String, String> commandPermissions = new HashMap<>();

    public MgCommand() {
        commandPermissions.put("setrole", "mguhc.admin");
        commandPermissions.put("getrole", "mguhc.admin");
        commandPermissions.put("help", "none");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length == 0) {
            sender.sendMessage("§cUtilisation: /mg <setrole|getrole> [paramètres]");
            return true;
        }

        String subCommand = args[0].toLowerCase();
        String requiredPermission = commandPermissions.get(subCommand);

        if (requiredPermission != null && !requiredPermission.equals("none") && !sender.hasPermission(requiredPermission)) {
            sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        switch (subCommand) {
            case "setrole":
                return setroleCmd.onCommand(sender, command, label, subArgs);
                
            case "getrole":
                return getroleCmd.onCommand(sender, command, label, subArgs);
                
            case "help":
                return helpCmd.onCommand(sender, command, label, subArgs);
                
            default:
                sender.sendMessage("§cSous-commande inconnue. Faites /mg help pour plus d'informations.");
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (String sub : commandPermissions.keySet()) {
                String reqPerm = commandPermissions.get(sub);
                if ((reqPerm.equals("none") || sender.hasPermission(reqPerm)) && sub.startsWith(args[0].toLowerCase())) {
                    completions.add(sub);
                }
            }
            return completions;
        }

        String subCommand = args[0].toLowerCase();
        String reqPerm = commandPermissions.get(subCommand);

        if (reqPerm == null || (!reqPerm.equals("none") && !sender.hasPermission(reqPerm))) {
            return completions;
        }
        
        if (args.length == 2) {
            return null;
        }
        
        else if (args.length == 3 && subCommand.equals("setrole")) {
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