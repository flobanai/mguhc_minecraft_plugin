package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Ares extends Role {

    public Ares() {
        super("Arès");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        // Arès a Force 1 de base
        player.applyStrength(1);
    }
}