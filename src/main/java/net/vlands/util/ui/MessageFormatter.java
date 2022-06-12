package net.vlands.util.ui;

import me.clip.placeholderapi.PlaceholderAPI;
import net.vlands.VLandsUtilities;
import org.bukkit.entity.Player;

public class MessageFormatter {
    private final VLandsUtilities plugin;

    public MessageFormatter(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public String convert(String message) {
        if (plugin.hasDependency(VLandsUtilities.PLACEHOLDERAPI)) {
            message = PlaceholderAPI.setPlaceholders(null, message);
        }
        return ColorUtils.toColors(message);
    }

    public String convertContextual(String message, Player player) {
        if (plugin.hasDependency(VLandsUtilities.PLACEHOLDERAPI)) {
            if (player != null) {
                message = PlaceholderAPI.setPlaceholders(player, message);
            }
        }
        return ColorUtils.toColors(message);
    }

    public String convertRelational(String message, Player player1, Player player2) {
        if (plugin.hasDependency(VLandsUtilities.PLACEHOLDERAPI)) {
            if ((player1 != null) && (player2 != null)) {
                message = PlaceholderAPI.setRelationalPlaceholders(player1, player2, message);
            }
        }
        return ColorUtils.toColors(message);
    }
}
