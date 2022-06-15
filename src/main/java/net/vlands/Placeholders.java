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

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return null;
        }

        switch (params) {
            case SERVER + "version": return BukkitUtils.getServerVersion();
            case SERVER + "version_tab": return BukkitUtils.getVersion();
            case SERVER + "name": return BukkitUtils.getServerName();
            case SERVER + "mod_name": return BukkitUtils.getServerModName();
            case SERVER + "ip": return BukkitUtils.getIp();
            case SERVER + "motd": return BukkitUtils.getMotd();
            case SERVER + "port": return String.valueOf(BukkitUtils.getPort());
            case SERVER + "is_spigot": return String.valueOf(BukkitUtils.isSpigot());
            case SERVER + "bukkit_build": return String.valueOf(BukkitUtils.getBukkitBuild());
            case SERVER + "paper_build": return String.valueOf(BukkitUtils.getPaperSpigotBuild());
            case SERVER + "spigot_build": return String.valueOf(BukkitUtils.getSpigotBuild());
            case SERVER + "build": return String.valueOf(BukkitUtils.getBuild());
            case SERVER + "version_build": return String.valueOf(BukkitUtils.getVersionBuild());
            case SERVER + "max_players": return String.valueOf(BukkitUtils.getMaxPlayers());
            case SERVER + "op_count": return String.valueOf(BukkitUtils.getOPCount());
            case SERVER + "plugin_count": return String.valueOf(BukkitUtils.getPluginCount());
            case SERVER + "total_players_joined": return String.valueOf(BukkitUtils.getTotalPlayerCount());
            case SERVER + "has_whitelist": return String.valueOf(BukkitUtils.hasWhitelist());
            case SERVER + "has_uuid_support": return String.valueOf(BukkitUtils.hasUUIDSupport());
            case SERVER + "version_major": return String.valueOf(BukkitUtils.getVersionMajor());
            case SERVER + "version_minor": return String.valueOf(BukkitUtils.getVersionMinor());

            case SERVER + GAME + "online_players": return String.valueOf(BukkitUtils.getOnlinePlayers().size());
            case SERVER + GAME + "online_creative": return String.valueOf(BukkitUtils.getGameModePlayers(GameMode.CREATIVE).size());
            case SERVER + GAME + "online_survival": return String.valueOf(BukkitUtils.getGameModePlayers(GameMode.SURVIVAL).size());
            case SERVER + GAME + "online_adventure": return String.valueOf(BukkitUtils.getGameModePlayers(GameMode.ADVENTURE).size());
            case SERVER + GAME + "online_spectator": return String.valueOf(BukkitUtils.getGameModePlayers(GameMode.SPECTATOR).size());
            case SERVER + GAME + "online_vanished": return String.valueOf(BukkitUtils.getVanishedPlayers().size());
            case SERVER + GAME + "online_unvanished": return String.valueOf(BukkitUtils.getUnHiddenPlayers().size());
            case SERVER + GAME + "online_disguised": return String.valueOf(BukkitUtils.getDisguisedPlayers().size());
            case SERVER + GAME + "online_ops": return String.valueOf(BukkitUtils.getOnlineOperators().size());

            default:
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
        }

        return "null";
    }
}
