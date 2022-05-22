package net.vlands;

import de.slikey.effectlib.EffectManager;
import lombok.Getter;
import net.vlands.data.CooldownManager;
import net.vlands.data.manager.DataStorageManager;
import net.vlands.data.manager.SQLiteStorageManager;
import net.vlands.data.player.PlayerDataManager;
import net.vlands.effect.EffectsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class VLandsUtilities extends JavaPlugin {

    @Getter private PlayerDataManager playerDataManager;
    @Getter private DataStorageManager dataStorageManager;
    @Getter private CooldownManager cooldownManager;
    @Getter private EffectsManager effectsManager;
    @Getter private EffectManager complexEffectsManager;

    @Override
    public void onEnable() {
        setupManagers();
    }

    private void setupManagers() {
        dataStorageManager = new SQLiteStorageManager(new File(this.getDataFolder(), "database.db"));
        dataStorageManager.init();
        playerDataManager = new PlayerDataManager(this);
        Bukkit.getPluginManager().registerEvents(playerDataManager, this);
        cooldownManager = new CooldownManager(this);
        effectsManager = new EffectsManager(this);
        complexEffectsManager = new EffectManager(this);
    }
}
