package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Artemis extends Role {

    public Artemis() {
        super("Artémis");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(1);
    }
}