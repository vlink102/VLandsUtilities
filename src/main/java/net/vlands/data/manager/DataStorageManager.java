package net.vlands.data.manager;

import net.vlands.data.player.PlayerData.PlayerDataSnapShot;

import java.util.*;

public abstract class DataStorageManager {

    public abstract void init();

    public abstract void saveNameAndUUID(String name, String uuid);

    public void savePlayerData(PlayerDataSnapShot data) {
        this.savePlayerDataMultiple(Collections.singleton(data));
    }

    public abstract void savePlayerDataMultiple(Set<PlayerDataSnapShot> data);

    public PlayerDataSnapShot getDataFromUUID(UUID uuid) {
        return this.getDataFromUUIDMultiple(Collections.singleton(uuid)).get(uuid);
    }

    public PlayerDataSnapShot getDataFromName(String name) {
        return this.getDataFromNameMultiple(Collections.singleton(name)).get(name);
    }

    public abstract Map<UUID, PlayerDataSnapShot> getDataFromUUIDMultiple(Set<UUID> uuids);

    public abstract Map<String, PlayerDataSnapShot> getDataFromNameMultiple(Set<String> names);

}
