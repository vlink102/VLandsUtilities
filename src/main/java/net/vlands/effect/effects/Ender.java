package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Ender extends Effect {

    public Ender(VLandsUtilities plugin) {
        super("ender", "&5Ender");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.PORTAL.display(0.1F, 0.1F, 0.1F, 0.1F, 12, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.ENDER_CHEST, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 6, location, 20.0D);
    }
}
