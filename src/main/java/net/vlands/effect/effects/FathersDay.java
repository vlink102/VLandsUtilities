package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class FathersDay extends Effect {

    public FathersDay(VLandsUtilities plugin) {
        super("fathersday", "&bFather's Day");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.WOOL, (byte)3), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.HEART.display(0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
    }
}
