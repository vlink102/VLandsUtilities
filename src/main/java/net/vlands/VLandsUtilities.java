package net.vlands;

import com.earth2me.essentials.IEssentials;
import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import io.aquaticlabs.statssb.StatsSBAPI;
import lombok.Getter;
import me.activated.core.plugin.AquaCoreAPI;
import net.milkbowl.vault.economy.Economy;
import net.vlands.commands.KitCommands;
import net.vlands.data.CooldownManager;
import net.vlands.data.manager.DataStorageManager;
import net.vlands.data.manager.SQLiteStorageManager;
import net.vlands.data.player.PlayerDataManager;
import net.vlands.death.managers.SkillManager;
import net.vlands.death.managers.VaultManager;
import net.vlands.effect.EffectsManager;
import net.vlands.kits.KitManager;
import net.vlands.util.GenericUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import revxrsal.commands.bukkit.core.BukkitHandler;
import revxrsal.commands.command.ArgumentStack;
import revxrsal.commands.process.ValueResolver;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class VLandsUtilities extends JavaPlugin {


    public static final String VAULT = "Vault";
    public static final String STATSSB = "StatsSB";
    public static final String PLACEHOLDERAPI = "PlaceholderAPI";
    public static final String EFFECTLIB = "EffectLib";
    public static final String ESSENTIALSX = "EssentialsX";
    public static final String AQUACORE = "AquaCore";

    @Getter private PlayerDataManager playerDataManager;
    @Getter private DataStorageManager dataStorageManager;
    @Getter private CooldownManager cooldownManager;
    @Getter private EffectsManager effectsManager;
    @Getter private EffectManager complexEffectsManager;
    @Getter private KitManager kitManager;

    @Getter private SkillManager skillManager;

    @Getter private VaultManager vaultManager;
    @Getter private Economy economy;

    @Getter private HashMap<String, Object> apis;
    @Getter private HashMap<String, Boolean> pluginMap;

    @Getter private IEssentials essentials;

    @Override
    public void onEnable() {
        setupManagers();
        setupCommands();
    }

    private void setupManagers() {
        dataStorageManager = new SQLiteStorageManager(new File(this.getDataFolder(), "database.db"));
        dataStorageManager.init();

        playerDataManager = new PlayerDataManager(this);
        Bukkit.getPluginManager().registerEvents(playerDataManager, this);

        cooldownManager = new CooldownManager(this);
        kitManager = new KitManager(this);

        skillManager = new SkillManager(this);

        pluginMap.put(VAULT, hasPlugin(VAULT));
        pluginMap.put(STATSSB, hasPlugin(STATSSB));
        pluginMap.put(PLACEHOLDERAPI, hasPlugin(PLACEHOLDERAPI));
        pluginMap.put(EFFECTLIB, hasPlugin(EFFECTLIB));

        if (setupEconomy()) {
            vaultManager = new VaultManager(this, getEconomy());
            GenericUtils.log("Vault Dependency found, hooking onto Economy");
        } else {
            GenericUtils.sendWarning("No Vault Dependency found, Economy is disabled.");
        }
        if (hasPlugin("StatsSB")) {
            GenericUtils.log("StatsSB Dependency found, hooking onto Statistics + Combat Tags + Bow Accuracy");
            apis.put(STATSSB, StatsSBAPI.getAPI().getClass());
        } else {
            GenericUtils.sendWarning("No StatsSB Dependency found, Statistics + Combat Tags + Bow Accuracy are disabled.");
        }
        if (hasPlugin("PlaceholderAPI")) {
            GenericUtils.log("PlaceholderAPI Dependency found, hooking onto Placeholders");
        } else {
            GenericUtils.sendWarning("No PlaceholderAPI Dependency found, Placeholder support is disabled.");
        }
        if (hasPlugin("EffectLib")) {
            GenericUtils.log("EffectLib Dependency found, hooking onto Effects");
            effectsManager = new EffectsManager(this);
            complexEffectsManager = new EffectManager(this);
        } else {
            GenericUtils.sendWarning("No EffectLib Dependency found, KillEffects + Several custom items disabled.");
        }

        pluginMap.put(ESSENTIALSX, false);
        pluginMap.put(AQUACORE, false);

        if (hasPlugin("EssentialsX")) {
            if (hasPlugin("AquaCore")) {
                GenericUtils.log("AquaCore Dependency found, hooking onto API (Display name formatting).");
                GenericUtils.log("EssentialsX display name formatting disabled due to AquaCore override.");

                pluginMap.put(AQUACORE, true);
                pluginMap.put(ESSENTIALSX, false);

                apis.put(AQUACORE, AquaCoreAPI.INSTANCE);
            } else {
                GenericUtils.log("EssentialsX Dependency found, hooking onto API (Display name formatting).");
                essentials = (IEssentials) getServer().getPluginManager().getPlugin("EssentialsX");

                pluginMap.put(ESSENTIALSX, true);
            }
        } else {
            if (hasPlugin("AquaCore")) {
                GenericUtils.log("AquaCore Dependency found, hooking onto API (Display name formatting).");
                pluginMap.put(AQUACORE, true);
            }
        }
    }

    private boolean hasPlugin(String plugin) {
        return getServer().getPluginManager().getPlugin(plugin) != null;
    }

    private boolean setupEconomy() {
        if (!hasPlugin(VAULT)) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        economy = rsp.getProvider();
        return economy != null;
    }

    private void setupCommands() {
        CommandHandler commandHandler = new BukkitHandler(this)
                .registerValueResolver(ArgumentStack.class, ValueResolver.ValueResolverContext::arguments);
        commandHandler.registerDependency(VLandsUtilities.class, this);
        commandHandler.getAutoCompleter().registerSuggestion("players", (args, sender, command) -> {
            String last = args.get(args.size() - 1).toLowerCase(Locale.ROOT);
            return Bukkit.getOnlinePlayers().stream().map((Function<Player, String>) HumanEntity::getName).filter(s -> s.toLowerCase(Locale.ROOT).startsWith(last)).collect(Collectors.toList());
        });
        commandHandler.register(new KitCommands());

        ((BukkitCommandHandler) commandHandler).registerBrigadier();
    }
}
