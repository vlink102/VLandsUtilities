package net.vlands.commands;

import net.vlands.VLandsUtilities;
import net.vlands.kits.Kit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Dependency;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;
import revxrsal.commands.command.ArgumentStack;

import java.util.ArrayList;
import java.util.List;

public class KitCommands {

    @Dependency
    private VLandsUtilities plugin;

    @Command("kit")
    @Subcommand("create") // TODO idk how to use subcommands in lamp there isnt many examples.
    @CommandPermission("vlands.commands.createkit")
    public void createKit(Player player,@Named("internal name") String internalName, @Named("cost") Double cost, @Named("formatted name") ArgumentStack stack) {
        String formattedName = ChatColor.translateAlternateColorCodes('&', stack.join(" "));
        if (formattedName.equals("") || internalName.equals("")) return;

        List<PotionEffect> effectList = new ArrayList<>(player.getActivePotionEffects());
        effectList.forEach(potionEffect -> potionEffect = new PotionEffect(potionEffect.getType(), 1200 /* 1 minute */, potionEffect.getAmplifier()));

        Kit kit = new Kit(internalName, formattedName, player.getEquipment().getArmorContents(), player.getInventory().getContents(), effectList, cost, 60000); // TODO idk how potioneffects work here, maybe only alllow from yaml.
        this.plugin.getKitManager().addOrReplaceKit(kit);
    }

}
