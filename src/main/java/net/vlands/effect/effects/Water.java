package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Water extends Effect {

    public Water(VLandsUtilities plugin) {
        super("water", "&9Water");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.WATER_WAKE.display(0.5F, 0.5F, 0.5F, 0.1F, 12, location, 20.0D);
    }
}
