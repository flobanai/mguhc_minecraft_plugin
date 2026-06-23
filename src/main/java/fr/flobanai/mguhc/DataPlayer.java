package fr.flobanai.mguhc;

import java.util.UUID;

import org.bukkit.entity.Player;

import fr.flobanai.mguhc.roles.Role;

public class DataPlayer {
    private final UUID uuid;
    private Role role; // On utilise maintenant l'Objet Role et non plus l'Enum
    
    private double damageModifier = 1.0;
    private double resistanceModifier = 1.0;
    private float speedModifier = 1.0f;

    public DataPlayer(UUID uuid) {
        this.uuid = uuid;
        this.role = null; 
    }

    // --- METHODES D'APPLICATION DES STATS ---

    public void applyStrength(int level) {
        this.damageModifier = 1.0 + (GameConstants.BASE_STRENGTH * level);
    }

    public void applyResistance(int level) {
        this.resistanceModifier = 1.0 - (GameConstants.BASE_RESISTANCE * level);
        if (this.resistanceModifier < 0.0) this.resistanceModifier = 0.0;
    }

    public void applySpeed(int level) {
        this.speedModifier = 1.0f + (GameConstants.BASE_SPEED * level);
    }

    public void updateRealSpeed(Player player) {
        float finalSpeed = 0.2f * this.speedModifier;
        if (finalSpeed > 1.0f) finalSpeed = 1.0f;
        if (finalSpeed < -1.0f) finalSpeed = -1.0f;
        player.setWalkSpeed(finalSpeed);
    }

    // --- GETTERS ET SETTERS ---
    
    public UUID getUuid() { return uuid; }
    
    public Role getRole() { return role; }
    
    public void setRole(Role role) { 
        this.role = role; 
        
        // On remet les stats à zéro (100%) avant d'appliquer les nouvelles
        // pour éviter que les effets se cumulent si on change de rôle en cours de partie
        this.damageModifier = 1.0;
        this.resistanceModifier = 1.0;
        this.speedModifier = 1.0f;
        
        // On délègue la tâche au fichier du rôle concerné !
        if (this.role != null) {
            this.role.applyBaseStats(this);
        }
    }

    public double getDamageModifier() { return damageModifier; }
    public void setDamageModifier(double damageModifier) { this.damageModifier = damageModifier; }

    public double getResistanceModifier() { return resistanceModifier; }
    public void setResistanceModifier(double resistanceModifier) { this.resistanceModifier = resistanceModifier; }

    public float getSpeedModifier() { return speedModifier; }
    public void setSpeedModifier(float speedModifier) { this.speedModifier = speedModifier; }
}