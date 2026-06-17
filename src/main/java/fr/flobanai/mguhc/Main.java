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

public class Main extends JavaPlugin implements Listener {
    
    public static HashMap<UUID, DataPlayer> uhcPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        System.out.println("MG UHC Plugin Enabled");
        getServer().getPluginManager().registerEvents(this, this);

        // Commandes
        getCommand("mgSetRole").setExecutor(new SetroleCommand());
        getCommand("mgGetRole").setExecutor(new GetroleCommand());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Création du profil UHC de test pour le joueur
        DataPlayer uhcPlayer = new DataPlayer(player.getUniqueId()); // Aucun rôle par défaut
        uhcPlayers.put(player.getUniqueId(), uhcPlayer);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            Player playerTrigger = (Player) event.getEntity();

            DataPlayer uhcPlayer = uhcPlayers.get(playerTrigger.getUniqueId());

            if (uhcPlayer != null && uhcPlayer.getRole() == Roles.ZEUS && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
                playerTrigger.sendMessage("Dégâts de chute évités");
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        
        // GESTION DE LA FORCE (Si l'attaquant est un joueur)
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            DataPlayer uhcAttacker = uhcPlayers.get(attacker.getUniqueId());

            if (uhcAttacker != null) {
                // On multiplie les dégâts de base par ton modificateur de force
                event.setDamage(event.getDamage() * uhcAttacker.getDamageModifier());
            }
        }

        // GESTION DE LA RÉSISTANCE (Si la victime est un joueur)
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            DataPlayer uhcVictim = uhcPlayers.get(victim.getUniqueId());

            if (uhcVictim != null) {
                // On multiplie les dégâts reçus par ton modificateur de résistance
                event.setDamage(event.getDamage() * uhcVictim.getResistanceModifier());
            }
        }
    }


    @org.bukkit.event.EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        DataPlayer uhcPlayer = uhcPlayers.get(player.getUniqueId());

        if (uhcPlayer != null) {
            // On remet la vitesse du joueur à la valeur de son rôle après sa résurrection
            uhcPlayer.updateRealSpeed(player);
        }
    }
}
