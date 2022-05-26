package net.vlands.death.managers;

import net.milkbowl.vault.economy.Economy;
import net.vlands.VLandsUtilities;
import org.bukkit.entity.Player;

public class VaultManager {
    private final VLandsUtilities plugin;
    private final Economy economy;

    public VaultManager(VLandsUtilities plugin, Economy economy) {
        this.plugin = plugin;
        this.economy = economy;
    }

    public void add(Player player, Double amount) {
        economy.depositPlayer(player, amount);
    }

    public void remove(Player player, Double amount) {
        economy.withdrawPlayer(player, amount);
    }

    public void set(Player player, Double amount) {
        economy.withdrawPlayer(player, economy.getBalance(player) + amount);
    }

    public void reset(Player player) {
        economy.withdrawPlayer(player, economy.getBalance(player));
    }
}
