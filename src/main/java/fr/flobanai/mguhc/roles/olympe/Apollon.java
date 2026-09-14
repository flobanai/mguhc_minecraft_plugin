package fr.flobanai.mguhc.roles.olympe;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Apollon extends Role {

    public Apollon() {
        super("Apollon");
        this.setTeam(Team.OLYMPE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
    }
}
