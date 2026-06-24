package fr.flobanai.mguhc.roles;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.DataPlayer;
import fr.flobanai.mguhc.GameConstants;
import fr.flobanai.mguhc.Main;

public class Ares extends Role {

    public Ares() {
        super("Arès");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        // Arès a Force 1 de base
        player.applyStrength(1);
    }

    @Override
    public double getSituationalSpeed(Player player) {
        boolean hasAphrodite = false;
        boolean hasHephaistos = false;
        for (Entity entity : player.getNearbyEntities(15.0, 15.0, 15.0)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                DataPlayer targetData = Main.uhcPlayers.get(target.getUniqueId());
                
                if (targetData != null) {
                    if (targetData.getRole() instanceof Aphrodite){
                        hasAphrodite = true;
                    }
                    if (targetData.getRole() instanceof Hephaistos){
                        hasHephaistos = true;
                    }
                    
                }
            }
        }

        if (hasAphrodite && !hasHephaistos){
            return GameConstants.BASE_SPEED;
        }
        
        return 0.0;
    }
}