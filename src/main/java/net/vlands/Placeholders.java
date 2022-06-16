package net.vlands;

import me.activated.core.api.ServerData;
import me.activated.core.plugin.AquaCoreAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.vlands.util.internal.bukkit.BukkitUtils;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Supplier;

public class Placeholders extends PlaceholderExpansion {

    private final VLandsUtilities plugin;

    public Placeholders(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "vlands";
    }

    @Override
    public @NotNull String getAuthor() {
        return "V_Link";
    }

    @Override
    public @NotNull String getVersion() {
        return VLandsUtilities.VERSION;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player != null && player.isOnline()) {
            return onPlaceholderRequest(player.getPlayer(), params);
        }
        return null;
    }

    private static final String SERVER = "server_";
    private static final String CORE = "core_";
    private static final String GAME = "game_";
    private static final String NETWORK = "network_";

    private final HashMap<String, Supplier<Object>> placeholderMap = new HashMap<>(){{
        this.put(SERVER + "version", BukkitUtils::getServerVersion);
        this.put(SERVER + "version_tab", BukkitUtils::getVersion);
        this.put(SERVER + "name", BukkitUtils::getServerName);
        this.put(SERVER + "mod_name", BukkitUtils::getServerModName);
        this.put(SERVER + "ip", BukkitUtils::getIp);
        this.put(SERVER + "motd", BukkitUtils::getMotd);
        this.put(SERVER + "port", BukkitUtils::getPort);
        this.put(SERVER + "is_spigot", BukkitUtils::isSpigot);
        this.put(SERVER + "bukkit_build", BukkitUtils::getBukkitBuild);
        this.put(SERVER + "paper_build", BukkitUtils::getPaperSpigotBuild);
        this.put(SERVER + "spigot_build", BukkitUtils::getSpigotBuild);
        this.put(SERVER + "build", BukkitUtils::getBuild);
        this.put(SERVER + "version_build", BukkitUtils::getVersionBuild);
        this.put(SERVER + "max_players", BukkitUtils::getMaxPlayers);
        this.put(SERVER + "op_count", BukkitUtils::getOPCount);
        this.put(SERVER + "plugin_count", BukkitUtils::getPluginCount);
        this.put(SERVER + "total_players_joined", BukkitUtils::getTotalPlayerCount);
        this.put(SERVER + "has_whitelist", BukkitUtils::hasWhitelist);
        this.put(SERVER + "has_uuid_support", BukkitUtils::hasUUIDSupport);
        this.put(SERVER + "version_major", BukkitUtils::getVersionMajor);
        this.put(SERVER + "version_minor", BukkitUtils::getVersionMinor);
        this.put(SERVER + GAME + "online_players", () -> BukkitUtils.getOnlinePlayers().size());
        this.put(SERVER + GAME + "online_creative", () -> BukkitUtils.getGameModePlayers(GameMode.CREATIVE).size());
        this.put(SERVER + GAME + "online_survival", () -> BukkitUtils.getGameModePlayers(GameMode.SURVIVAL).size());
        this.put(SERVER + GAME + "online_adventure", () -> BukkitUtils.getGameModePlayers(GameMode.ADVENTURE).size());
        this.put(SERVER + GAME + "online_spectator", () -> BukkitUtils.getGameModePlayers(GameMode.SPECTATOR).size());
        this.put(SERVER + GAME + "online_vanished", () -> BukkitUtils.getVanishedPlayers().size());
        this.put(SERVER + GAME + "online_unvanished", () -> BukkitUtils.getUnHiddenPlayers().size());
        this.put(SERVER + GAME + "online_disguised", () -> BukkitUtils.getDisguisedPlayers().size());
        this.put(SERVER + GAME + "online_ops", BukkitUtils::getOnlineOperators);
    }};

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return null;
        }
        Object value = placeholderMap.get(params);
        if (value != null) return value.toString();

        if (params.startsWith(NETWORK)) {
            if (plugin.hasDependency(VLandsUtilities.PLUGIN_AQUACORE)) {
                AquaCoreAPI aquaCoreAPI = plugin.getAquaCoreAPI();
                String server = params.split("_")[1];
                if (VLandsUtilities.isValidServer(server)) {
                    ServerData serverData = aquaCoreAPI.getServerData(server);
                    String newParams = params.substring(8 /* NETWORK.length() */ + server.length());
                    switch (newParams) {
                        case SERVER + GAME + "online_players": return String.valueOf(serverData.getOnlinePlayers().size());
                        case SERVER + "max_players": return String.valueOf(serverData.getMaxPlayers());
                        case SERVER + "name": return serverData.getServerName();
                        case SERVER + "raw_name": return server;
                        case SERVER + "maintenance": return String.valueOf(serverData.isMaintenance());
                        case SERVER + "whitelisted": return String.valueOf(serverData.isWhitelisted());
                    }
                }
            }
        }

        return "null";
    }
}
