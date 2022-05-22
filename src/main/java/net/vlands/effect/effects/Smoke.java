package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Smoke extends Effect {

    public Smoke(VLandsUtilities plugin) {
        super("smoke", "&8Smoke");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.SMOKE_LARGE.display(0.3F, 0.3F, 0.3F, 0.01F, 20, location, 20.0D);
    }
}
