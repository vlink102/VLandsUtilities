package net.vlands.effect;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Getter
public abstract class Effect {

    private final String name;
    private final String fancyName;
    private final String rawName;

    public Effect(String name, String fancyName){
        this.name = name;
        this.fancyName = ChatColor.translateAlternateColorCodes('&',fancyName);
        this.rawName = ChatColor.stripColor(fancyName);
    }

    public abstract void playEffect(Player player, Player target);

}
