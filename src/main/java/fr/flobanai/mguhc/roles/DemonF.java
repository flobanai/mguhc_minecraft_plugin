package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class DemonF extends Role {

    public DemonF() {
        super("Demon Femelle");
        this.setTeam("Tartare");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyStrength(1);
    }

    @Override
    public void applyDayEffects(DataPlayer player) {
        player.applySpeed(0);
    }

    @Override
    public void applyNightEffects(DataPlayer player) {
        player.applySpeed(1);
    }
}