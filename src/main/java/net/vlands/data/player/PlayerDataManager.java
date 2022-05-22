package net.vlands.data.player;

import net.vlands.Main;
import net.vlands.util.Callback;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerDataManager implements Listener {

    private Main plugin;

    public PlayerDataManager(Main plugin) {
        this.plugin = plugin;
    }


    public PlayerData getPlayerData(Player player) {
        return null;
    }

    public Callback<PlayerData> getPlayerData(String name) {
        return Callback.withResult(null);
    }

    public Callback<PlayerData> getPlayerData(UUID uuid) {
        return Callback.withResult(null);
    }

}
