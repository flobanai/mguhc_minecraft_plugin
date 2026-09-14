package fr.flobanai.mguhc;

import org.bukkit.plugin.java.JavaPlugin;

import fr.flobanai.mguhc.command.MgCommand;
import fr.flobanai.mguhc.game.DeathListener;
import fr.flobanai.mguhc.game.GameManager;
import fr.flobanai.mguhc.player.PlayerDataRegistry;

public class Main extends JavaPlugin {

    private static Main instance;

    private PlayerDataRegistry playerRegistry;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        playerRegistry = new PlayerDataRegistry();
        gameManager = new GameManager(this, playerRegistry);

        getServer().getPluginManager().registerEvents(new DeathListener(playerRegistry, gameManager), this);
        getCommand("mg").setExecutor(new MgCommand());

        gameManager.startEffectsTicker();
    }

    public static Main getInstance() {
        return instance;
    }

    public PlayerDataRegistry getPlayerRegistry() {
        return playerRegistry;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
