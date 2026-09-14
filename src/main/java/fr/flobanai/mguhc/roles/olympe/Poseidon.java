package fr.flobanai.mguhc.roles.olympe;

import org.bukkit.entity.Player;

import fr.flobanai.mguhc.game.GameConstants;
import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Poseidon extends Role {

    public Poseidon() {
        super("Poséidon");
        this.setTeam(Team.OLYMPE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }

    @Override
    public double getSituationalResistance(Player player) {
        if (player.getLocation().getBlock().isLiquid()) {
            return GameConstants.BASE_RESISTANCE;
        }
        return 0.0;
    }
}
