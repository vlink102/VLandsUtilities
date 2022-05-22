package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Thunder extends Effect {

    public Thunder(VLandsUtilities plugin) {
        super("thunder", "&8Thunder");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.CLOUD.display(0.3F, 0.3F, 0.3F, 0.1F, 20, location, 20.0D);
        ParticleEffect.WATER_SPLASH.display(0.3F, 0.4F, 0.3F, 0.1F, 20, location, 20.0D);
        location.getWorld().strikeLightningEffect(location);
    }
}
