package fr.flobanai.mguhc.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerDataRegistry {

    private final Map<UUID, DataPlayer> players = new HashMap<>();

    public DataPlayer get(UUID uuid) {
        return players.get(uuid);
    }

    public DataPlayer get(Player player) {
        return players.get(player.getUniqueId());
    }

    public DataPlayer getOrCreate(Player player) {
        return players.computeIfAbsent(player.getUniqueId(), DataPlayer::new);
    }

    public Collection<DataPlayer> all() {
        return players.values();
    }
}
