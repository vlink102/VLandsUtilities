package net.vlands.kits;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Kit {

    @Getter private final String internalName;
    @Getter private final String formattedName;
    private final ItemStack[] armorItems;
    private final ItemStack[] inventoryItems;
    @Getter private final double cost;
    @Getter private long cooldown;

    /**
     * @param internalName The simple string that will be used in the kit command - /kit (commandIdentifier).
     * @param formattedName The color coded, formatted string that will be used to display the kit's name in messages and GUIs.
     * @param armorItems The 4 armor items - Helmet, Chestplate, Leggings, Boots.
     * @param inventoryItems The list of items to be added to the inventory.
     * @param cost The amount to withdraw from the player's balance on receiving the kit.
     */
    public Kit(String internalName, String formattedName, ItemStack[] armorItems, ItemStack[] inventoryItems, double cost, long cooldown) {
        this.internalName = internalName.toLowerCase();
        this.formattedName = formattedName;

        for (int i = 0; i < armorItems.length; i++) {
            if (armorItems[i] != null && armorItems[i].getType() == Material.AIR)
                armorItems[i] = null;
        }
        this.armorItems = Arrays.copyOf(armorItems, 4);

        for (int i = 0; i < inventoryItems.length; i++) {
            if (inventoryItems[i] != null && inventoryItems[i].getType() == Material.AIR)
                inventoryItems[i] = null;
        }
        this.inventoryItems = Arrays.copyOf(inventoryItems, 36);
        this.cost = cost;
        this.cooldown = cooldown;
    }

    public ItemStack[] getInventoryItems() {
        return Arrays.copyOf(inventoryItems, 36);
    }

    public ItemStack getHelmet() {
        return armorItems[0];
    }

    public ItemStack getChestplate() {
        return armorItems[1];
    }

    public ItemStack getPants() {
        return armorItems[2];
    }

    public ItemStack getBoots() {
        return armorItems[3];
    }
}
