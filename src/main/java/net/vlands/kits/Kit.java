package net.vlands.kits;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class Kit {
    private final String commandIdentifier;
    private final String formattedName;
    private final ItemStack[] armorItems;
    private final List<ItemStack> inventoryItems;
    private final Double cost;

    /**
     * @param commandIdentifier The simple string that will be used in the kit command - /kit (commandIdentifier).
     * @param formattedName The color coded, formatted string that will be used to display the kit's name in messages and GUIs.
     * @param armorItems The 4 armor items - Helmet, Chestplate, Leggings, Boots.
     * @param inventoryItems The list of items to be added to the inventory.
     * @param cost The amount to withdraw from the player's balance on receiving the kit.
     */
    public Kit(String commandIdentifier, String formattedName, ItemStack[] armorItems, List<ItemStack> inventoryItems, Double cost) {
        this.commandIdentifier = commandIdentifier;
        this.formattedName = formattedName;
        this.armorItems = armorItems;
        this.inventoryItems = inventoryItems;
        this.cost = cost;
    }
}
