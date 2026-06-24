package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Cronos extends Role {

    public Cronos() {
        super("Cronos");
        this.setTeam("Tartare");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(2);
    }

    @Override
    public void applyDayEffects(DataPlayer player) {
        player.applyStrength(0);
    }

    @Override
    public void applyNightEffects(DataPlayer player) {
        player.applyStrength(1);
    }

}