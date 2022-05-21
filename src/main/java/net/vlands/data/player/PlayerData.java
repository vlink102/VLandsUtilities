package net.vlands.data.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    @Getter
    private String name;
    @Getter
    private UUID uuid;
    @Getter@Setter
    private String killEffect;
    @Getter@Setter
    private double accuracy;
    @Getter@Setter
    private int skillPoints;

    private Map<String, Long> cooldownsLastUse;

    protected PlayerData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void setCooldownLastUse(String cooldown, long time) {

    }

    public long getCooldownLastUse(String cooldown) {
        if (this.cooldownsLastUse.containsKey(cooldown))
            return this.cooldownsLastUse.get(cooldown);
        return 0;
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
