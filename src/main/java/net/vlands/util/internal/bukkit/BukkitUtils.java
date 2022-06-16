package net.vlands.util.internal.bukkit;

import com.google.common.base.Charsets;
import net.vlands.VLandsUtilities;
import net.vlands.util.math.MathUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BukkitUtils {
    private static String version = "";
    private static boolean uuidSupport = true;
    private static boolean legacyOnlinePlayers = false;

    private static Method getOnlinePlayers = null;
    private static Class<?> bukkitClass = null;
    private static Class<?> minecraftServerClass;
    private static Method getServerMethod = null;
    private static Class<?> gameProfileClass = null;
    private static Constructor gameProfileClassConstructor = null;

    private static int versionMajor = -1;
    private static int versionMinor = -1;
    private static int versionBuild = 0;

    static {
        try {
            getVersion();
        }catch(Throwable ex){
            VLandsUtilities.getInstance().getLogger().severe("Something happened while trying to get the version of the server!");
            VLandsUtilities.getInstance().getLogger().severe("Please create a ticket with the following information:");
            VLandsUtilities.getInstance().getLogger().severe(Bukkit.getServer().getClass().getPackage().getName());
            ex.printStackTrace();
            throw ex;
        }
        try {
            bukkitClass = ReflectionUtil.getClass("Bukkit", "org.bukkit");
            getOnlinePlayers = ReflectionUtil.getMethod(bukkitClass, "getOnlinePlayers");
            minecraftServerClass = ReflectionUtil.getClass("MinecraftServer", ReflectionUtil.DynamicPackage.MINECRAFT_SERVER);
            getServerMethod = ReflectionUtil.getMethod(minecraftServerClass, "getServer");
            if (version.contains("v1_7")) {
                gameProfileClass = ReflectionUtil.getClass("GameProfile", "net.minecraft.util.com.mojang.authlib");
            } else if (version.contains("v1_8")) {
                gameProfileClass = ReflectionUtil.getClass("GameProfile", "com.mojang.authlib");
            }
            if (gameProfileClass != null) {
                gameProfileClassConstructor = ReflectionUtil.getConstructor(gameProfileClass, UUID.class, String.class);
            }
        } catch (Throwable ignored) {
        }
    }
    public static String getVersion() {
        if (Bukkit.getServer() == null) {
            return "";
        }

        if (version.equals("")) {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

            String[] data = BukkitUtils.getVersion().substring(1).split("_");
            if (MathUtils.isInteger(data[1]) && MathUtils.isInteger(data[0])) {
                versionMinor = Integer.parseInt(data[1]);
                versionMajor = Integer.parseInt(data[0]);
                if (data[2].startsWith("R")) {
                    versionBuild = Integer.parseInt(data[2].replace("R", "")) + 1;
                }
            }
            boolean latest = BukkitUtils.getVersionMajor() >= 1 && BukkitUtils.getVersionMinor() >= 8;

            uuidSupport = latest
                    || BukkitUtils.getVersion().contains("v1_7_R3") || BukkitUtils.getVersion().contains("v1_7_R4");
            if (BukkitUtils.getVersion().contains("v1_6") || BukkitUtils.getVersion().contains("v1_7")) {
                legacyOnlinePlayers = true;
            }
        }
        return version;
    }

    /**
     * Get server name
     *
     * @return Server Name
     */
    public static String getServerName() {
        return Bukkit.getServerName();
    }

    public static boolean disablePlugin(Plugin plugin) {
        Bukkit.getPluginManager().disablePlugin(plugin);
        return Bukkit.getPluginManager().isPluginEnabled(plugin);
    }

    /**
     * Get server motd
     *
     * @return MOTD
     */
    public static String getMotd() {
        return Bukkit.getMotd();
    }

    /**
     * Get max playeres
     *
     * @return Maximum players
     */
    public static int getMaxPlayers() {
        return Bukkit.getMaxPlayers();
    }

    /**
     * Get bukkit plugins
     *
     * @return Plugin Array
     */
    public static Plugin[] getPlugins() {
        return Bukkit.getPluginManager().getPlugins();
    }

    /**
     * Get server port
     *
     * @return Port
     */
    public static int getPort() {
        return Bukkit.getPort();
    }

    /**
     * Get bukkit plugin count
     *
     * @return Plugin count
     */
    public static int getPluginCount() {
        return Bukkit.getPluginManager().getPlugins().length;
    }

    /**
     * Get total player count
     *
     * @return Player Count
     */
    public static int getTotalPlayerCount() {
        return Bukkit.getOfflinePlayers().length;
    }

    /**
     * Get operator count
     *
     * @return Operator count
     */
    public static int getOPCount() {
        return Bukkit.getOperators().size();
    }

    /**
     * Get operators
     *
     * @return Operator array
     */
    public static Player[] getOperators() {
        return (Player[]) Bukkit.getOperators().toArray();
    }

    public static Collection<? extends Player> getOnlineOperators() {
        return getAllPlayersWhere(player -> player.isOnline() && player.isOp());
    }

    /**
     * Get ip
     *
     * @return IP address
     */
    public static String getIp() {
        String bukkitIP = Bukkit.getServer().getIp();
        return (bukkitIP == null || bukkitIP.equals("")) ? "127.0.0.1" : bukkitIP;
    }

    /**
     * Get players with a specific game mode
     *
     * @param mode Game Mode
     * @return Player Count
     */
    public static int getPlayerCount(GameMode mode) {
        int size = 0;
        for (Player p : getOnlinePlayers()) {
            if (p.getGameMode() == mode) {
                size++;
            }
        }
        return size;
    }

    public static Collection<? extends Player> getPlayersWithPermission(String permission) {
        return getAllPlayersWhere(player -> player.hasPermission(permission));
    }

    public static Collection<? extends Player> getOnlinePlayers() {
        if (legacyOnlinePlayers) {
            Player[] players = {};
            try {
                Object pObj = getOnlinePlayers.invoke(null);
                if (pObj instanceof Player[]) {
                    players = (Player[]) getOnlinePlayers.invoke(null);
                } else {
                    legacyOnlinePlayers = true;
                    return Bukkit.getOnlinePlayers();
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return Arrays.asList(players);
        } else {
            return Bukkit.getOnlinePlayers();
        }
    }

    public static Collection<? extends Player> getVanishedPlayers() {
        VLandsUtilities plugin = VLandsUtilities.getInstance();
        List<Player> playerList = new ArrayList<>();
        if (plugin.hasDependency(VLandsUtilities.PLUGIN_AQUACORE)) playerList.addAll(getAllPlayersWhere(player -> plugin.getAquaCoreAPI().getPlayerData(player.getUniqueId()).isVanished()));
        if (plugin.hasDependency(VLandsUtilities.PLUGIN_ESSENTIALSX)) playerList.addAll(getAllPlayersWhere(player -> plugin.getEssentials().getUserMap().getUser(player.getUniqueId()).isVanished()));
        return playerList;
    }

    public static Collection<? extends Player> getDisguisedPlayers() {
        VLandsUtilities plugin = VLandsUtilities.getInstance();
        List<Player> playerList = new ArrayList<>();
        if (plugin.hasDependency(VLandsUtilities.PLUGIN_AQUACORE)) playerList.addAll(getAllPlayersWhere(player -> plugin.getAquaCoreAPI().isDisguised(player)));
        return playerList;
    }

    public static Collection<? extends Player> getUnHiddenPlayers() {
        VLandsUtilities plugin = VLandsUtilities.getInstance();
        List<Player> playerList = new ArrayList<>();
        if (plugin.hasDependency(VLandsUtilities.PLUGIN_AQUACORE)) playerList.addAll(getAllPlayersWhere(player -> !plugin.getAquaCoreAPI().getPlayerData(player.getUniqueId()).isVanished()));
        if (plugin.hasDependency(VLandsUtilities.PLUGIN_ESSENTIALSX)) playerList.addAll(getAllPlayersWhere(player -> !plugin.getEssentials().getUserMap().getUser(player.getUniqueId()).isVanished()));
        return playerList;
    }

    private static Collection<? extends Player> getAllPlayersWhere(Predicate<Player> filter) {
        return getPlayersWhere(BukkitUtils.getOnlinePlayers(), filter);
    }

    private static Collection<? extends Player> getPlayersWhere(Collection<? extends Player> players, Predicate<Player> filter) {
        return players.stream().filter(filter).toList();
    }

    private static Collection<? extends Player> getPlayersFromEntities(Collection<? extends Entity> entities) {
        return entities.stream().filter(Player.class::isInstance).map(Player.class::cast).toList();
    }

    public static Collection<? extends Player> getGameModePlayers(GameMode gameMode) {
        return getAllPlayersWhere(player -> Objects.equals(player.getGameMode(), gameMode));
    }

    public static Collection<? extends Player> getWorldPlayers(World world) {
        return getAllPlayersWhere(player -> Objects.equals(player.getWorld(), world));
    }

    public static Collection<? extends Player> getNearbyPlayers(Entity entity, Double radius) {
        return getPlayersFromEntities(entity.getNearbyEntities(radius,radius,radius));
    }

    public static Collection<? extends Player> getNearbyPlayers(Location location, Double radius) {
        return getPlayersFromEntities(location.getWorld().getNearbyEntities(location, radius, radius, radius));
    }

    /**
     * Check if server has whitelist
     *
     * @return Whitelist
     */
    public static boolean hasWhitelist() {
        return Bukkit.hasWhitelist();
    }

    public static int getBuild() {
        if (isSpigot())
            return getSpigotBuild();
        else
            return getBukkitBuild();
    }

    public static void setVersion(String version) {
        BukkitUtils.version = version;
    }

    public static boolean hasUUIDSupport() {
        boolean latest = false;
        if (BukkitUtils.getVersionMajor() >= 1 && BukkitUtils.getVersionMinor() >= 8) {
            latest = true;
        }

        if (!version.equals(""))
            return uuidSupport;
        else if (latest || BukkitUtils.getVersion().contains("v1_7_R3")
                || BukkitUtils.getVersion().contains("v1_7_R4")) {
            uuidSupport = true;
            return true;
        }
        uuidSupport = false;
        return false;
    }

    /**
     * Get bukkit build
     *
     * @return Bukkit build
     */
    public static int getBukkitBuild() {
        String version = Bukkit.getVersion();
        Pattern pattern = Pattern.compile("(b)(\\d+)(jnks)");
        Matcher matcher = pattern.matcher(version);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(2));
        }

        return 0;
    }

    public static int getSpigotBuild() {
        String version = Bukkit.getVersion();
        Pattern pattern = Pattern.compile("(git-Spigot-)(\\d+)");
        Matcher matcher = pattern.matcher(version);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(2));
        }

        return 0;
    }

    public static int getPaperSpigotBuild() {
        String version = Bukkit.getVersion();
        Pattern pattern = Pattern.compile("(git-Paper-)(\\d+)");
        Matcher matcher = pattern.matcher(version);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(2));
        }

        return 0;
    }

    /**
     * Check if running spigot
     *
     * @return Spigot
     */
    public static boolean isSpigot() {
        return Bukkit.getVersion().toLowerCase().contains("spigot") || Bukkit.getVersion().toLowerCase().contains("paper");
    }

    /**
     * Get minecraft server version
     * <p>
     * This will get the minecraft server version as shown in the server list.
     *
     * @return Server version
     */
    public static String getServerVersion() {
        return version;
    }

    /**
     * Get server mod name
     *
     * @return Server mod name
     */
    public static String getServerModName() {
        String mod = "";
        try {
            Class<?> minecraftServerClass = ReflectionUtil.getClass("MinecraftServer", ReflectionUtil.DynamicPackage.MINECRAFT_SERVER);
            Object serverInstance = ReflectionUtil.getMethod(minecraftServerClass, "getServer")
                    .invoke(minecraftServerClass);

            mod = (String) ReflectionUtil.getMethod("getServerModName", minecraftServerClass).invoke(serverInstance);
        } catch (Exception ex) {
        }
        return mod;
    }

    /**
     * Change server version
     *
     * @param version Version
     */
    public static void changeVersion(String version) {
        try {
            VLandsUtilities.getInstance().getLogger().info("Preparing version change ...");
            Class<?> serverPing = ReflectionUtil.getClass("ServerPing", ReflectionUtil.DynamicPackage.MINECRAFT_SERVER);
            Class<?> serverPingData = ReflectionUtil.getClass("ServerPingServerData", ReflectionUtil.DynamicPackage.MINECRAFT_SERVER);
            Class<?> minecraftServerClass = ReflectionUtil.getClass("MinecraftServer", ReflectionUtil.DynamicPackage.MINECRAFT_SERVER);
            Object serverInstance = ReflectionUtil.getMethod(minecraftServerClass, "getServer")
                    .invoke(minecraftServerClass);
            Field[] fields = minecraftServerClass.getDeclaredFields();
            VLandsUtilities.getInstance().getLogger().info("Searching for ping packet ...");
            for (Field field : fields) {
                if (field.getType().equals(serverPing)) {
                    VLandsUtilities.getInstance().getLogger().info("Ping information found! Searching for current version ...");
                    Object value = ReflectionUtil.getValueFromClass(field.getName(), serverInstance,
                            minecraftServerClass);
                    Field[] fields_value = value.getClass().getDeclaredFields();
                    for (Field field_value : fields_value) {
                        if (field_value.getType().equals(serverPingData)) {
                            VLandsUtilities.getInstance().getLogger().info("Server version information found!");
                            Object serverData = ReflectionUtil.getValueFromClass(field_value.getName(), value,
                                    value.getClass());
                            String curVersion = (String) ReflectionUtil.getValue("a", serverData);
                            VLandsUtilities.getInstance().getLogger().info("Current server version: " + curVersion);
                            VLandsUtilities.getInstance().getLogger().info("Performing change ...");
                            ReflectionUtil.setValue("a", version, serverData);
                            ReflectionUtil.setValue("b", 4, serverData);
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getVersionMajor() {
        if (versionMajor == -1) {
            getVersion();
        }
        return versionMajor;
    }

    public static void setVersionMajor(int versionMajor) {
        BukkitUtils.versionMajor = versionMajor;
    }

    public static int getVersionMinor() {
        if (versionMinor == -1) {
            getVersion();
        }
        return versionMinor;
    }

    public static void setVersionMinor(int versionMinor) {
        BukkitUtils.versionMinor = versionMinor;
    }

    public static int getVersionBuild() {
        return versionBuild;
    }

    public static void setVersionBuild(int versionBuild) {
        BukkitUtils.versionBuild = versionBuild;
    }

    @SuppressWarnings("deprecation")
    public OfflinePlayer getOfflinePlayer(String name) {
        UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
        try {
            if (version.contains("v1_7")) {

            } else if (version.contains("v1_8")) {

            } else {
                return Bukkit.getOfflinePlayer(name);
            }

            Object gameProfile = gameProfileClassConstructor
                    .newInstance(uuid, name);
            Object serverInstance = getServerMethod
                    .invoke(minecraftServerClass);
            return (OfflinePlayer) ReflectionUtil.invokeMethod("getOfflinePlayer", minecraftServerClass,
                    serverInstance, gameProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Bukkit.getOfflinePlayer(uuid);
    }
}
