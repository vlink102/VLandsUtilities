package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.effect.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ValentinesDayEffect extends Effect {

    public ValentinesDayEffect() {
        super("valentinesday", "&dValentine's Day");
    }

    @Override
    public void playEffect(Player player, Player target) {
        ParticleEffect.HEART.display(0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS_PANE, (byte)14), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)14), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
    }
}
