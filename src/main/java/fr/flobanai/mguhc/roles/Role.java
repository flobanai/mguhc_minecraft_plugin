package fr.flobanai.mguhc.roles;

import org.bukkit.entity.Player;

import fr.flobanai.mguhc.player.DataPlayer;

public abstract class Role {
    private final String name;
    private Team team;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team newTeam) {
        this.team = newTeam;
    }

    public void applyDayEffects(DataPlayer player) {}

    public void applyNightEffects(DataPlayer player) {}

    public double getSituationalResistance(Player player) {
        return 0.0;
    }

    public double getSituationalStrength(Player player) {
        return 0.0;
    }

    public float getSituationalSpeed(Player player) {
        return 0.0f;
    }

    public abstract void applyBaseStats(DataPlayer player);

    public void usePower(Player player) {
        player.sendMessage("§cCe rôle n'a pas de pouvoir actif.");
    }

    public void resetEpisodeUses() {}

    /**
     * @return false si la mort de ce rôle ne doit jamais être annoncée (ex: Hadès).
     */
    public boolean isDeathAnnounced() {
        return true;
    }

    /**
     * Appelée sur le rôle du TUEUR lorsqu'une de ses victimes meurt, avant le broadcast normal.
     *
     * @return true si ce rôle a intercepté/masqué l'annonce (le broadcast normal est alors supprimé).
     */
    public boolean onKillerDeathBroadcast(DataPlayer killerData, String plainLine, String hiddenLine, boolean isNight) {
        return false;
    }

    /**
     * Appelée sur le rôle de la VICTIME juste après la mort, une fois le broadcast géré.
     */
    public void onOwnDeath() {}
}
