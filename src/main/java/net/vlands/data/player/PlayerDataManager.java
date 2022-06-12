package net.vlands.data.player;

import net.vlands.VLandsUtilities;
import net.vlands.data.player.PlayerData.PlayerDataSnapShot;
import net.vlands.util.internal.bukkit.ui.MinecraftName;
import net.vlands.util.internal.java.Callback;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager implements Listener {

    private final VLandsUtilities plugin;

    private final Map<UUID, PlayerData> uuidPlayerDataMap = new HashMap<>();
    private final Map<MinecraftName, UUID> nameUUIDMap = new HashMap<>();

    public PlayerDataManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    //this is temp and just for testing, i have to load data async
    @EventHandler
    private void onPlayerJoin(PlayerLoginEvent event) {
        //updates name and uuid database;
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            long past = System.currentTimeMillis();
            this.plugin.getDataStorageManager().saveNameAndUUID(event.getPlayer().getName(), event.getPlayer().getUniqueId());
            nameUUIDMap.put(new MinecraftName(event.getPlayer().getName()), event.getPlayer().getUniqueId());

            //data already loaded
            if (uuidPlayerDataMap.containsKey(event.getPlayer().getUniqueId())) return;


            PlayerDataSnapShot snapShot = this.plugin.getDataStorageManager().getDataFromUUID(event.getPlayer().getUniqueId());

            if (snapShot == null)
                snapShot = new PlayerDataSnapShot(event.getPlayer().getName(),
                        event.getPlayer().getUniqueId(), "defualt", 0.0, 0,
                        false,new HashMap<>(), null);
            PlayerData playerData = new PlayerData(event.getPlayer().getName(), event.getPlayer().getUniqueId(), snapShot);
            long present = System.currentTimeMillis();
            System.out.println("Loading data for " + event.getPlayer().getName() + " took " + (present-past) + "ms.");

            Bukkit.getScheduler().runTask(plugin, () -> this.uuidPlayerDataMap.put(event.getPlayer().getUniqueId(), playerData));
        });


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
        return this.uuidPlayerDataMap.get(player.getUniqueId());
    }

    /**
     * Used for player data for offline players
     * @param name Offline player name
     * @return Callback null
     */
    public Callback<PlayerData> getPlayerData(String name) {
        return Callback.withResult(null);
    }

    /**
     * Used for player data for offline players
     * @param uuid Offline player uuid
     * @return Callback null
     */
    public Callback<PlayerData> getPlayerData(UUID uuid) {
        return Callback.withResult(null);
    }

}
