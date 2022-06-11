package net.vlands.util.internal;

import net.vlands.VLandsUtilities;
import net.vlands.util.ui.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {

    private static String prefix = "[VLands] ";
    private final VLandsUtilities plugin;

    public Logger(VLandsUtilities plugin) {
        this.plugin = plugin;
        Logger.prefix = "[" + plugin.getName() + "] ";
    }

    public void log(String message) {
        if (plugin == null) System.out.println(prefix + message);
        else Bukkit.getLogger().info(prefix + message);
    }

    public void info(String message) {
        if (plugin == null) System.out.println(prefix + message);
        else Bukkit.getLogger().info(prefix + message);
    }

    // Idk if this prints nicely
    public void coloredLog(String message) {
        ConsoleCommandSender console = plugin.getServer().getConsoleSender();
        console.sendMessage("[INFO] " + prefix + ColorUtils.toColors(message));
    }

    // Don't use too much
    public void coloredMessage(String message) {
        ConsoleCommandSender console = plugin.getServer().getConsoleSender();
        console.sendMessage(ColorUtils.toColors(message));
    }

    public void warn(String message) {
        if (plugin == null) System.out.println(prefix + message);
        else Bukkit.getLogger().warning(prefix + message);
    }

    public void severe(String message) {
        message = ColorUtils.removeColors(message); // Remove colors
        if (plugin == null) System.out.println(prefix + message);
        else Bukkit.getLogger().severe(prefix + message);
    }

    public void error(String message, Exception exception) {
        severe(message);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        severe(sw.toString());
    }

    public void stackTrace(String message, Exception exception) {
        warn(message);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
    }

    public void sendDebug(String message) {
        if (true /*config value is true*/) {
            if (plugin == null) System.out.println(prefix + message);
            else Bukkit.getLogger().info(prefix + "[DEBUG] " + message);
        }
    }

}
