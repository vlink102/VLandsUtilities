package net.vlands.util.internal.bukkit.ui;

import net.vlands.VLandsUtilities;
import org.bukkit.command.ConsoleCommandSender;

public class Logger {

    private final VLandsUtilities plugin;

    public Logger(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public enum LogLevel {
        DEV,
        DEBUG,
        INFO,
        WARN,
        SEVERE
    }

    public void log(LogLevel level, String message) {
        ConsoleCommandSender console = plugin.getConsoleCommandSender();
        switch (level) {
            case DEV -> {if (true /* TODO Configuration Developer Mode */) {console.sendMessage(ColorUtils.toColors(/* TODO Configuration Developer Prefix */ message));}}
            case DEBUG -> {if (false /* TODO Configuration Debug Mode */) {console.sendMessage(ColorUtils.toColors(/* TODO Configuration Debug Prefix */ message));}}
            case INFO -> console.sendMessage(ColorUtils.toColors(/* TODO Configuration Info Prefix */ message));
            case WARN -> console.sendMessage(ColorUtils.toColors(/* TODO Configuration Warn Prefix */ message));
            case SEVERE -> console.sendMessage(ColorUtils.toColors(/* TODO Configuration Severe Prefix */ message));
        }
    }
}
