package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Cloud extends Effect {

    public Cloud(VLandsUtilities plugin) {
        super("cloud", "&fCloud");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.CLOUD.display(0.0F, 0.0F, 0.0F, 0.1F, 100, location, 20.0D);
    }
}
