package net.vlands.effect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public abstract class Effect {

    private String name;
    private String fancyName;

    public abstract void playEffect(Player player, Player target);

}
