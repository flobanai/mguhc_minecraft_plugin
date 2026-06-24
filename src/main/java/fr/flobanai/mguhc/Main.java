package fr.flobanai.mguhc;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.flobanai.mguhc.roles.Zeus; // Import requis pour vérifier si le joueur est Zeus

public class Main extends JavaPlugin implements Listener {
    
    public static HashMap<UUID, DataPlayer> uhcPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        System.out.println("MG UHC Plugin Enabled");
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("mg").setExecutor(new MgCommand());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        DataPlayer uhcPlayer = new DataPlayer(player.getUniqueId());
        uhcPlayers.put(player.getUniqueId(), uhcPlayer);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            Player playerTrigger = (Player) event.getEntity();
            DataPlayer uhcPlayer = uhcPlayers.get(playerTrigger.getUniqueId());

            // --- NOUVELLE LOGIQUE POUR VÉRIFIER LE RÔLE ---
            // On vérifie si l'objet Rôle stocké dans DataPlayer est une "instance de" la classe Zeus
            if (uhcPlayer != null && uhcPlayer.getRole() instanceof Zeus && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
                playerTrigger.sendMessage("Dégâts de chute évités grâce à vos pouvoirs divins !");
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            DataPlayer uhcAttacker = uhcPlayers.get(attacker.getUniqueId());

            if (uhcAttacker != null) {
                event.setDamage(event.getDamage() * uhcAttacker.getDamageModifier());
            }
        }

        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            DataPlayer uhcVictim = uhcPlayers.get(victim.getUniqueId());

            if (uhcVictim != null) {
                event.setDamage(event.getDamage() * uhcVictim.getResistanceModifier());
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
}