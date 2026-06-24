package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Hades extends Role {

    public Hades() {
        super("Hadès");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyResistance(1);
        player.applyStrength(1);
    }
}