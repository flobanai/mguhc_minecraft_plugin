package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class DemonM extends Role {

    public DemonM() {
        super("Demon Male");
        this.setTeam("Tartare");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(1);
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