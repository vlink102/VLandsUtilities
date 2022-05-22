package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Treasure extends Effect {

    public Treasure(VLandsUtilities plugin) {
        super("treasure", "&6Treasure");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.DIAMOND, (byte)0), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.GOLD_INGOT, (byte)0), 0.5F, 0.5F, 0.5F, 0.05F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.IRON_INGOT, (byte)0), 0.5F, 0.5F, 0.5F, 0.01F, 20, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.EMERALD, (byte)0), 0.5F, 0.5F, 0.5F, 0.1F, 20, location, 20.0D);
    }
}
