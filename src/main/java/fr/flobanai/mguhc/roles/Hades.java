package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Hades extends Role {

    public Hades() {
        super("Hadès");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {}

    @Override
    public void applyDayEffects(DataPlayer player) {
        player.applyStrength(1);
        player.applyResistance(1);
    }

    @Override
    public void applyNightEffects(DataPlayer player) {
        player.applyStrength(0);
        player.applyResistance(2);
    }
}