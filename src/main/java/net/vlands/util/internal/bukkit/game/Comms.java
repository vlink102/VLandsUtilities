package net.vlands.util.internal.bukkit.game;

import net.vlands.VLandsUtilities;
import net.vlands.util.internal.bukkit.BukkitUtils;
import net.vlands.util.internal.bukkit.ui.ColorUtils;
import net.vlands.util.math.RandomGenerator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Objects;

public class Comms {

    private final VLandsUtilities plugin;

    public Comms(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    public static void sendMessage(String message, Player player) {
        player.sendMessage(ColorUtils.toColors(message));
    }

    public static void sendMessage(String message, Collection<? extends Player> players) {
        for (Player player : players) {
            player.sendMessage(ColorUtils.toColors(message));
        }
    }

    public static void sendGamemode(String message, GameMode gameMode) {
        for (Player player : BukkitUtils.getGamemodePlayers(gameMode)) {
            player.sendMessage(ColorUtils.toColors(message));
        }
    }

    public static void sendYLevel(String message, Double yLevel) {
        for (Player player : BukkitUtils.getOnlinePlayers()) {
            if (Objects.equals(player.getLocation().getY(), yLevel)) {
                player.sendMessage(ColorUtils.toColors(message));
            }
        }
    }

    public static void sendNearbyLocation(String message, Location location, Double radius) {
        for (Player player : BukkitUtils.getNearbyPlayers(location, radius)) {
            player.sendMessage(ColorUtils.toColors(message));
        }
    }

    public static void sendNearbyEntity(String message, Entity entity, Double radius) {
        for (Player player : BukkitUtils.getNearbyPlayers(entity, radius)) {
            player.sendMessage(ColorUtils.toColors(message));
        }
    }

    public static void sendPermission(String message, String permission) {
        for (Player player : BukkitUtils.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(ColorUtils.toColors(message));
            }
        }
    }

    public static void sendOps(String message) {
        for (Player player : BukkitUtils.getOperators()) {
            if (player.isOnline()) {
                player.sendMessage(ColorUtils.toColors(message));
            }
        }
    }

    public static void sendWorld(String message, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Player) {
                entity.sendMessage(message);
            }
        }
    }

    public static void sendDimension(String message, World world) {
        for (Player player : BukkitUtils.getOnlinePlayers()) {
            if (player.getWorld().equals(world) || player.getWorld().equals(Bukkit.getWorld(world.getName() + "_nether")) || player.getWorld().equals(Bukkit.getWorld(world.getName() + "_the_end"))) {
                player.sendMessage(ColorUtils.toColors(message));
            }
        }
    }

    public static void sendRandom(String message) {
        Player[] onlinePlayers = BukkitUtils.getOnlinePlayers().toArray(new Player[0]);
        Player player = onlinePlayers[RandomGenerator.random(onlinePlayers.length)];

        player.sendMessage(ColorUtils.toColors(message));
    }

    public static void sendRandom(String message, World world) {
        Player[] onlinePlayers = BukkitUtils.getWorldPlayers(world).toArray(new Player[0]);
        Player player = onlinePlayers[RandomGenerator.random(onlinePlayers.length)];

        player.sendMessage(ColorUtils.toColors(message));
    }
}
