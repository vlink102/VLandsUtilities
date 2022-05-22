package net.vlands.data.player;

import net.vlands.VLandsUtilities;
import net.vlands.util.Callback;
import net.vlands.util.MinecraftName;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.vlands.data.player.PlayerData.PlayerDataSnapShot;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager implements Listener {

    private VLandsUtilities plugin;

    private Map<UUID, PlayerData> uuidPlayerDataMap = new HashMap<>();
    private Map<MinecraftName, UUID> nameUUIDMap = new HashMap<>();

    public PlayerDataManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    //this is temp and just for testing, i have to load data async
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        //updates name and uuid database;
        this.plugin.getDataStorageManager().saveNameAndUUID(event.getPlayer().getName(), event.getPlayer().getUniqueId());
        nameUUIDMap.put(new MinecraftName(event.getPlayer().getName()), event.getPlayer().getUniqueId());

        //data already loaded
        if (uuidPlayerDataMap.containsKey(event.getPlayer().getUniqueId()))
            return;


        this.plugin.getDataStorageManager().getDataFromUUID(event.getPlayer().getUniqueId());
    }

    @EventHandler
    private void onPlayerLeave(PlayerQuitEvent event) {
        PlayerData playerData = this.getPlayerData(event.getPlayer());
        PlayerDataSnapShot snapShot = playerData.snapshot();

        this.uuidPlayerDataMap.remove(event.getPlayer().getUniqueId());
        this.nameUUIDMap.remove(new MinecraftName(event.getPlayer().getName()));

        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> this.plugin.getDataStorageManager().savePlayerData(snapShot));
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
