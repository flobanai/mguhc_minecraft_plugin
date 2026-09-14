package fr.flobanai.mguhc.roles.olympe;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Hades extends Role {

    public Hades() {
        super("Hadès");
        this.setTeam(Team.OLYMPE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {}

    @Override
    public void applyDayEffects(DataPlayer player) {
        player.applyStrength(1);
        player.applyResistance(1);
    }

    @Override
    public void applyNightEffects(DataPlayer player) {
        player.applyStrength(0);
        player.applyResistance(2);
    }

    @Override
    public boolean isDeathAnnounced() {
        return false;
    }
}
