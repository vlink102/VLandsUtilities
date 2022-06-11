package net.vlands.util.ui;

import org.bukkit.entity.Player;

public class Unknown {
    /**
     * Send a message to an unknown receiver
     * @param message Message to send
     * @param sender Receiver
     */
    public static void toSender(String message, Object sender) {
        if (sender instanceof Player) {
            Game.toPlayer(message, (Player) sender); // Send to game
        } else {
            Console.message(message); // Send to console
        }
    }
}
