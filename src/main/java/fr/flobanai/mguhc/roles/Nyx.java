package fr.flobanai.mguhc.roles;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.flobanai.mguhc.DataPlayer;
import fr.flobanai.mguhc.Main;

public class Nyx extends Role implements Listener {

    private UUID playerUuid;

    public Nyx() {
        super("Nyx");
        this.setTeam("Nyx");
    }

    public Nyx(String name) {
        super(name);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        this.playerUuid = player.getUuid();

        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = org.bukkit.Bukkit.getPlayer(playerUuid);
                
                if (p == null) return; 

                boolean hasNoArmor = true;
                
                for (org.bukkit.inventory.ItemStack item : p.getInventory().getArmorContents()){
                    if (item != null && item.getType() != org.bukkit.Material.AIR) {
                        hasNoArmor = false;
                        break;
                    }
                }
                
                if (hasNoArmor && !Main.isDay){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
                }
                else {
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 40L, 10L);
    }

    public UUID getPlayerUuid() {
        return this.playerUuid;
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
        
        if (killer != null) {
            DataPlayer dp = Main.uhcPlayers.get(killer.getUniqueId());

            if (dp != null && dp.getRole() != null && dp.getRole().getName().equalsIgnoreCase("Nyx") && !Main.isDay){
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
}