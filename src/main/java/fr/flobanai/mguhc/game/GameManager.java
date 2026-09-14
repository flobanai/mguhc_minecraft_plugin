package fr.flobanai.mguhc.game;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.player.PlayerDataRegistry;

public class GameManager {

    private final JavaPlugin plugin;
    private final PlayerDataRegistry playerRegistry;

    private boolean day = true;
    private int currentEpisode = 1;
    private BukkitTask gameTask;

    public GameManager(JavaPlugin plugin, PlayerDataRegistry playerRegistry) {
        this.plugin = plugin;
        this.playerRegistry = playerRegistry;
    }

    public boolean isDay() {
        return day;
    }

    public int getCurrentEpisode() {
        return currentEpisode;
    }

    public void startEffectsTicker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    DataPlayer dp = playerRegistry.get(player);
                    if (dp != null && dp.getRole() != null) {
                        dp.updateDynamicEffects(player);
                        dp.tickWaterTimer(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    public void startGame() {
        currentEpisode = 1;
        day = true;

        Server server = plugin.getServer();
        server.getWorlds().get(0).setTime(0);
        server.broadcastMessage("§8[§6MgUHC§8] §fDébut de l'épisode §e" + currentEpisode + " §f! §eLe soleil se lève.");

        for (DataPlayer dp : playerRegistry.all()) {
            if (dp.getRole() != null) {
                dp.getRole().resetEpisodeUses();
                dp.getRole().applyDayEffects(dp);
            }
        }

        startGameLoop();
    }

    private void startGameLoop() {
        if (gameTask != null) {
            gameTask.cancel();
        }

        gameTask = new BukkitRunnable() {
            @Override
            public void run() {
                advanceTime();
            }
        }.runTaskTimer(plugin, 12000L, 12000L);
    }

    private void advanceTime() {
        day = !day;

        Server server = plugin.getServer();
        if (day) {
            currentEpisode++;
            server.getWorlds().get(0).setTime(0);
            server.broadcastMessage("§8[§6MgUHC§8] §fDébut de l'épisode §e" + currentEpisode + " §f! §eLe soleil se lève.");
        } else {
            server.getWorlds().get(0).setTime(13000);
            server.broadcastMessage("§8[§6MgUHC§8] §9La nuit tombe.");
        }

        for (DataPlayer dp : playerRegistry.all()) {
            if (dp.getRole() != null) {
                if (day) {
                    dp.getRole().resetEpisodeUses();
                    dp.getRole().applyDayEffects(dp);
                } else {
                    dp.getRole().applyNightEffects(dp);
                }
            }
        }
    }

    public void forceNight() {
        if (day) {
            advanceTime();
            startGameLoop();
        }
    }

    public void forceDay() {
        if (!day) {
            advanceTime();
            startGameLoop();
        }
    }
}
