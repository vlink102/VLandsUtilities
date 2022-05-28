package net.vlands.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.vlands.VLandsUtilities;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {
    private final VLandsUtilities plugin;

    public MessageManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public String convert(String message) {
        if (plugin.hasPlaceholderAPI) {
            message = PlaceholderAPI.setPlaceholders(null, message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String convertContextual(String message, Player player) {
        if (plugin.hasPlaceholderAPI) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String convertRelational(String message, Player player1, Player player2) {
        if (plugin.hasPlaceholderAPI) {
            message = PlaceholderAPI.setRelationalPlaceholders(player1, player2, message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
