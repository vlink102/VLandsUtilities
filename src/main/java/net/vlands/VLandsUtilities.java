package net.vlands;

import lombok.Getter;
import net.vlands.data.CooldownManager;
import net.vlands.data.manager.DataStorageManager;
import net.vlands.data.manager.SQLiteStorageManager;
import net.vlands.data.player.PlayerDataManager;
import net.vlands.effect.EffectsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class VLandsUtilities extends JavaPlugin {

    @Getter
    private PlayerDataManager playerDataManager;
    @Getter
    private DataStorageManager dataStorageManager;
    @Getter
    private CooldownManager cooldownManager;
    @Getter
    private EffectsManager effectsManager;

    @Override
    public void onEnable() {
        this.setupManagers();
    }

    private void setupManagers() {
        this.dataStorageManager = new SQLiteStorageManager(new File(this.getDataFolder(), "database.db"));
        this.dataStorageManager.init();

        this.playerDataManager = new PlayerDataManager(this);
        Bukkit.getPluginManager().registerEvents(this.playerDataManager, this);

        this.cooldownManager = new CooldownManager(this);

        this.effectsManager = new EffectsManager(this);
    }
}
