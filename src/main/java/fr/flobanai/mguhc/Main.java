package fr.flobanai.mguhc;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.flobanai.mguhc.roles.Nyx;

public class Main extends JavaPlugin implements Listener {

    private static Main instance;
    public static HashMap<UUID, DataPlayer> uhcPlayers = new HashMap<>();
    
    public static boolean isDay = true;
    private int currentEpisode = 1;
    private BukkitTask gameTask;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("mg").setExecutor(new MgCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public void startGame() {
        currentEpisode = 1;
        isDay = true;
        
        getServer().getWorlds().get(0).setTime(0);
        getServer().broadcastMessage("§8[§6MgUHC§8] §fDébut de l'épisode §e" + currentEpisode + " §f! §eLe soleil se lève.");

        for (DataPlayer dp : uhcPlayers.values()) {
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
        }.runTaskTimer(this, 12000L, 12000L);
    }

    private void advanceTime() {
        isDay = !isDay;

        if (isDay) {
            currentEpisode++;
            getServer().getWorlds().get(0).setTime(0);
            getServer().broadcastMessage("§8[§6MgUHC§8] §fDébut de l'épisode §e" + currentEpisode + " §f! §eLe soleil se lève.");
        } else {
            getServer().getWorlds().get(0).setTime(13000);
            getServer().broadcastMessage("§8[§6MgUHC§8] §9La nuit tombe.");
        }

        for (DataPlayer dp : uhcPlayers.values()) {
            if (dp.getRole() != null) {
                if (isDay) {
                    dp.getRole().resetEpisodeUses();
                    dp.getRole().applyDayEffects(dp);
                } else {
                    dp.getRole().applyNightEffects(dp);
                }
            }
        }
    }

    public void forceNight() {
        if (isDay) {
            advanceTime();
            startGameLoop();
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        DataPlayer victimData = uhcPlayers.get(victim.getUniqueId());

        if (victimData != null && victimData.getRole() != null) {
            event.setDeathMessage(null);

            if (!"Hades".equalsIgnoreCase(victimData.getRole().getName())) {
                String team = victimData.getRole().getTeam();
                String color = "§f";

                if ("Tartare".equalsIgnoreCase(team)) {
                    color = "§c";
                } else if ("Olympe".equalsIgnoreCase(team)) {
                    color = "§e";
                } else if ("Nyx".equalsIgnoreCase(team)) {
                    color = "§5";
                }

                String line1 = "--------------------------------------";
                String line2 = victim.getName() + " est mort, il était " + color + victimData.getRole().getName();
                
                Player killer = victim.getKiller();
                boolean isHidden = false;

                if (killer != null) {
                    DataPlayer killerData = uhcPlayers.get(killer.getUniqueId());
                    if (killerData != null && killerData.getRole() instanceof Nyx && !isDay) {
                        isHidden = true;
                        Nyx nyxRole = (Nyx) killerData.getRole();
                        String hiddenLine2 = "§8" + victim.getName() + " a été englouti par les Ténèbres, il était " + color + victimData.getRole().getName();
                        nyxRole.addHiddenDeathMessage(hiddenLine2);
                    }
                }

                if (!isHidden) {
                    getServer().broadcastMessage(centerMessage(line1));
                    getServer().broadcastMessage(centerMessage(line2));
                    getServer().broadcastMessage(centerMessage(line1));
                }

                if (victimData.getRole() instanceof Nyx) {
                    Nyx nyxRole = (Nyx) victimData.getRole();
                    if (!nyxRole.getHiddenDeathMessages().isEmpty()) {
                        getServer().broadcastMessage(centerMessage(line1));
                        getServer().broadcastMessage(centerMessage("§5Les âmes capturées par Nyx sont libérées..."));
                        for (String msg : nyxRole.getHiddenDeathMessages()) {
                            getServer().broadcastMessage(centerMessage(msg));
                        }
                        getServer().broadcastMessage(centerMessage(line1));
                    }
                }
            }
        }
    }

    private String centerMessage(String message) {
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = (c == 'l' || c == 'L');
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;

        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + message;
    }
}