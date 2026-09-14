package fr.flobanai.mguhc.roles.tartare;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Cronos extends Role {

    public Cronos() {
        super("Cronos");
        this.setTeam(Team.TARTARE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(2);
    }

    @Override
    public void applyDayEffects(DataPlayer player) {
        player.applyStrength(0);
    }

    @Override
    public void applyNightEffects(DataPlayer player) {
        player.applyStrength(1);
    }

}
