package net.vlands.util;

import org.bukkit.Bukkit;

public class GenericUtils {

    public static void sendWarning(String message) {
        Bukkit.getLogger().warning(message);

    }

    public static void sendError(String message) {
        Bukkit.getLogger().severe(message);
    }

    public static void log(String message) {
        Bukkit.getLogger().info(message);
    }

    public static void sendDebug(String message) {
        // Todo
    }

}
