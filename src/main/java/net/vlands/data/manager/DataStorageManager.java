package net.vlands.data.manager;

import net.vlands.data.player.PlayerData.PlayerDataSnapShot;

import java.util.*;

public abstract class DataStorageManager {

    public abstract void init();

    public abstract void saveNameAndUUID(String name, UUID uuid);

    public void savePlayerData(PlayerDataSnapShot data) {
        this.savePlayerDataMultiple(Collections.singleton(data));
    }

    public abstract void savePlayerDataMultiple(Set<PlayerDataSnapShot> data);

    public PlayerDataSnapShot getDataFromUUID(UUID uuid) {
        return this.getDataFromUUIDMultiple(Collections.singletonList(uuid)).get(uuid);
    }

    public PlayerDataSnapShot getDataFromName(String name) {
        name = name.toLowerCase();
        return this.getDataFromNameMultiple(Collections.singletonList(name)).get(name);
    }

    public abstract Map<UUID, PlayerDataSnapShot> getDataFromUUIDMultiple(List<UUID> uuids);

    public abstract Map<String, PlayerDataSnapShot> getDataFromNameMultiple(List<String> names);

}