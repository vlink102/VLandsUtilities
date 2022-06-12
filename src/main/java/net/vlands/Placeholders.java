package net.vlands;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholders extends PlaceholderExpansion {

    private final VLandsUtilities plugin;

    public Placeholders(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "vlands";
    }

    @Override
    public @NotNull String getAuthor() {
        return "V_Link";
    }

    @Override
    public @NotNull String getVersion() {
        return VLandsUtilities.VERSION;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player != null && player.isOnline()) {
            return onPlaceholderRequest(player.getPlayer(), params);
        }
        return null;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return null;
        }
        // TODO
        if (params.equalsIgnoreCase("")) {
            return "blah";
        } else {
            return params;
        }
    }
}
