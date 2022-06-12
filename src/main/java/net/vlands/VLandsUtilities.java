package net.vlands;

import com.earth2me.essentials.IEssentials;
import de.slikey.effectlib.EffectManager;
import io.aquaticlabs.statssb.StatsSBAPI;
import lombok.Getter;
import me.activated.core.plugin.AquaCoreAPI;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.vlands.commands.KitCommands;
import net.vlands.data.CooldownManager;
import net.vlands.data.manager.DataStorageManager;
import net.vlands.data.manager.SQLiteStorageManager;
import net.vlands.data.player.PlayerDataManager;
import net.vlands.death.managers.SkillManager;
import net.vlands.death.managers.VaultManager;
import net.vlands.effect.EffectsManager;
import net.vlands.kits.KitManager;
import net.vlands.util.internal.bukkit.BuildStability;
import net.vlands.util.internal.bukkit.ui.ColouredConsoleSender;
import net.vlands.util.internal.bukkit.ui.Logger;
import net.vlands.util.internal.java.ClassEnumerator;
import net.vlands.util.internal.java.DynamicThreadWalker;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import revxrsal.commands.bukkit.core.BukkitHandler;
import revxrsal.commands.command.ArgumentStack;
import revxrsal.commands.process.ValueResolver;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class VLandsUtilities extends JavaPlugin {

    public static final String VERSION = "1.0.0";
    public static final String AUTHOR = "V_Link";
    public static final BuildStability STABILITY = BuildStability.UNFINISHED; // TODO
    public static final String DISCORD = "https://discord.gg/NJ3ZWYE4sj";

    public static final String VAULT = "Vault";
    public static final String STATSSB = "StatsSB";
    public static final String PLACEHOLDERAPI = "PlaceholderAPI";
    public static final String EFFECTLIB = "EffectLib";
    public static final String ESSENTIALSX = "EssentialsX";
    public static final String AQUACORE = "AquaCore";

    @Getter private static VLandsUtilities instance;

    @Getter private PlayerDataManager playerDataManager;
    @Getter private DataStorageManager dataStorageManager;
    @Getter private CooldownManager cooldownManager;
    @Getter private EffectsManager effectsManager;
    @Getter private EffectManager complexEffectsManager;
    @Getter private KitManager kitManager;

    @Getter private SkillManager skillManager;

    @Getter private VaultManager vaultManager;
    @Getter private Economy economy;
    @Getter private Permission permissions;
    @Getter private Chat chat;

    @Getter private Logger customLogger;

    @Getter private AquaCoreAPI aquaCoreAPI;
    @Getter private IEssentials essentials;
    @Getter private StatsSBAPI statsSBAPI;

    @Getter private ConsoleCommandSender consoleSender;

    private HashMap<String, Boolean> pluginMap;

    @Override
    public void onEnable() {
        instance = this;
        consoleSender = ColouredConsoleSender.getInstance();

        logger.coloredLog("&9&m─────── &r&6VLands Utilities &9&m──────");
        setupManagers();
        logger.log(" > Passed manager setup stage");
        setupCommands();
        logger.log(" > Passed command setup stage");
        logger.log("Plugin successfully initialized!");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void setupManagers() {

        dataStorageManager = new SQLiteStorageManager(new File(this.getDataFolder(), "database.db"));
        dataStorageManager.init();
        logger.coloredLog("&d ~ &eData storage manager initialized &7(DEV: &aClass: &7" + ClassEnumerator.getCurrentClassName(getClass()) + ", &aLine: &7" + DynamicThreadWalker.getLineNumber() + "&7)");

        playerDataManager = new PlayerDataManager(this);
        Bukkit.getPluginManager().registerEvents(playerDataManager, this);
        logger.coloredLog("&ePlayer data manager initialized &7(DEV~: &aClass: &7" + ClassEnumerator.getCurrentClassName(getClass()) + ", &aLine: &7" + DynamicThreadWalker.getLineNumber() + "&7)");

        cooldownManager = new CooldownManager(this);
        logger.coloredLog("&eCooldown manager initialized &7(DEV~: &aClass: &7" + ClassEnumerator.getCurrentClassName(getClass()) + ", &aLine: &7" + DynamicThreadWalker.getLineNumber() + "&7)");

        kitManager = new KitManager(this);
        logger.coloredLog("&eKit manager initialized &7(DEV~: &aClass: &7" + ClassEnumerator.getCurrentClassName(getClass()) + ", &aLine: &7" + DynamicThreadWalker.getLineNumber() + "&7)");

        skillManager = new SkillManager(this);
        logger.coloredLog("&eSkill manager initialized &7(DEV~: &aClass: &7" + ClassEnumerator.getCurrentClassName(getClass()) + ", &aLine: &7" + DynamicThreadWalker.getLineNumber() + "&7)");

        pluginMap.put(VAULT, hasPlugin(VAULT));
        pluginMap.put(STATSSB, hasPlugin(STATSSB));
        pluginMap.put(PLACEHOLDERAPI, hasPlugin(PLACEHOLDERAPI));
        pluginMap.put(EFFECTLIB, hasPlugin(EFFECTLIB));

        if (setupEconomy()) {
            vaultManager = new VaultManager(this, getEconomy());
            setupChat();
            setupPermissions();
            logger.log("Vault Dependency found, hooking onto Economy, Permissions and Chat");
        } else {
            logger.warn("No Vault Dependency found, Economy, Permissions and Chat are disabled.");
        }
        if (hasPlugin("StatsSB")) {
            logger.log("StatsSB Dependency found, hooking onto Statistics + Combat Tags + Bow Accuracy");
            statsSBAPI = StatsSBAPI.getAPI();
        } else {
            logger.warn("No StatsSB Dependency found, Statistics + Combat Tags + Bow Accuracy are disabled.");
        }
        if (hasPlugin("PlaceholderAPI")) {
            logger.log("PlaceholderAPI Dependency found, hooking onto Placeholders");
        } else {
            logger.warn("No PlaceholderAPI Dependency found, Placeholder support is disabled.");
        }
        if (hasPlugin("EffectLib")) {
            logger.log("EffectLib Dependency found, hooking onto Effects");
            effectsManager = new EffectsManager(this);
            complexEffectsManager = new EffectManager(this);
            effectsManager.registerAllEffects();
            logger.log("All effects Loaded Successfully!");
        } else {
            logger.warn("No EffectLib Dependency found, KillEffects + Several custom items disabled.");
        }

        pluginMap.put(ESSENTIALSX, false);
        pluginMap.put(AQUACORE, false);

        if (hasPlugin("EssentialsX")) {
            if (hasPlugin("AquaCore")) {
                logger.log("AquaCore Dependency found, hooking onto API (Display name formatting).");
                logger.log("EssentialsX display name formatting disabled due to AquaCore override.");

                pluginMap.put(AQUACORE, true);
                pluginMap.put(ESSENTIALSX, false);

                aquaCoreAPI = AquaCoreAPI.INSTANCE;
            } else {
                logger.log("EssentialsX Dependency found, hooking onto API (Display name formatting).");
                essentials = (IEssentials) getServer().getPluginManager().getPlugin(ESSENTIALSX);

                pluginMap.put(ESSENTIALSX, true);
            }
        } else {
            if (hasPlugin("AquaCore")) {
                logger.log("AquaCore Dependency found, hooking onto API (Display name formatting).");
                pluginMap.put(AQUACORE, true);

                aquaCoreAPI = AquaCoreAPI.INSTANCE;
            }
        }
    }

    private boolean hasPlugin(String plugin) {
        return getServer().getPluginManager().getPlugin(plugin) != null;
    }

    public boolean hasDependency(String plugin) {
        return pluginMap.getOrDefault(plugin, false);
    }

    private boolean setupEconomy() {
        if (!hasPlugin(VAULT)) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        economy = rsp.getProvider();
        return economy != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        permissions = rsp.getProvider();
        return permissions != null;
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
