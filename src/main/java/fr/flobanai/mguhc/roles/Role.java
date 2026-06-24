package fr.flobanai.mguhc.roles;

import org.bukkit.entity.Player;

import fr.flobanai.mguhc.DataPlayer;

/**
 * Classe de base (abstraite) pour tous les rôles.
 * Chaque rôle du jeu devra étendre (extends) cette classe.
 */
public abstract class Role {
    private final String name;
    private String team;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String newTeam) {
        if (newTeam.equalsIgnoreCase("Olympe") || 
            newTeam.equalsIgnoreCase("Tartare") || 
            newTeam.equalsIgnoreCase(this.name)) {
            
            this.team = newTeam;
            
        } else {
            System.out.println("[Attention] Tentative d'assigner une équipe invalide (" + newTeam + ") au rôle " + this.name);
        }
    }

    public abstract void applyBaseStats(DataPlayer player);
    public void applyDayEffects(DataPlayer player) {}
    public void applyNightEffects(DataPlayer player) {}
    public double getSituationalResistance(Player player) {return 0.0;}
    public double getSituationalStrength(Player player) {return 0.0;}
    public double getSituationalSpeed(Player player) {return 0.0;}
}