package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ValentinesDayEffect extends Effect {

    public ValentinesDayEffect() {
        super("valentinesday", "&dValentine's Day");
    }

    @Override
    public void playEffect(Player player, Player target) {
        Location location = target.getLocation().subtract(0,1,0);
        ParticleEffect.HEART.display(0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS_PANE, (byte)14), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)14), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
    }
}
