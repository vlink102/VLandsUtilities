package net.vlands.util.internal.bukkit.ui;

import com.earth2me.essentials.User;
import me.activated.core.api.player.PlayerData;
import me.activated.core.plugin.AquaCoreAPI;
import net.milkbowl.vault.chat.Chat;
import net.vlands.VLandsUtilities;
import org.bukkit.entity.Player;

@SuppressWarnings("DuplicatedCode")
public class DisplayNameManager {
    private final VLandsUtilities plugin;

    public DisplayNameManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public String convertFull(Player player, Boolean prefix, Boolean tag, Boolean nameColor, Boolean name, Boolean suffix) {
        String playerPrefix = "";
        String playerTag = "";
        String playerNameColor = "";
        String playerName = "";
        String playerSuffix = "";

        if (plugin.hasDependency(VLandsUtilities.PLUGIN_AQUACORE)) {
            AquaCoreAPI aquaCore = plugin.getAquaCoreAPI();
            PlayerData playerData = aquaCore.getPlayerData(player.getUniqueId());

            playerPrefix = prefix ? playerData.getHighestRank().getPrefix() : "";
            playerTag = tag ? playerData.getTagColor() + playerData.getTag() : "";
            playerNameColor = nameColor ? playerData.getNameColor() : "";
            if (playerData.isVanished()) {
                playerName = name ? "???&r" : "";
            } else {
                playerName = name ? aquaCore.getGlobalPlayer(player.getUniqueId()).getName() : ""; // FIXME AquaCore playerName
            }
            playerSuffix = suffix ? playerData.getHighestRank().getSuffix() : "";
        } else if (plugin.hasDependency(VLandsUtilities.PLUGIN_ESSENTIALSX) && plugin.hasDependency(VLandsUtilities.PLUGIN_VAULT)) {
            User user = plugin.getEssentials().getUserMap().getUser(player.getUniqueId());
            Chat chat = plugin.getChat();

            playerPrefix = prefix ? chat.getPlayerPrefix(player) : "";
            if (user.isVanished()) {
                playerName = name ? "???&r" : "";
            } else {
                playerName = name ? user.getDisplayName() : "";
            }
            playerSuffix = suffix ? chat.getPlayerSuffix(player) : "";
        } else if (plugin.hasDependency(VLandsUtilities.PLUGIN_ESSENTIALSX)) {
            User user = plugin.getEssentials().getUserMap().getUser(player.getUniqueId());
            if (user.isVanished()) {
                playerName = name ? "???&r" : "";
            } else {
                playerName = name ? user.getDisplayName() : "";
            }
        } else if (plugin.hasDependency(VLandsUtilities.PLUGIN_VAULT)) {
            Chat chat = plugin.getChat();
            playerPrefix = prefix ? chat.getPlayerPrefix(player) : "";
            playerName = name ? player.getName() : "";
            playerSuffix = suffix ? chat.getPlayerSuffix(player) : "";
        }

        return ColorUtils.toColors(playerPrefix + playerTag + playerNameColor + playerName + playerSuffix);
    }

    public String convertReal(Player player, Boolean prefix, Boolean tag, Boolean nameColor, Boolean name, Boolean suffix) {
        String playerPrefix = "";
        String playerTag = "";
        String playerNameColor = "";
        String playerName = "";
        String playerSuffix = "";

        if (plugin.hasDependency(VLandsUtilities.PLUGIN_AQUACORE)) {
            AquaCoreAPI aquaCore = plugin.getAquaCoreAPI();
            PlayerData playerData = aquaCore.getPlayerData(player.getUniqueId());

            playerPrefix = prefix ? playerData.getHighestRank().getPrefix() : ""; // FIXME Might not be real rank
            playerTag = tag ? playerData.getTagColor() + playerData.getTag() : "";
            playerNameColor = nameColor ? playerData.getNameColor() : ""; // FIXME Might not be real name color
            playerName = name ? aquaCore.getRealName(player) : "";
            playerSuffix = suffix ? playerData.getHighestRank().getSuffix() : ""; // FIXME Might not be real suffix
        } else if (plugin.hasDependency(VLandsUtilities.PLUGIN_ESSENTIALSX) && plugin.hasDependency(VLandsUtilities.PLUGIN_VAULT)) {
            User user = plugin.getEssentials().getUserMap().getUser(player.getUniqueId());
            Chat chat = plugin.getChat();

            playerPrefix = prefix ? chat.getPlayerPrefix(player) : "";
            playerName = name ? user.getName() : "";
            playerSuffix = suffix ? chat.getPlayerSuffix(player) : "";
        } else if (plugin.hasDependency(VLandsUtilities.PLUGIN_ESSENTIALSX)) {
            User user = plugin.getEssentials().getUserMap().getUser(player.getUniqueId());
            playerName = name ? user.getName() : "";
        } else if (plugin.hasDependency(VLandsUtilities.PLUGIN_VAULT)) {
            Chat chat = plugin.getChat();
            playerPrefix = prefix ? chat.getPlayerPrefix(player) : "";
            playerName = name ? player.getName() : "";
            playerSuffix = suffix ? chat.getPlayerSuffix(player) : "";
        }

        return ColorUtils.toColors(playerPrefix + playerTag + playerNameColor + playerName + playerSuffix);
    }
}
