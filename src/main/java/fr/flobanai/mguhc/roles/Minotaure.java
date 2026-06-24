package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Minotaure extends Role {

    public Minotaure() {
        super("Minotaure");
        this.setTeam("Tartare");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }
}