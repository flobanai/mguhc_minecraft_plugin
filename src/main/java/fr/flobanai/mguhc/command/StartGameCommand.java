package fr.flobanai.mguhc.command;

import org.bukkit.command.CommandSender;

import fr.flobanai.mguhc.Main;

public class StartGameCommand implements SubCommand {

    @Override
    public String permission() {
        return "mguhc.admin";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Main.getInstance().getGameManager().startGame();
        return true;
    }
}
