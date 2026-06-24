package fr.flobanai.mguhc;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    
    public static HashMap<UUID, DataPlayer> uhcPlayers = new HashMap<>();
    private org.bukkit.scheduler.BukkitRunnable timeTask;

    @Override
    public void onEnable() {
        System.out.println("MG UHC Plugin Enabled");
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("mg").setExecutor(new MgCommand());

        timeTask = new org.bukkit.scheduler.BukkitRunnable() {
            boolean wasDay = true;

            @Override
            public void run() {
                org.bukkit.World world = getServer().getWorlds().get(0);
                long time = world.getTime();
                
                boolean isDay = time < 12000 || time > 23000;

                if (isDay && !wasDay) {
                    wasDay = true;
                    getServer().broadcastMessage("§eLe jour se lève...");
                    
                    for (DataPlayer uhcPlayer : uhcPlayers.values()) {
                        if (uhcPlayer.getRole() != null) {
                            uhcPlayer.getRole().applyDayEffects(uhcPlayer);
                        }
                    }
                } else if (!isDay && wasDay) {
                    wasDay = false;
                    getServer().broadcastMessage("§8La nuit tombe...");
                    
                    for (DataPlayer uhcPlayer : uhcPlayers.values()) {
                        if (uhcPlayer.getRole() != null) {
                            uhcPlayer.getRole().applyNightEffects(uhcPlayer);
                        }
                    }
                }
            }
        };
        timeTask.runTaskTimer(this, 0L, 20L);

        new org.bukkit.scheduler.BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    DataPlayer dp = uhcPlayers.get(player.getUniqueId());
                    if (dp != null && dp.getRole() instanceof fr.flobanai.mguhc.roles.Poseidon) {
                        dp.tickWaterTimer(player);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L);

        new org.bukkit.scheduler.BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    DataPlayer dp = uhcPlayers.get(player.getUniqueId());
                    if (dp != null) {
                        dp.updateRealSpeed(player);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 40L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        DataPlayer uhcPlayer = new DataPlayer(player.getUniqueId());
        uhcPlayers.put(player.getUniqueId(), uhcPlayer);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            DataPlayer uhcAttacker = uhcPlayers.get(attacker.getUniqueId());

            if (uhcAttacker != null) {
                double rawDamage = event.getDamage();
                
                if (uhcAttacker.getStrengthLevel() > 0) {
                    rawDamage -= (3.0 * uhcAttacker.getStrengthLevel());
                }
                
                if (rawDamage < 1.0) rawDamage = 1.0;

                double finalStrength = uhcAttacker.getDamageModifier();
                
                event.setDamage(rawDamage * finalStrength);
            }
        }

        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            DataPlayer uhcVictim = uhcPlayers.get(victim.getUniqueId());

            if (uhcVictim != null) {
                double rawDamage = event.getDamage();

                if (uhcVictim.getResistanceLevel() > 0) {
                    double vanillaResistance = 1.0 - (0.2 * uhcVictim.getResistanceLevel());
                    if (vanillaResistance <= 0) vanillaResistance = 0.01;
                    rawDamage = rawDamage / vanillaResistance;
                }

                double finalResistance = uhcVictim.getResistanceModifier();

                event.setDamage(rawDamage * finalResistance);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        DataPlayer uhcPlayer = uhcPlayers.get(player.getUniqueId());

        if (uhcPlayer != null) {
            uhcPlayer.updateRealSpeed(player);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        DataPlayer dataDeadPlayer = uhcPlayers.get(player.getUniqueId());
        
        if (dataDeadPlayer.getRole().getName() != null){
            event.setDeathMessage(null);
            String team = dataDeadPlayer.getRole().getTeam();
            String color = "§f";

            if (team.equalsIgnoreCase("Tartare")) {
                color = "§c";
            } else if (team.equalsIgnoreCase("Olympe")) {
                color = "§e";
            } else if (team.equalsIgnoreCase("Nyx")) {
                color = "§5";
            }

            if (!dataDeadPlayer.getRole().getName().equalsIgnoreCase("Hadès")) {
                String line1 =  "==================================================\n\n";
                String line2 = player.getName() + " est mort, il était " + color + dataDeadPlayer.getRole().getName() + "\n";
                String line3 =  "==================================================\n";


                getServer().broadcastMessage(centerMessage(line1));
                getServer().broadcastMessage(centerMessage(line2));
                getServer().broadcastMessage(centerMessage(line3));

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
