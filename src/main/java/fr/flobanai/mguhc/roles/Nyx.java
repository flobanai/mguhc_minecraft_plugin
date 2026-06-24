package fr.flobanai.mguhc.roles;

import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
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
    private int darknessUses = 1;

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

    @Override
    public void usePower(Player player) {
        if (this.darknessUses <= 0) {
            player.sendMessage("§cVous avez déjà utilisé ce pouvoir durant cet épisode !");
            return;
        }

        this.darknessUses--;
        player.sendMessage("§5Les joueurs autour de vous sont aveuglés.");

        // Parcourt toutes les entités dans un rayon de 15 blocs (15, 15, 15)
        for (Entity entity : player.getNearbyEntities(15.0, 15.0, 15.0)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                
                // Cécité I (amplifier = 0) pendant 20 secondes (400 ticks)
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, false, false));
                target.sendMessage("§8La nuit vous aveugle...");
            }
        }

        Location center = player.getLocation().add(0, 1, 0);

        for (int i = 0; i < 75; i++) {
            double offsetX = (Math.random() - 0.5) * 15.0; // Étendue sur X
            double offsetY = (Math.random() - 0.5) * 5.0;  // Étendue sur Y
            double offsetZ = (Math.random() - 0.5) * 15.0; // Étendue sur Z
            
            Location particleLoc = new Location(
                player.getWorld(), 
                center.getX() + offsetX, 
                center.getY() + offsetY, 
                center.getZ() + offsetZ
            );
            player.getWorld().playEffect(particleLoc, Effect.SMOKE, 4);
        }
    }

    @Override
    public void resetEpisodeUses() {
        this.darknessUses = 1;
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