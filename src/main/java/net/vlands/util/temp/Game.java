package net.vlands.util.temp;

import net.vlands.VLandsUtilities;
import net.vlands.util.internal.bukkit.BukkitUtils;
import net.vlands.util.internal.bukkit.ui.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Set;

public class Game {

    private final VLandsUtilities plugin;

    public Game(VLandsUtilities plugin) {
        this.plugin = plugin;
    }

    /**
     * Send a private message
     * @param message Message to send
     */
    public static void toPlayer(String message, Player player) {
        player.sendMessage();
    }


    /**
     * Send a private message
     * @param message Message to send
     */
    public static void toPlayers(String message, Set<Player> players) {
        for (Player player : players) {
            toPlayer(message, player);
        }
    }


    /**
     * Send a private message
     * @param message    Message to send
     * @param permission Permission
     */
    public static void toPermission(String message, String permission) {
        message = ColorUtils.toColors(message); // ColorUtils the message
        Bukkit.broadcast(message, permission);
    }

    /**
     * Send a broadcast to the server
     * @param message Message to broadcast
     */
    public static void toServer(String message) {
        message = ColorUtils.toColors(message); // ColorUtils the message
        Bukkit.broadcastMessage(message);
    }

    /**
     * Send a message to a specific world
     * @param message Message
     * @param world   World
     */
    public static void toWorld(String message, World world) {
        message = ColorUtils.toColors(message); // ColorUtils the message
        for (Player player : BukkitUtils.getOnlinePlayers()) {
            if (player.getWorld().equals(world)) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * Send a message to a specific world
     * @param message Message
     * @param world   World
     */
    public static void toWorldGroup(String message, World world) {
        message = ColorUtils.toColors(message); // ColorUtils the message
        for (Player player : BukkitUtils.getOnlinePlayers()) {
            if (player.getWorld().equals(world)
                    || player.getWorld().equals(
                    Bukkit.getWorld(world.getName() + "_nether"))
                    || player.getWorld().equals(
                    Bukkit.getWorld(world.getName() + "_the_end"))) {
                player.sendMessage(message);
            }
        }
    }
}
