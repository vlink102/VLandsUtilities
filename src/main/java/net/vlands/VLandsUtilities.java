package net.vlands;

import de.slikey.effectlib.EffectManager;
import lombok.Getter;
import net.vlands.commands.CreateKitCommand;
import net.vlands.data.CooldownManager;
import net.vlands.data.manager.DataStorageManager;
import net.vlands.data.manager.SQLiteStorageManager;
import net.vlands.data.player.PlayerDataManager;
import net.vlands.effect.EffectsManager;
import net.vlands.kits.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bukkit.BukkitCommandHandler;
import revxrsal.commands.bukkit.core.BukkitHandler;
import revxrsal.commands.command.ArgumentStack;
import revxrsal.commands.process.ValueResolver;

import java.io.File;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class VLandsUtilities extends JavaPlugin {

    @Getter private PlayerDataManager playerDataManager;
    @Getter private DataStorageManager dataStorageManager;
    @Getter private CooldownManager cooldownManager;
    @Getter private EffectsManager effectsManager;
    @Getter private EffectManager complexEffectsManager;
    @Getter private KitManager kitManager;

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
        effectsManager = new EffectsManager(this);
        complexEffectsManager = new EffectManager(this);
        this.kitManager = new KitManager(this);
    }

    private void setupCommands() {
        CommandHandler commandHandler = new BukkitHandler(this)
                .registerValueResolver(ArgumentStack.class, ValueResolver.ValueResolverContext::arguments);
        commandHandler.getAutoCompleter().registerSuggestion("players", (args, sender, command) -> {
            String last = args.get(args.size() - 1).toLowerCase(Locale.ROOT);
            return Bukkit.getOnlinePlayers().stream().map((Function<Player, String>) HumanEntity::getName).filter(s -> s.toLowerCase(Locale.ROOT).startsWith(last)).collect(Collectors.toList());
        });
        commandHandler.register(new CreateKitCommand(this));


        //last thing to do
        ((BukkitCommandHandler) commandHandler).registerBrigadier();
    }
}
