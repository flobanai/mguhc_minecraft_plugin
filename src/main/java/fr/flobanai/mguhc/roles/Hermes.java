package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Hermes extends Role {

    public Hermes() {
        super("Hermès");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(1);
    }
}