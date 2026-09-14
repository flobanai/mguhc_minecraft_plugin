package fr.flobanai.mguhc.roles.olympe;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import fr.flobanai.mguhc.Main;
import fr.flobanai.mguhc.game.GameConstants;
import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Ares extends Role {

    public Ares() {
        super("Ares");
        setTeam(Team.OLYMPE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }

    @Override
    public float getSituationalSpeed(Player player) {
        boolean hasAphrodite = false;
        boolean hasHephaistos = false;

        for (Entity entity : player.getNearbyEntities(15.0, 15.0, 15.0)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                DataPlayer targetData = Main.getInstance().getPlayerRegistry().get(target);

                if (targetData != null) {
                    if (targetData.getRole() instanceof Aphrodite) hasAphrodite = true;
                    if (targetData.getRole() instanceof Hephaistos) hasHephaistos = true;
                }
            }
        }

        if (hasAphrodite && !hasHephaistos) {
            return (float) GameConstants.BASE_SPEED;
        }

        return 0.0f;
    }
}
