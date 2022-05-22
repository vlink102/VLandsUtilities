package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Gold extends Effect {

    public Gold(VLandsUtilities plugin) {
        super("gold", "&6Gold");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.GOLD_INGOT, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.FIREWORKS_SPARK.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }
}
