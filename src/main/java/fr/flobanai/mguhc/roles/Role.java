package fr.flobanai.mguhc.roles;

import fr.flobanai.mguhc.DataPlayer;

/**
 * Classe de base (abstraite) pour tous les rôles.
 * Chaque rôle du jeu devra étendre (extends) cette classe.
 */
public abstract class Role {
    private final String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Cette méthode sera personnalisée dans chaque fichier de rôle
     * pour appliquer les pourcentages spécifiques à l'attribution.
     */
    public abstract void applyBaseStats(DataPlayer player);
}