package net.vlands.effect;

import lombok.Getter;
import net.vlands.util.ui.ColorUtils;
import org.bukkit.entity.Player;

@Getter
public abstract class Effect {

    private final String name;
    private final String fancyName;
    private final String rawName;

    public Effect(String name, String fancyName){
        this.name = name;
        this.fancyName = ColorUtils.toColors(fancyName);
        this.rawName = ColorUtils.removeColors(fancyName);
    }

    public abstract void playEffect(Player killer, Player victim);

}
