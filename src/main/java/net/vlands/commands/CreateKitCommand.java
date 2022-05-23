package net.vlands.commands;

import lombok.AllArgsConstructor;
import net.vlands.VLandsUtilities;
import net.vlands.kits.Kit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.bukkit.annotation.CommandPermission;
import revxrsal.commands.command.ArgumentStack;

@AllArgsConstructor
public class CreateKitCommand {

    private VLandsUtilities plugin;

    @Command("createkit")
    @CommandPermission("vlands.commands.createkit")
    public void createKit(Player player,@Named("internal name") String internalName, @Named("cost") Double cost, @Named("formatted name") ArgumentStack stack) {
        String formattedName = ChatColor.translateAlternateColorCodes('&', stack.join(" "));
        if (formattedName.equals("") || internalName.equals(""))
            //cant creat ekit
            return;

        Kit kit = new Kit(internalName, formattedName, player.getEquipment().getArmorContents(), player.getInventory().getContents(), cost);
        this.plugin.getKitManager().addOrReplaceKit(kit);
    }

}
