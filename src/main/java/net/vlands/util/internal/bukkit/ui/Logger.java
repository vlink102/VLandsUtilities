package net.vlands.util.internal.bukkit.ui;

public class Logger {
    public enum LogLevel {
        DEV,
        DEBUG,
        INFO,
        WARN,
        SEVERE
    }

    public void log(LogLevel level, String message) {
        switch (level) {
            case DEV -> {
                if (true /* TODO Configuration Developer Mode */) {
// TODO
                }
            }
            case DEBUG -> {

            }
            case INFO -> {

            }
            case WARN -> {

            }
            case SEVERE -> {

            }
        }
    }
}
