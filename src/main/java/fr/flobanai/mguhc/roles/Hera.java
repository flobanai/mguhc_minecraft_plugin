package fr.flobanai.mguhc.roles;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.DataPlayer;
import fr.flobanai.mguhc.GameConstants;
import fr.flobanai.mguhc.Main;

public class Hera extends Role {

    public Hera() {
        super("Hera");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyResistance(1);
    }

    @Override
    public double getSituationalStrength(Player player) {
        for (Entity entity : player.getNearbyEntities(15.0, 15.0, 15.0)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                DataPlayer targetData = Main.uhcPlayers.get(target.getUniqueId());
                
                if (targetData != null && targetData.getRole() instanceof Zeus) {
                    return GameConstants.BASE_STRENGTH;
                }
            }
        }
        return 0.0;
    }
}