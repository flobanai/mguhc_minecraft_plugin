package fr.flobanai.mguhc.roles.olympe;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.Main;
import fr.flobanai.mguhc.game.GameConstants;
import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Hera extends Role {

    public Hera() {
        super("Hera");
        this.setTeam(Team.OLYMPE);
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
                DataPlayer targetData = Main.getInstance().getPlayerRegistry().get(target);

                if (targetData != null && targetData.getRole() instanceof Zeus) {
                    return GameConstants.BASE_STRENGTH;
                }
            }
        }
        return 0.0;
    }
}
