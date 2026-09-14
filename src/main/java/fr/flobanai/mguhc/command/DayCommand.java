package fr.flobanai.mguhc.command;

import org.bukkit.command.CommandSender;

import fr.flobanai.mguhc.Main;

public class DayCommand implements SubCommand {

    @Override
    public String permission() {
        return "mguhc.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (Main.getInstance().getGameManager().isDay()) {
            sender.sendMessage("§cIl fait déjà jour !");
            return true;
        }

        Main.getInstance().getGameManager().forceDay();
        sender.sendMessage("§aVous avez forcé le lever du jour.");
        return true;
    }
}
