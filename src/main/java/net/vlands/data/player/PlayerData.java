package net.vlands.data.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.vlands.effect.Effect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerData {

    @Getter
    private final String name;
    @Getter
    private final UUID uuid;
    @Getter
    @Setter
    private String killEffect;
    @Getter
    @Setter
    private double accuracy;
    @Getter
    @Setter
    private int skillPoints;
    @Getter
    @Setter
    private boolean ignoringCooldowns;

    private final Map<String, Long> cooldownsLastUse;

    protected PlayerData(String name, UUID uuid, PlayerDataSnapShot playerDataSnapShot) {
        this.name = name;
        this.uuid = uuid;
        this.skillPoints = playerDataSnapShot.skillPoints;
        this.accuracy = playerDataSnapShot.accuracy;
        this.ignoringCooldowns = playerDataSnapShot.ignoringCooldowns;
        this.killEffect = playerDataSnapShot.killEffect;
        this.cooldownsLastUse = new HashMap<>(playerDataSnapShot.cooldownsLastUse);
    }

    // SKILL POINTS SECTION
    public void addSkillPoints(int amount) {
        this.skillPoints += amount;
    }
    public void removeSkillPoints(int amount) {
        this.skillPoints -= amount;
    }
    public void resetSkillPoints() {
        this.skillPoints = 0;
    }

    // ACCURACY SECTION
    public void resetAccuracy() {
        this.accuracy = 0.00D;
    }

    // IGNORING COOLDOWNS SECTION
    public void toggleIgnoreCooldown() {
        this.ignoringCooldowns = !this.ignoringCooldowns;
    }
    public void setIgnoreCooldown(boolean ignoreCooldown) {
        this.ignoringCooldowns = ignoreCooldown;
    }
    public boolean isIgnoringCooldown() {
        return this.ignoringCooldowns;
    }

    // COOLDOWN SECTION
    public void setCooldownLastUse(String cooldown, long time) {
        if (time <= 0) this.cooldownsLastUse.remove(cooldown);
        else this.cooldownsLastUse.put(cooldown, time);
    }
    public long getCooldownLastUse(String cooldown) {
        if (this.cooldownsLastUse.containsKey(cooldown)) return this.cooldownsLastUse.get(cooldown);
        return 0;
    }

    public Set<String> getCooldownList() {
        return this.cooldownsLastUse.keySet();
    }
    public Map<String, Long> getCooldowns() {
        return this.cooldownsLastUse;
    }

    // KILL EFFECTS SECTION


    public PlayerDataSnapShot snapshot() {
        return new PlayerDataSnapShot(this);
    }

    /**
     * This class represents the data as it was at the point in time when the snapshot was created.
     */
    @AllArgsConstructor
    @Getter
    public static class PlayerDataSnapShot {

        private String name;
        private UUID uuid;
        private String killEffect;
        private double accuracy;
        private int skillPoints;
        private boolean ignoringCooldowns;
        private Map<String, Long> cooldownsLastUse;

        private PlayerDataSnapShot(PlayerData playerData) {
            this.name = playerData.name;
            this.uuid = playerData.uuid;
            this.killEffect = playerData.killEffect;
            this.accuracy = playerData.accuracy;
            this.skillPoints = playerData.skillPoints;
            this.ignoringCooldowns = playerData.ignoringCooldowns;
            this.cooldownsLastUse = new HashMap<>(playerData.cooldownsLastUse);
        }
    }

}
