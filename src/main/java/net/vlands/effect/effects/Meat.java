package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Meat extends Effect {

    public Meat(VLandsUtilities plugin) {
        super("meat", "&cMeat");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_BEEF, (byte)0), 0.5F, 0.5F, 0.5F, 0.1F, 6, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_CHICKEN, (byte)0), 0.5F, 0.5F, 0.5F, 0.05F, 6, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_RABBIT, (byte)0), 0.5F, 0.5F, 0.5F, 0.01F, 6, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_MUTTON, (byte)0), 0.5F, 0.5F, 0.5F, 0.1F, 6, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_FISH, (byte)1), 0.5F, 0.5F, 0.5F, 0.1F, 6, location, 20.0D);
    }
}
