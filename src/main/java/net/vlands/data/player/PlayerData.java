package net.vlands.data.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    @Getter
    private final String name;
    @Getter
    private final UUID uuid;
    @Getter@Setter
    private String killEffect;
    @Getter@Setter
    private double accuracy;
    @Getter@Setter
    private int skillPoints;

    private final Map<String, Long> cooldownsLastUse;

    protected PlayerData(String name, UUID uuid,PlayerDataSnapShot playerDataSnapShot) {
        this.name = name;
        this.uuid = uuid;
        this.skillPoints = playerDataSnapShot.skillPoints;
        this.accuracy = playerDataSnapShot.accuracy;;
        this.killEffect = playerDataSnapShot.killEffect;
        this.cooldownsLastUse = new HashMap<>(playerDataSnapShot.cooldownsLastUse);
    }

    public void addSkillPoints(int amount) {
        this.skillPoints += amount;
    }

    public void removeSkillPoints(int amount) {
        this.skillPoints -= amount;
    }

    public void setCooldownLastUse(String cooldown, long time) {

    }

    public long getCooldownLastUse(String cooldown) {
        if (this.cooldownsLastUse.containsKey(cooldown))
            return this.cooldownsLastUse.get(cooldown);
        return 0;
    }

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
        private Map<String, Long> cooldownsLastUse;

        private PlayerDataSnapShot(PlayerData playerData) {
            this.name = playerData.name;
            this.uuid = playerData.uuid;
            this.killEffect = playerData.killEffect;
            this.accuracy = playerData.accuracy;
            this.skillPoints = playerData.skillPoints;
            this.cooldownsLastUse = new HashMap<>(playerData.cooldownsLastUse);
        }
    }

}
