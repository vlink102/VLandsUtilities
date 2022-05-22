package net.vlands.util.cooldown;

import net.vlands.VLandsUtilities;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {
    private final VLandsUtilities plugin;

    public CooldownManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    private final HashMap<UUID, HashMap<String, Cooldown>> activeCooldowns = new HashMap<>();
    public HashMap<UUID, HashMap<String, Cooldown>> getActiveCooldowns() {
        return activeCooldowns;
    }

    /**
     * @param player Player to add the cooldown to
     * @param identifier Cooldown unique identifier
     * @param duration Length of cooldown
     * @param startTime Start of cooldown (System time)
     */
    public void addCooldown(Player player, String identifier, Long duration, Long startTime) {
        activeCooldowns.get(player.getUniqueId()).put(identifier, new Cooldown(duration, startTime));
    }

    /**
     * @param player Player to add the cooldown to
     * @param identifier Cooldown unique identifier
     * @param duration Length of addition
     */
    public void addToExistingCooldown(Player player, String identifier, Long duration) {
        HashMap<String, Cooldown> cooldowns = activeCooldowns.get(player.getUniqueId());
        cooldowns.put(identifier, new Cooldown(getRemaining(player, identifier, TimeUtil.TimeUnit.MILLISECONDS) + duration, cooldowns.get(identifier).getLastUsed()));
    }

    /**
     * @param player Player to remove the cooldown from
     * @param identifier Cooldown unique identifier
     */
    public void removeCooldown(Player player, String identifier) {
        activeCooldowns.get(player.getUniqueId()).remove(identifier);
    }

    /**
     * @param player Player to get the remaining time
     * @param identifier Cooldown unique identifier
     * @param timeUnit The preferred time unit
     * @return The remaining time of the cooldown in the preferred time unit
     */
    public Long getRemaining(Player player, String identifier, TimeUtil.TimeUnit timeUnit) {
        if (!activeCooldowns.containsKey(player.getUniqueId())) return 0L;
        if (!activeCooldowns.get(player.getUniqueId()).containsKey(identifier)) return 0L;

        Cooldown cooldown = activeCooldowns.get(player.getUniqueId()).get(identifier);

        return TimeUtil.convert((cooldown.getDuration() + cooldown.getLastUsed()) - System.currentTimeMillis(), timeUnit, 2);
    }

    /**
     * @param player Player to get cooldowns from
     * @return The player's active cooldowns
     */
    public HashMap<String, Cooldown> getActiveCooldowns(Player player) {
        return activeCooldowns.get(player.getUniqueId());
    }

    /**
     * @param player Player to get cooldowns from
     * @return If the player is on cooldown or not
     */
    public boolean isOnCooldown(Player player, String identifier) {
        return activeCooldowns.get(player.getUniqueId()).containsKey(identifier);
    }

}
