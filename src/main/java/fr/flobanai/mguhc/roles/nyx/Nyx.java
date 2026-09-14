package fr.flobanai.mguhc.roles.nyx;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.flobanai.mguhc.Main;
import fr.flobanai.mguhc.player.DataPlayer;
import fr.flobanai.mguhc.roles.Role;
import fr.flobanai.mguhc.roles.Team;
import fr.flobanai.mguhc.util.MessageUtils;

public class Nyx extends Role {

    private UUID playerUuid;
    private int darknessUses = 1;
    private final List<String> hiddenDeathMessages = new ArrayList<>();

    public Nyx() {
        super("Nyx");
        this.setTeam(Team.NYX);
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        this.playerUuid = player.getUuid();

        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = Bukkit.getPlayer(playerUuid);

                if (p == null) return;

                boolean hasNoArmor = true;

                for (org.bukkit.inventory.ItemStack item : p.getInventory().getArmorContents()) {
                    if (item != null && item.getType() != org.bukkit.Material.AIR) {
                        hasNoArmor = false;
                        break;
                    }
                }

                if (hasNoArmor && !Main.getInstance().getGameManager().isDay()) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                } else {
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 40L, 10L);
    }

    public UUID getPlayerUuid() {
        return this.playerUuid;
    }

    @Override
    public void applyDayEffects(DataPlayer player) {
        player.applyResistance(0);
    }

    @Override
    public void applyNightEffects(DataPlayer player) {
        player.applyResistance(1);
    }

    @Override
    public void usePower(Player player) {
        if (this.darknessUses <= 0) {
            player.sendMessage("§cVous avez déjà utilisé ce pouvoir durant cet épisode !");
            return;
        }

        this.darknessUses--;
        player.sendMessage("§5Les joueurs autour de vous sont aveuglés.");

        for (Entity entity : player.getNearbyEntities(15.0, 15.0, 15.0)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0, false, false));
                target.sendMessage("§8La nuit vous aveugle...");
            }
        }

        Location center = player.getLocation().add(0, 1, 0);

        for (int i = 0; i < 500; i++) {
            double offsetX = (Math.random() - 0.5) * 15.0;
            double offsetY = (Math.random() - 0.5) * 5.0;
            double offsetZ = (Math.random() - 0.5) * 15.0;

            Location particleLoc = new Location(
                player.getWorld(),
                center.getX() + offsetX,
                center.getY() + offsetY,
                center.getZ() + offsetZ
            );

            player.getWorld().playEffect(particleLoc, Effect.SMOKE, 4);
        }
    }

    @Override
    public void resetEpisodeUses() {
        this.darknessUses = 1;
    }

    @Override
    public boolean onKillerDeathBroadcast(DataPlayer killerData, String plainLine, String hiddenLine, boolean isNight) {
        if (!isNight) {
            return false;
        }

        this.hiddenDeathMessages.add(hiddenLine);

        killerData.applyStrength(1);
        new BukkitRunnable() {
            @Override
            public void run() {
                killerData.applyStrength(0);
            }
        }.runTaskLater(JavaPlugin.getPlugin(Main.class), 3600L);

        return true;
    }

    @Override
    public void onOwnDeath() {
        if (this.hiddenDeathMessages.isEmpty()) {
            return;
        }

        Server server = Bukkit.getServer();
        server.broadcastMessage(MessageUtils.centerMessage(MessageUtils.SEPARATOR));
        server.broadcastMessage(MessageUtils.centerMessage("§5Les âmes capturées par Nyx sont libérées..."));
        for (String msg : this.hiddenDeathMessages) {
            server.broadcastMessage(MessageUtils.centerMessage(msg));
        }
        server.broadcastMessage(MessageUtils.centerMessage(MessageUtils.SEPARATOR));
    }
}
