package fr.flobanai.mguhc.game;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.player.PlayerDataRegistry;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.util.MessageUtils;

public class DeathListener implements Listener {

    private final PlayerDataRegistry playerRegistry;
    private final GameManager gameManager;

    public DeathListener(PlayerDataRegistry playerRegistry, GameManager gameManager) {
        this.playerRegistry = playerRegistry;
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        DataPlayer victimData = playerRegistry.get(victim);

        if (victimData == null || victimData.getRole() == null) {
            return;
        }

        event.setDeathMessage(null);

        Role victimRole = victimData.getRole();
        if (!victimRole.isDeathAnnounced()) {
            return;
        }

        String color = victimRole.getTeam() != null ? victimRole.getTeam().getChatColor().toString() : "§f";
        String plainLine = victim.getName() + " est mort, il était " + color + victimRole.getName();
        String hiddenLine = "§8" + victim.getName() + " a été englouti par les Ténèbres, il était " + color + victimRole.getName();

        boolean intercepted = false;
        Player killer = victim.getKiller();
        if (killer != null) {
            DataPlayer killerData = playerRegistry.get(killer);
            if (killerData != null && killerData.getRole() != null) {
                intercepted = killerData.getRole().onKillerDeathBroadcast(killerData, plainLine, hiddenLine, !gameManager.isDay());
            }
        }

        if (!intercepted) {
            broadcastFramed(victim.getServer(), plainLine);
        }

        victimRole.onOwnDeath();
    }

    private void broadcastFramed(Server server, String line) {
        server.broadcastMessage(MessageUtils.centerMessage(MessageUtils.SEPARATOR));
        server.broadcastMessage(MessageUtils.centerMessage(line));
        server.broadcastMessage(MessageUtils.centerMessage(MessageUtils.SEPARATOR));
    }
}
