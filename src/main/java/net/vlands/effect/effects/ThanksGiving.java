package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class ThanksGiving extends Effect {

    public ThanksGiving(VLandsUtilities plugin) {
        super("thanksgiving", "&2Thanksgiving");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.COOKED_CHICKEN, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(Material.PUMPKIN_PIE, (byte)0), 0.3F, 0.3F, 0.3F, 0.1F, 10, location, 20.0D);
    }
}
