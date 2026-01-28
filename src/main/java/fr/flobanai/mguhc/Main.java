package fr.flobanai.mguhc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {
    
    public static HashMap<UUID, Roles> playerRoles = new HashMap<>();

    @Override
    public void onEnable() {
        System.out.println("MG UHC Plugin Enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playerRoles.put(player.getUniqueId(), Roles.ZEUS);
        player.sendMessage(ChatColor.GOLD + "Tu es Zeus !");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
            Player playerTrigger = (Player) event.getEntity();

            Roles playerRole = playerRoles.get(playerTrigger.getUniqueId());

            if (playerRole == Roles.ZEUS && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
                playerTrigger.sendMessage("Dégâts de chute évités");
            }
        }
    }
}
