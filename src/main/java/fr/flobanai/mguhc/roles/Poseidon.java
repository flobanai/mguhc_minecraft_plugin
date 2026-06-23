package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Poseidon extends Role {

    public Poseidon() {
        super("Poséidon");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}