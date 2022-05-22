package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Warm extends Effect {

    public Warm(VLandsUtilities plugin) {
        super("warm", "&cWarm");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)11), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.STAINED_GLASS_PANE, (byte)14), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)4), 0.3F, 0.3F, 0.3F, 0.1F, 5, location, 20.0D);
        ParticleEffect.FLAME.display(0.3F, 0.3F, 0.3F, 0.1F, 3, location, 20.0D);
    }
}
