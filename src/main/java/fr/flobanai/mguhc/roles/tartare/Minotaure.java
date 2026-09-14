package fr.flobanai.mguhc.roles.tartare;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;

public class Minotaure extends Role {

    public Minotaure() {
        super("Minotaure");
        this.setTeam(Team.TARTARE);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}
