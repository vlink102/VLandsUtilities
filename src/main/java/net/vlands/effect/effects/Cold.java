package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Cold extends Effect {

    public Cold(VLandsUtilities plugin) {
        super("cold", "&9C&bo&9l&bd");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS, (byte)3), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS, (byte)11), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.ICE, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.CLOUD.display(0.0F, 0.0F, 0.0F, 0.1F, 30, location, 20.0D);
    }
}
