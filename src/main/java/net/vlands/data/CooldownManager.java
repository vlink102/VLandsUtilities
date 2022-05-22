package net.vlands.data;

import net.vlands.VLandsUtilities;
import net.vlands.data.player.PlayerDataManager;
import org.bukkit.entity.Player;

public class CooldownManager {

    private PlayerDataManager pdm;

    public CooldownManager(VLandsUtilities plugin) {
        this.pdm = plugin.getPlayerDataManager();
    }

    public long getRemainingTime(Player player, String cooldown) {
        return System.currentTimeMillis() - this.pdm.getPlayerData(player).getCooldownLastUse(cooldown);
    }

    public boolean isOnCooldown(Player player, String cooldown) {
        return  getRemainingTime(player, cooldown) <= 0;
    }

    public long getLastUse(Player player, String cooldown) {
        return this.pdm.getPlayerData(player).getCooldownLastUse(cooldown);
    }

    public void setLastUse(Player player, String cooldown, long time) {
        this.pdm.getPlayerData(player).setCooldownLastUse(cooldown, time);
    }

    public void setLastUseToNow(Player player, String cooldown) {
        this.pdm.getPlayerData(player).setCooldownLastUse(cooldown, System.currentTimeMillis());
    }

    public void resetCooldown(Player player, String cooldown) {
        setLastUse(player, cooldown,0L);
    }

}
