package fr.flobanai.mguhc.roles;

import org.bukkit.entity.Player;
import fr.flobanai.mguhc.DataPlayer;
import fr.flobanai.mguhc.GameConstants;

public class Poseidon extends Role {

    public Poseidon() {
        super("Poséidon");
        this.setTeam("Olympe");
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