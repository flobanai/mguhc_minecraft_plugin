package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Thesee extends Role {

    public Thesee() {
        super("Thésée");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}