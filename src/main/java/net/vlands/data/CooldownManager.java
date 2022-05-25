package net.vlands.data;

import net.vlands.VLandsUtilities;
import net.vlands.data.player.PlayerDataManager;
import org.bukkit.entity.Player;

public class CooldownManager {
    private final PlayerDataManager pdm;

    public CooldownManager(VLandsUtilities plugin) {
        pdm = plugin.getPlayerDataManager();
    }

    public long getTimePassed(Player player, String cooldown) {
        return System.currentTimeMillis() - pdm.getPlayerData(player).getCooldownLastUse(cooldown);
    }

    public boolean isOnCooldown(Player player, String cooldown, long duration) {
        return getTimePassed(player, cooldown) < duration;
    }

    public long getRemainingTime(Player player, String cooldown, long duration) {
        return duration - getTimePassed(player, cooldown);
    }

    public long getLastUse(Player player, String cooldown) {
        return this.pdm.getPlayerData(player).getCooldownLastUse(cooldown);
    }

    public void forceSetLastUse(Player player, String cooldown, long time) {
        pdm.getPlayerData(player).setCooldownLastUse(cooldown, time);
    }

    public void setLastUseToNow(Player player, String cooldown) {
        pdm.getPlayerData(player).setCooldownLastUse(cooldown, System.currentTimeMillis());
    }

    public void resetCooldown(Player player, String cooldown) {
        forceSetLastUse(player, cooldown, 0L);
    }

    public boolean isIgnoringCooldowns(Player player) {
        return pdm.getPlayerData(player).isIgnoringCooldowns();
    }

    public void toggleIgnoringCooldowns(Player player) {
        pdm.getPlayerData(player).toggleIgnoreCooldown();
    }

    public void setIgnoringCooldowns(Player player, boolean bool) {
        pdm.getPlayerData(player).setIgnoringCooldowns(bool);
    }

}
