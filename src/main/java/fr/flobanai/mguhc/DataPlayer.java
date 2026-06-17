package fr.flobanai.mguhc;

import java.util.UUID;

import org.bukkit.entity.Player;


public class DataPlayer {
    private final UUID uuid;
    private Roles role;
    
    // Modificateurs sous forme de multiplicateurs (1.0 = 100% / pas de changement)
    private double damageModifier = 1.0;       // ex: 1.15 pour +15% de force
    private double resistanceModifier = 1.0;   // ex: 0.85 pour -15% de dégâts subis
    private float speedModifier = 1.0f;         // ex: 1.10f pour +10% de vitesse

    public DataPlayer(UUID uuid) {
        this.uuid = uuid;
        this.role = null; // Aucun rôle par défaut
    }

    public DataPlayer(UUID uuid, Roles role) {
        this.uuid = uuid;
        this.role = role;
        // On applique les valeurs de base du rôle à la création
        resetToRoleDefaults();
    }

    /**
     * Applique un niveau de force personnalisé au joueur
     * @param level Le niveau de force (1 pour Force I, 2 pour Force II, etc.)
     */
    public void applyStrength(int level) {
        // Pour Force 1 : 1.0 + (0.60 * 1) = 1.60
        // Pour Force 2 : 1.0 + (0.60 * 2) = 2.20
        this.damageModifier = 1.0 + (GameConstants.BASE_STRENGTH * level);
    }

    /**
     * Applique un niveau de résistance personnalisé au joueur
     */
    public void applyResistance(int level) {
        // La résistance soustrait des dégâts, on fait donc 1.0 - (constante)
        // Pour Résistance 1 : 1.0 - (0.20 * 1) = 0.80 (le joueur prend 80% des dégâts)
        // Pour Résistance 2 : 1.0 - (0.20 * 2) = 0.60 (le joueur prend 60% des dégâts)
        this.resistanceModifier = 1.0 - (GameConstants.BASE_RESISTANCE * level);
        
        // Sécurité pour éviter qu'un joueur devienne invincible ou gagne de la vie
        if (this.resistanceModifier < 0.0) {
            this.resistanceModifier = 0.0;
        }
    }

    public void applySpeed(int level) {
        // La vitesse augmente, on fait donc 1.0 + (constante)
        // Pour Vitesse 1 : 1.0 + (0.20 * 1) = 1.20 (le joueur se déplace 20% plus vite)
        // Pour Vitesse 2 : 1.0 + (0.20 * 2) = 1.40 (le joueur se déplace 40% plus vite)
        this.speedModifier = 1.0f + (GameConstants.BASE_SPEED * level);
    }

    /**
     * Applique la vitesse actuelle du profil au joueur en jeu
     */
    public void updateRealSpeed(Player player) {
        // On s'assure de ne pas dépasser les limites de Minecraft (entre -1.0 et 1.0)
        float finalSpeed = 0.2f * this.speedModifier;
        if (finalSpeed > 1.0f) finalSpeed = 1.0f;
        if (finalSpeed < -1.0f) finalSpeed = -1.0f;
        
        player.setWalkSpeed(finalSpeed);
    }

    /**
     * Définit les pourcentages par défaut propres à chaque divinité
     */
    public void resetToRoleDefaults() {
        switch (this.role) {
            case ZEUS:
                applySpeed(1); // Vitesse 1
                break;
            case POSEIDON:
                applyStrength(1); // Force 1
                break;
            case HADES:
                applyResistance(1); // Résistance 1
                break;
            case HERMES:
                applySpeed(1); // Vitesse 1
                break;
            case HERA:
                applyResistance(1); // Résistance 1
                break;
            case ARTEMIS:
                applySpeed(1); // Vitesse 1
                break;
            case THESEE:
                applyStrength(1); // Force 1
                break;
            case ARES:
                applyStrength(1); // Force 1
                break;
            case HEPHAISTOS:
                applyResistance(1); // Résistance 1
                break;
            case CRONOS:
                applySpeed(2); // Vitesse 2
                break;
            case MINOTAURE:
                applyStrength(1); // Force 1
                break;
            case DEMON_MALE:
                applySpeed(1); // Vitesse 1
                break;
            case DEMON_FEMALE:
                applyStrength(1); // Force 1
                break;
        }
    }

    // --- GETTERS ET SETTERS POUR LES MODIFICATIONS DYNAMIQUES ---
    
    public UUID getUuid() { return uuid; }
    
    public Roles getRole() { return role; }
    public void setRole(Roles role) { 
        this.role = role; 
        resetToRoleDefaults(); 
    }

    public double getDamageModifier() { return damageModifier; }
    public void setDamageModifier(double damageModifier) { this.damageModifier = damageModifier; }

    public double getResistanceModifier() { return resistanceModifier; }
    public void setResistanceModifier(double resistanceModifier) { this.resistanceModifier = resistanceModifier; }

    public float getSpeedModifier() { return speedModifier; }
    public void setSpeedModifier(float speedModifier) { this.speedModifier = speedModifier; }
}