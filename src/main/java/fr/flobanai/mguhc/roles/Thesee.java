package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Thesee extends Role {

    public Thesee() {
        super("Thésée");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}