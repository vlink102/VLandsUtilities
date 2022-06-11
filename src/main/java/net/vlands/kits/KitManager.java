package net.vlands.kits;

import net.vlands.VLandsUtilities;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class KitManager {

    private VLandsUtilities plugin;
    private File kitFolder;
    private Map<String, Kit> kits = new HashMap<>();

    public KitManager(VLandsUtilities plugin) {
        this.plugin = plugin;
        this.kitFolder = new File(this.plugin.getDataFolder(), "kits");
        if (!this.kitFolder.isDirectory()) this.kitFolder.mkdirs();
        if (this.kitFolder.listFiles() == null) return;

        for (File file : Objects.requireNonNull(this.kitFolder.listFiles())) {
            //why is this here?
            //YamlUtils.createConfig(plugin.getDataFolder() + ":kits", file.getName(), plugin.getResource("kit.yml"))

            if (!file.getName().endsWith(".yml")) continue;
            String name = file.getName().substring(0, file.getName().length() - 4).toLowerCase();
            try {
                loadKit(file, name);
                this.plugin.getLogger().info("Loaded kit: " + name);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
                this.plugin.getLogger().warn("There was an error loading the kit named: " + name);
            }
        }
    }

    public void reloadKits() {

    }

    private void loadKit(File file, String name) throws IOException, InvalidConfigurationException {
        FileConfiguration kitConfig = new YamlConfiguration();
        kitConfig.load(file);

        String formattedName = kitConfig.getString("formatted-name");
        ItemStack helmet = kitConfig.getItemStack("helmet");
        ItemStack chestplate = kitConfig.getItemStack("chestplate");
        ItemStack pants = kitConfig.getItemStack("pants");
        ItemStack boots = kitConfig.getItemStack("boots");
        ItemStack[] invContents = new ItemStack[36];
        for (int i = 0; i < 36; i++) {
            invContents[i] = kitConfig.getItemStack(Integer.toString(i));
        }
        double cost = kitConfig.getDouble("cost");
        long cooldown = kitConfig.getLong("cooldown");
        List<PotionEffect> potionEffects = (List<PotionEffect>) kitConfig.getList("potions");
        Kit kit = new Kit(name, formattedName, new ItemStack[]{helmet, chestplate, pants, boots}, invContents, potionEffects, cost, cooldown);

        this.kits.put(name, kit);
    }

    private String potionToString(PotionEffect effect) {
        return effect.getType().toString() + ":" + effect.getAmplifier() + ":" + effect.getDuration();
    }

    private void saveKit(Kit kit) throws IOException, InvalidConfigurationException {
        File file = new File(this.kitFolder, kit.getInternalName() + ".yml");
        if (!file.exists())
            file.createNewFile();

        FileConfiguration config = new YamlConfiguration();
        config.load(file);

        config.set("formatted-name", kit.getFormattedName());
        config.set("cost", kit.getCost());
        config.set("cooldown", kit.getCooldown());
        config.set("potions", kit.getPotionEffects());
        config.set("helmet", kit.getHelmet());
        config.set("chestplate", kit.getChestplate());
        config.set("pants", kit.getPants());
        config.set("boots", kit.getBoots());
        ItemStack[] items = kit.getInventoryItems();
        for (int i = 0; i < items.length; i++) {
            config.set(i + "", items[i]);
        }
        config.save(file);
    }

    public Set<Kit> getKits() {
        return new HashSet<>(this.kits.values());
    }

    public Kit getKitByName(String internalName) {
        return kits.get(internalName.toLowerCase());
    }

    public void addOrReplaceKit(Kit kit) {
        this.kits.put(kit.getInternalName(), kit);
        try {
            this.saveKit(kit);
        } catch (IOException | InvalidConfigurationException e) {
            this.plugin.getLogger().severe("Could not save the kit: " + kit.getInternalName());
            e.printStackTrace();
        }
    }

    public void deleteKit(Kit kit) {
        this.kits.remove(kit.getInternalName());
        File file = new File(this.kitFolder, kit.getInternalName());
        if (file.exists())
            file.delete();
    }

    public boolean isOnCooldown(Player player, Kit kit) {
        return this.plugin.getCooldownManager().isOnCooldown(player, kit.getInternalName(), kit.getCooldown());
    }

    public void applyKitWithCooldown(Player player, Kit kit) {
        this.plugin.getCooldownManager().setLastUseToNow(player, kit.getInternalName());
        this.applyKitWithoutCooldown(player, kit);
    }

    public void removeKit(Player player) {
        //TODO
    }

    public void applyKitWithoutCooldown(Player player, Kit kit) {
        this.setLastKit(kit, player);
        //TODO apply the kit
    }

    public Kit getLastKit(Player player) {
        return this.kits.get(this.plugin.getPlayerDataManager().getPlayerData(player).getKit());
    }

    private void setLastKit(Kit kit, Player player) {
        this.plugin.getPlayerDataManager().getPlayerData(player).setKit(kit.getInternalName());
    }

}
