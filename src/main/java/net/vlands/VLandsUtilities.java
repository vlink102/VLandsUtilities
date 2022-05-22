package net.vlands;

import lombok.Getter;
import net.vlands.data.manager.DataStorageManager;
import net.vlands.data.manager.SQLiteStorageManager;
import net.vlands.data.player.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class VLandsUtilities extends JavaPlugin {

    @Getter
    private PlayerDataManager playerDataManager;
    @Getter
    private DataStorageManager dataStorageManager;

    @Override
    public void onEnable() {
        this.setupDataManagers();
    }

    private void setupDataManagers() {
        this.dataStorageManager = new SQLiteStorageManager(new File(this.getDataFolder(), "database.db"));
        this.playerDataManager = new PlayerDataManager(this);
        Bukkit.getPluginManager().registerEvents(this.playerDataManager, this);
    }
}
