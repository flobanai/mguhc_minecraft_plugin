package fr.flobanai.mguhc.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class MgCommand implements TabExecutor {

    private final Map<String, SubCommand> subCommands = new LinkedHashMap<>();

    public MgCommand() {
        subCommands.put("setrole", new SetRoleCommand());
        subCommands.put("getrole", new GetRoleCommand());
        subCommands.put("help", new HelpCommand());
        subCommands.put("start", new StartGameCommand());
        subCommands.put("night", new NightCommand());
        subCommands.put("day", new DayCommand());
        subCommands.put("darkness", new DarknessCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§cUtilisation: /mg <commande> [paramètres]");
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand == null) {
            sender.sendMessage("§cSous-commande inconnue. Faites /mg help pour plus d'informations.");
            return true;
        }

        String permission = subCommand.permission();
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
        return subCommand.execute(sender, subArgs);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (Map.Entry<String, SubCommand> entry : subCommands.entrySet()) {
                String permission = entry.getValue().permission();
                if ((permission == null || sender.hasPermission(permission)) && entry.getKey().startsWith(args[0].toLowerCase())) {
                    completions.add(entry.getKey());
                }
            }
            return completions;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = subCommands.get(subCommandName);

        if (subCommand == null) {
            return completions;
        }

        String permission = subCommand.permission();
        if (permission != null && !sender.hasPermission(permission)) {
            return completions;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
        return subCommand.tabComplete(sender, subArgs);
    }
}
