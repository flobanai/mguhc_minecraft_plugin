package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

public class Zeus extends Role {

    public Zeus() {
        // On définit le nom d'affichage du rôle
        super("Zeus"); 
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        // Zeus a Vitesse 1 de base
        player.applySpeed(1); 
        
        // Si tu as d'autres choses à faire quand le joueur devient Zeus,
        // c'est ici que tu l'écriras !
    }
}