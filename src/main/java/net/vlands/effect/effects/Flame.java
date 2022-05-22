package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Flame extends Effect {

    public Flame(VLandsUtilities plugin) {
        super("flame", "&6Flame");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.FLAME.display(0.2F, 0.2F, 0.2F, 0.1F, 10, location, 20.0D);
    }
}
