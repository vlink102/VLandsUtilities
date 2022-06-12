package net.vlands.util.ui;

import com.earth2me.essentials.User;
import me.activated.core.api.player.PlayerData;
import me.activated.core.plugin.AquaCoreAPI;
import net.vlands.VLandsUtilities;
import org.bukkit.entity.Player;

public class DisplayNameManager {
    private final VLandsUtilities plugin;

    public DisplayNameManager(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public enum FakeLevel {
        DISGUISE, // [Prefix] [Tag] {Disguise Name}           [Suffix]      [Media] [XX] Not_V_Link [MrBeast]
        NICK,     // [Prefix] [Tag] {Nick Name}               [Suffix]      [Owner] [XX] Dream [Cool]
        FULL,     // [Prefix] [Tag] {Nick|Disguise|Real Name} [Suffix]      [Media] [XX] WowThisIsACoolName [MrBeast]
        REAL      // [Prefix] [Tag] {Real Name}               [Suffix]      [Owner] [XX] V_Link [Cool]
    }

    public enum FormatLevel {
        FULL,     // [Prefix] [Tag] {Nick|Disguise|Real Name} [Suffix]      [Owner] [XX] V_Link [Cool]
        RANK,     // [Prefix]       {Nick|Disguise|Real Name} [Suffix]      [Owner] V_Link
        NAME,     //                {Nick|Disguise|Real Name}               V_Link
        STRIPPED, //                {Nick|Disguise|Real Name}               V_Link (No color)
        VANISHED  //                {"???"}                                 ???
    }

    public String convert(Player player, FakeLevel fakeLevel, FormatLevel formatLevel) {
        String playerPrefix;
        String playerTag;
        String playerNameColor;
        String playerName;
        String playerSuffix;

        if (plugin.hasDependency(VLandsUtilities.AQUACORE)) {
            AquaCoreAPI aquaCore = plugin.getAquaCoreAPI();
            PlayerData playerData = aquaCore.getPlayerData(player.getUniqueId());

            if (playerData.isVanished()) {

            }
        }
        if (plugin.hasDependency(VLandsUtilities.ESSENTIALSX)) {
            User user = plugin.getEssentials().getUserMap().getUser(player.getUniqueId());

            if (user.isVanished()) {

            }
        }
    }
}
