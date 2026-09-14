package fr.flobanai.mguhc.roles.olympe;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Thesee extends Role {

    public Thesee() {
        super("Thésée");
        this.setTeam(Team.OLYMPE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}
