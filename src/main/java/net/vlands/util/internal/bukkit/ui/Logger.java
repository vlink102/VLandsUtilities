package net.vlands.util.internal.bukkit.ui;

import net.vlands.VLandsUtilities;
import net.vlands.util.internal.java.ClassEnumerator;
import net.vlands.util.internal.java.DynamicThreadWalker;
import org.bukkit.command.ConsoleCommandSender;

import javax.annotation.Nullable;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {

    private final VLandsUtilities plugin;

    public Logger(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        SEVERE
    }

    public enum StackLevel {
        PRINT,
        ERROR,
        SEVERE
    }

    public void log(LogLevel level, String message) {
        ConsoleCommandSender console = plugin.getConsoleCommandSender();
        switch (level) {
            case DEBUG -> {if (false /* TODO Configuration Debug Mode */) {console.sendMessage(ColorUtils.toColors(/* TODO Configuration Debug Prefix */ message));}}
            case INFO -> console.sendMessage(ColorUtils.toColors(/* TODO Configuration Info Prefix */ message));
            case WARN -> console.sendMessage(ColorUtils.toColors(/* TODO Configuration Warn Prefix */ message));
            case SEVERE -> console.sendMessage(ColorUtils.toColors(/* TODO Configuration Severe Prefix */ message));
        }
    }

    public void devLine(String message) {
        if (false /* TODO Configuration Dev Mode */) {
            ConsoleCommandSender console = plugin.getConsoleCommandSender();
            console.sendMessage(ColorUtils.toColors(/* TODO Configuration Dev Prefix */ message + "&7 (&dClass: &7" + ClassEnumerator.getCurrentClassName(getClass()) + ", &dLine: &7" + DynamicThreadWalker.getLineNumber() + "&7)"));
        }
    }

    public void intrinsic(String objName, Object o) {
        if (false /* TODO Configuration Intrinsic Mode */) {
            ConsoleCommandSender console = plugin.getConsoleCommandSender();
            console.sendMessage(ColorUtils.toColors(/* TODO Configuration Intrinsic Prefix */ "&8Value of " + objName + "&8 (" + o.getClass().getName() + "&8 ~ " + o.getClass().getSimpleName() + "&8): " + o));
        }
    }

    public void stackTrace(StackLevel level, Exception exception, @Nullable String message) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        switch (level) {
            case PRINT -> {
                log(LogLevel.INFO, (message == null) ? "&eAn unhandled exception occurred. This will not affect the server or plugin. (DO NOT REPORT THIS. IT IS NOT A BUG)" : message);
                exception.printStackTrace(pw);
            }
            case ERROR -> {
                log(LogLevel.SEVERE, (message == null) ? "&cAn unexpected error occurred. Please forward this to " + VLandsUtilities.AUTHORS + "&c (Author(s))" : message);
                exception.printStackTrace(pw);
                log(LogLevel.SEVERE, sw.toString());
            }
            case SEVERE -> {
                log(LogLevel.SEVERE, (message == null) ? "&4A fatal error occurred. Please report this to " + VLandsUtilities.AUTHORS + "&c (Author(s)). " + VLandsUtilities.PLUGIN + " has been disabled." : message);
                exception.printStackTrace(pw);
                log(LogLevel.SEVERE, sw.toString());
            }
        }
    }

}
