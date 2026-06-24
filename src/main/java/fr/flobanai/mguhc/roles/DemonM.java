package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class DemonM extends Role {

    public DemonM() {
        super("Demon_male");
        this.setTeam("Tartare");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applySpeed(1);
    }
}