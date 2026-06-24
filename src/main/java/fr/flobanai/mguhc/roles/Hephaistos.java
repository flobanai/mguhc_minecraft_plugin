package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Hephaistos extends Role {

    public Hephaistos() {
        super("Héphaïstos");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyResistance(1);
    }
}