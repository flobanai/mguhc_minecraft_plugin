package fr.flobanai.mguhc.command;

import org.bukkit.command.CommandSender;

import fr.flobanai.mguhc.Main;

public class NightCommand implements SubCommand {

    @Override
    public String permission() {
        return "mguhc.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!Main.getInstance().getGameManager().isDay()) {
            sender.sendMessage("§cIl fait déjà nuit !");
            return true;
        }

        Main.getInstance().getGameManager().forceNight();
        sender.sendMessage("§aVous avez forcé la tombée de la nuit.");
        return true;
    }
}
