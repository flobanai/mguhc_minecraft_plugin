package fr.flobanai.mguhc;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.flobanai.mguhc.roles.Role;

public class DataPlayer {
    private final UUID uuid;
    private Role role;
    private int waterTimer = 0;
    
    private double damageModifier = 1.0;
    private double resistanceModifier = 1.0;
    private float speedModifier = 1.0f;

    private int strengthLevel = 0;
    private int resistanceLevel = 0;
    private int speedLevel = 0;

    private int baseStrength = 0;
    private int baseResistance = 0;
    private int baseSpeed = 0;

    public DataPlayer(UUID uuid) {
        this.uuid = uuid;
        this.role = null; 
    }

    public void applyStrength(int level) {
        this.baseStrength = level;
        forceUpdate(org.bukkit.Bukkit.getPlayer(this.uuid));
    }

    public void applyResistance(int level) {
        this.baseResistance = level;
        forceUpdate(org.bukkit.Bukkit.getPlayer(this.uuid));
    }

    public void applySpeed(int level) {
        this.baseSpeed = level;
        forceUpdate(org.bukkit.Bukkit.getPlayer(this.uuid));
    }

    public void updateDynamicEffects(Player player) {
        if (player == null) return;

        int totalStr = this.baseStrength;
        int totalRes = this.baseResistance;
        int totalSpd = this.baseSpeed;

        if (this.role != null) {
            totalStr += (int) Math.round(this.role.getSituationalStrength(player) / GameConstants.BASE_STRENGTH);
            totalRes += (int) Math.round(this.role.getSituationalResistance(player) / GameConstants.BASE_RESISTANCE);
            totalSpd += (int) Math.round(this.role.getSituationalSpeed(player) / GameConstants.BASE_SPEED);
        }

        if (totalStr != this.strengthLevel) {
            this.strengthLevel = totalStr;
            this.damageModifier = 1.0 + (GameConstants.BASE_STRENGTH * totalStr);
            applyOrRemoveEffect(player, PotionEffectType.INCREASE_DAMAGE, totalStr);
        }

        if (totalRes != this.resistanceLevel) {
            this.resistanceLevel = totalRes;
            this.resistanceModifier = 1.0 - (GameConstants.BASE_RESISTANCE * totalRes);
            if (this.resistanceModifier < 0.0) this.resistanceModifier = 0.0;
            applyOrRemoveEffect(player, PotionEffectType.DAMAGE_RESISTANCE, totalRes);
        }

        if (totalSpd != this.speedLevel) {
            this.speedLevel = totalSpd;
            this.speedModifier = 1.0f + (GameConstants.BASE_SPEED * totalSpd);
            applyOrRemoveEffect(player, PotionEffectType.SPEED, totalSpd);
            
            float vanillaMultiplier = 1.0f + (0.2f * totalSpd);
            float targetSpeed = 0.2f * this.speedModifier;
            float finalSpeed = targetSpeed / vanillaMultiplier;
            
            if (finalSpeed > 1.0f) finalSpeed = 1.0f;
            if (finalSpeed < -1.0f) finalSpeed = -1.0f;
            player.setWalkSpeed(finalSpeed);
        }
    }

    private void forceUpdate(Player player) {
        if (player != null) {
            this.strengthLevel = -1; 
            this.resistanceLevel = -1;
            this.speedLevel = -1;
            updateDynamicEffects(player);
        }
    }

    private void applyOrRemoveEffect(Player p, PotionEffectType type, int level) {
        if (level > 0) {
            p.removePotionEffect(type);
            p.addPotionEffect(new PotionEffect(type, Integer.MAX_VALUE, level - 1, false, false), true);
        } else {
            p.removePotionEffect(type);
        }
    }

    public void tickWaterTimer(Player player) {
        if (player.getLocation().getBlock().isLiquid()) {
            waterTimer++;
            
            if (waterTimer >= 30) {
                double maxHealth = player.getMaxHealth();
                if (player.getHealth() < maxHealth) {
                    player.setHealth(Math.min(maxHealth, player.getHealth() + 2.0));
                }
                waterTimer = 0;
            }
        } else {
            waterTimer = 0;
        }
    }

    public UUID getUuid() { return uuid; }
    
    public Role getRole() { return role; }
    
    public void setRole(Role role) { 
        this.role = role; 
        
        this.baseStrength = 0;
        this.baseResistance = 0;
        this.baseSpeed = 0;
        
        if (this.role != null) {
            this.role.applyBaseStats(this);
        }
    }

    public double getDamageModifier() { return damageModifier; }
    public double getResistanceModifier() { return resistanceModifier; }
    public float getSpeedModifier() { return speedModifier; }

    public int getStrengthLevel() { return strengthLevel; }
    public int getResistanceLevel() { return resistanceLevel; }
    public int getSpeedLevel() { return speedLevel; }

    public void updateRealSpeed(Player player) {
        updateDynamicEffects(player);
    }
}