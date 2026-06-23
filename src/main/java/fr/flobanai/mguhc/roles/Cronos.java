package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Cronos extends Role {

    public Cronos() {
        super("Cronos");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(2);
    }
}