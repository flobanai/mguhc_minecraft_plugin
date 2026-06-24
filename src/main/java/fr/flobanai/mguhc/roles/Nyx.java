package fr.flobanai.mguhc.roles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.flobanai.mguhc.DataPlayer;
import fr.flobanai.mguhc.Main;

public class Nyx extends Role implements Listener {

    public Nyx() {
        super("Nyx");
        this.setTeam("Nyx");
    }

    public Nyx(String name) {
        super(name);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
    }

    @Override 
    public void applyDayEffects(DataPlayer player){
        player.applyResistance(0);
    }

    @Override
    public void applyNightEffects(DataPlayer player){
        player.applyResistance(1);
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event){
        Player killer = event.getEntity().getKiller();
        DataPlayer dp = Main.uhcPlayers.get(killer.getUniqueId());

        if (dp.getRole().getName().equalsIgnoreCase("Nyx") && !Main.isDay){
            dp.applyStrength(1);

            new BukkitRunnable() {
                @Override
                public void run() {
                    dp.applyStrength(0);
                }
            }.runTaskLater(JavaPlugin.getPlugin(Main.class), 3600L);
        }
    }
}