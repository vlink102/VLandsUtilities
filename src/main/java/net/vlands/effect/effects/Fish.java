package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Fish extends Effect {

    public Fish(VLandsUtilities plugin) {
        super("fish", "&bFish");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_FISH, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_FISH, (byte)1), 0.3F, 0.3F, 0.3F, 0.1F, 2, location, 20.0D);
        ParticleEffect.WATER_WAKE.display(0.3F, 0.3F, 0.3F, 0.05F, 10, location, 20.0D);
    }
}
