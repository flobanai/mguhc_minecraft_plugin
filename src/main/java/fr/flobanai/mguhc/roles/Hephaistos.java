package fr.flobanai.mguhc.roles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.flobanai.mguhc.DataPlayer;

public class Hephaistos extends Role {

    public Hephaistos() {
        super("Héphaïstos");
        this.setTeam("Olympe");
    }

    @Override
    public void applyBaseStats(DataPlayer player) {
        player.applyResistance(1);
        Player p = Bukkit.getPlayer(player.getUuid());
        if (p != null){
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
        }
    }
}