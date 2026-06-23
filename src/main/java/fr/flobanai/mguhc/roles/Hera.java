package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Hera extends Role {

    public Hera() {
        super("Héra");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyResistance(1);
    }
}
