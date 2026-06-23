package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class DemonF extends Role {

    public DemonF() {
        super("Demon_femelle");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}