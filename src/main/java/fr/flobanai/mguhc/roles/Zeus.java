package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Zeus extends Role {

    public Zeus() {
        super("Zeus"); 
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        // Zeus a Vitesse 1 de base
        player.applySpeed(1); 
    }
}