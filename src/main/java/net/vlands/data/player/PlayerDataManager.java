package net.vlands.data.player;

import net.vlands.VLandsUtilities;
import net.vlands.util.Callback;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class PlayerDataManager implements Listener {

    private VLandsUtilities plugin;

    public PlayerDataManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }


    public PlayerData getPlayerData(Player player) {
        return null;
    }

    /**
     * Used for player data for offline players
     * @param name
     * @return
     */
    public Callback<PlayerData> getPlayerData(String name) {
        return Callback.withResult(null);
    }

    /**
     * Used for player data for offline players
     * @param uuid
     * @return
     */
    public Callback<PlayerData> getPlayerData(UUID uuid) {
        return Callback.withResult(null);
    }

}
