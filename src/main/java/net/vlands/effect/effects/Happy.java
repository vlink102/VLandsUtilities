package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Happy extends Effect {

    public Happy(VLandsUtilities plugin) {
        super("happy", "&eHappy");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.VILLAGER_HAPPY.display(0.5F, 0.5F, 0.5F, 0.01F, 100, location, 20.0D);
    }
}
