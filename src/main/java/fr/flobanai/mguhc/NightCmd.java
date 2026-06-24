package fr.flobanai.mguhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NightCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!Main.isDay) {
            sender.sendMessage("§cIl fait déjà nuit !");
            return true;
        }

        Main.getInstance().forceNight();
        sender.sendMessage("§aVous avez forcé la tombée de la nuit.");
        return true;
    }
}