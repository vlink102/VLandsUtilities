package net.vlands.effect.effects;

import de.slikey.effectlib.util.ParticleEffect;
import net.vlands.VLandsUtilities;
import net.vlands.effect.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Heart extends Effect {

    public Heart(VLandsUtilities plugin) {
        super("heart", "&cHeart");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void playEffect(Player killer, Player victim) {
        Location location = victim.getLocation().subtract(0,1,0);

        ParticleEffect.HEART.display(0.4F, 0.4F, 0.4F, 0.1F, 10, location, 20.0D);
    }
}
