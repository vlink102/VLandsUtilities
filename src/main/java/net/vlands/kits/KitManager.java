package net.vlands.kits;

import net.vlands.VLandsUtilities;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KitManager {

    private VLandsUtilities plugin;
    private File kitFolder;
    private Map<String, Kit> kits = new HashMap<>();

    public KitManager(VLandsUtilities plugin) {
        this.plugin = plugin;
        this.kitFolder = new File(this.plugin.getDataFolder(), "kits");
        if (!this.kitFolder.isDirectory())
            this.kitFolder.mkdirs();

        if (this.kitFolder.listFiles() == null)
            return;

        for (File file : this.kitFolder.listFiles()) {
            if (!file.getName().endsWith(".yml"))
                continue; //not a kit file
            String name = file.getName().substring(0, file.getName().length() - 5);
            try {
                loadKit(file, name);
                this.plugin.getLogger().info("Loaded kit: " + name);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
                this.plugin.getLogger().warning("There was an error loading the kit named: " + name);
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
        Kit kit = new Kit(name, formattedName, new ItemStack[]{helmet, chestplate, pants, boots}, invContents, cost);

        this.kits.put(name, kit);
    }

    private void saveKit(Kit kit) throws IOException, InvalidConfigurationException {
        File file = new File(this.kitFolder, kit.getInternalName() + ".yml");
        if (!file.exists())
            file.createNewFile();

        FileConfiguration config = new YamlConfiguration();
        config.load(file);

        config.set("formatted-name", kit.getFormattedName());
        config.set("cost", kit.getCost());
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

    public Kit getKit(String internalName) {
        return kits.get(internalName);
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

}
