package fr.flobanai.mguhc.roles.olympe;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Zeus extends Role {

    public Zeus() {
        super("Zeus");
        this.setTeam(Team.OLYMPE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        // Zeus a Vitesse 1 de base
        player.applySpeed(1);
    }
}
